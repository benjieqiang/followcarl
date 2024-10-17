package com.ben.followcarl.c11_monostonestack;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-26  16:55
 * @Description: 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * @Version: 1.0
 */
public class L5_84_largestRectangleArea {

    /*
     * 暴力解法思路：
     * 1.遍历整个数组，当前柱子为i，从中心分别向左右扩展，寻找首个左矮柱子和首个右矮柱子；两个最矮柱子中间就是当前柱子能覆盖的柱子。
     * 2.左右矮柱子之间的宽度w = right - left - 1; h = heights[i]当前柱子高度；
     * */
    public int largestRectangleArea(int[] heights) {
        int sum = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int right = i;

            for (; left >= 0; left--) {
                if (heights[left] < heights[i]) break; // 找到最矮的左柱子；
            }
            for (; right < heights.length; right++) {
                if (heights[right] < heights[i]) break; //找到最矮的右柱子
            }
            int w = right - left - 1; // 当i = 2时，此时left = 1跳出了上面的for循环，所以要计算该区间的长度，得减1
            int h = heights[i];
            sum = Math.max(sum, w * h);
        }
        return sum;
    }


    /**
     * @param heights:
     * @return int
     * @description 双指针 todo:没搞懂；
     * 难点在寻找左柱子数组和右柱子数组
     * @author benjieqiang
     * @date 2023/8/17 7:57 PM
     */
    public int largestRectangleArea2(int[] heights) {
        int length = heights.length;
        int[] minLeftIndex = new int[length];
        int[] minRightIndex = new int[length];

        // 记录每个柱子 左边第一个小于该柱子的下标
        minLeftIndex[0] = -1; // 注意这里初始化，防止下面while死循环
        for (int i = 1; i < length; i++) {
            int t = i - 1;
            // 这里不是用if，而是不断向左寻找的过程
            while (t >= 0 && heights[t] >= heights[i]) t = minLeftIndex[t];
            minLeftIndex[i] = t;
        }

        System.out.println(Arrays.toString(minLeftIndex));

        // 记录每个柱子 右边第一个小于该柱子的下标
        minRightIndex[length - 1] = length; // 注意这里初始化，防止下面while死循环
        for (int i = length - 2; i >= 0; i--) {
            int t = i + 1;
            // 这里不是用if，而是不断向右寻找的过程
            while (t < length && heights[t] >= heights[i]) t = minRightIndex[t];
            minRightIndex[i] = t;
        }
        System.out.println(Arrays.toString(minRightIndex));
        // 求和
        int res = 0;
        for (int i = 0; i < length; i++) {
            int sum = heights[i] * (minRightIndex[i] - minLeftIndex[i] - 1);
            res = Math.max(sum, res);
        }
        return res;
    }

    /*
     *  单调递减栈(从大到小的顺序) 找当前柱子首个左矮柱子和首个右矮柱子，中间宽度 * 当前柱高 = s
        1. 数组首尾加0 来结束栈;
     *  末尾加0的原因：对于原来递增数组，[2,4,6,8]， 先加入首元素，stack = [0:2] 之后因为全部元素大于栈顶元素，都入栈
     *   stack = [3:8,2:6,1:4,2:0], top is 3. 加末尾0让他强制走入while循环中求当前柱子的左右矮柱子。
     *
     *  头部加0的原因：比如递减[8,6,4,2], 先加入首元素，stack = [0:8], 从第一个元素6开始遍历，因为6小于8，所以找到了矮柱子，
     *  进入while循环，此时中间柱子mid = 8, 右矮柱子right = 6，栈为空了，左矮柱子没有，所以直接跳过，一次加入6，4，2得到结果是0。加头部0是为了防止这种情况。
     *  heights = [2,4]
     *  1. 扩容： heights = [0,2,4,0];
     *  2. 首元素入栈，stack = [0:0] top is 0;
     *  3. for循环从1开始遍历到3，比较当前柱子高度和栈顶柱子的高度大小，大的等的入栈，小的比较
     *      1. i = 1 heights[1] = 2 > 0, 入栈， stack = [1:2, 0:0]
     *      2. i = 2, 高度4 > 2,入栈， stack = [2:4, 1:2, 0:0]
     *      3. i = 3, 高度0 < 4，找到右矮柱子了。
     *       while循环，栈不为空，mid = st.pop();
     *      left柱子 = st.pop()2，w = i - left - 1，两个左右矮柱子之间的距离，
     *      高度是heights[mid] s = Math.max(s, w *h);
     *
     *  */
    public int largestRectangleArea3(int[] heights) {
        int res = 0;
        // 1. 数组首尾加0 来结束栈;
        int[] newHeights = new int[heights.length + 2];
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;
        for (int i = 0; i < heights.length; i++) {
            newHeights[i + 1] = heights[i];
        }
        heights = newHeights;

        // 2. 单调递减栈
        Deque<Integer> st = new LinkedList<>();
        st.push(0);

        for (int i = 1; i < heights.length; i++) {
            if (heights[i] >= heights[st.peek()]) {
                // 只要发现当前柱子比栈顶的柱子要高, 入栈.因为要找下一个矮柱子
                st.push(i);
            } else if (heights[i] == heights[st.peek()]) {
                st.pop(); // 这个可以加，可以不加，效果一样，思路不同
                st.push(i);
            } else {
                // 发现右矮柱子
                while (!st.isEmpty() && heights[i] < heights[st.peek()]) {
                    int mid = st.pop(); // 当前柱子
                    if (!st.isEmpty()) {
                        int left = st.peek(); // 左矮柱子
                        int h = heights[mid];
                        int w = i - left - 1; // 比如以5为基准,左边比5小的数字的下标是1, 右边比5小的下标是4, 那么要求中间的长度是
                        // 4 - 1 - 1 = 2;
                        res = Math.max(res, h * w);
                    }
                }
                st.push(i);
            }
        }
        return res;
    }

    @Test
    public void testLarge() {
        int[] nums = {2, 1, 5, 6, 2, 3};
//        int[] nums = {4,2}; //4
//        System.out.println(largestRectangleArea(nums));
        System.out.println(largestRectangleArea2(nums));
//        System.out.println(largestRectangleArea3(nums));
    }

}
