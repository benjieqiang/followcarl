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
     * 1.遍历整个数组，当前柱子为i，找到左边比它矮的柱子，找到右边比它矮的柱子，找不到说明这跟柱子能贯穿整个柱状图；
     * 2.如果左边柱子比当前柱子矮，说明不能向左扩展了；如果右边柱子比当前柱子矮，也不能向右扩展，
     * 最后结果就是向左向右扩展形成的最大宽度w = right - left - 1; h = heights[i]当前柱子高度；
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

    /* 双指针法：
     *
     *
     *
     * */
    public int largestRectangleArea2(int[] heights) {
        int size = heights.length;
        int[] minLeftIndex = new int[size];
        int[] minRightIndex = new int[size];

        // 记录每个柱子 左边第一个小于该柱子的下标
        minLeftIndex[0] = -1; // 注意这里初始化，防止下面while死循环
        for (int i = 1; i < size; i++) {
            int t = i - 1;
            // 这里不是用if，而是不断向左寻找的过程
            while (t >= 0 && heights[t] >= heights[i]) t = minLeftIndex[t];
            minLeftIndex[i] = t;
        }

        System.out.println(Arrays.toString(minLeftIndex));

        // 记录每个柱子 右边第一个小于该柱子的下标
        minRightIndex[size - 1] = size; // 注意这里初始化，防止下面while死循环
        for (int i = size - 2; i >= 0; i--) {
            int t = i + 1;
            // 这里不是用if，而是不断向右寻找的过程
            while (t < size && heights[t] >= heights[i]) t = minRightIndex[t];
            minRightIndex[i] = t;
        }
        System.out.println(Arrays.toString(minRightIndex));
        // 求和
        int res = 0;
        for (int i = 0; i < size; i++) {
            int sum = heights[i] * (minRightIndex[i] - minLeftIndex[i] - 1);
            res = Math.max(sum, res);
        }
        return res;
    }

    /*
     *  单调栈：递减的，找到当前元素mid 的左边比它小的柱子left和右边比它小的柱子right；
     *  末尾加0：比如[2,4,6,8] 一直往栈里面加是这样的[8,6,4,2]，一直结束不了，因为找不到一个数小于当前栈顶的元素
     *  头部加0：比如[8,6,4, 2], 往栈里面加成为[8]，此时heights[1]= 6和栈顶元素比较比它小，那么要开始进行收获结果了，
     *  mid = st.peek(); st.pop(); left应该是栈顶元素，但是此时栈已经为空了，没法给left赋值，所以需要加上0。进行计算。
     *  for循环遍历数组，当前元素大于等于栈顶元素就一直入栈；
     *  当前元素比栈顶元素小的时候，进行收获结果的操作：
     *  利用while来持续比较和栈顶元素的大小，直到当前元素不小于栈顶元素，则把该元素入栈；
     *  mid = st.peek(); // 栈顶元素
     *  st.pop();// 弹出该元素因为要拿出它的左边最小的柱子的下标；
     *  left = st.pop();
     *  只要栈不为空，h = heights[mid]; w = i - left - 1;
     *  面积为h * w
     *  res = max(h*w, res);

     *
     *
     *  */
    public int largestRectangleArea3(int[] heights) {
        int res = 0;
        // 1. 数组首尾加0
        int[] newHeights = new int[heights.length + 2];
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;
        for (int i = 0; i < heights.length; i++) {
            newHeights[i + 1] = heights[i];
        }
//        System.out.println(Arrays.toString(newHeights));
        heights = newHeights;

        // 2. 单调递减栈
        Deque<Integer> st = new LinkedList<>();
        st.push(0);

        for (int i = 1; i < heights.length; i++) {
            if (heights[i] >= heights[st.peek()]) {
                st.push(i);
            } else {
                while(!st.isEmpty() && heights[i] < heights[st.peek()]) {
                    int mid = st.peek();
                    st.pop();
                    if (!st.isEmpty()){
                        int left = st.peek();
                        int h = heights[mid];
                        int w = i - left - 1;
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
        System.out.println(largestRectangleArea3(nums));
    }

}
