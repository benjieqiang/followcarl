package com.ben.followcarl.c11_monostonestack;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-26  14:56
 * @Description: 接雨水
 * 题目: 一堆高度不同的柱子,形成的凹槽,所能接的最大的雨水;
 * @Version: 1.0
 */
public class L4_42_trap {
    /**
     * 1.暴力解法思路:
     * 按照列来计算，宽度一定是1，每一列的雨水高度求和。
     *
     * 开始和结尾接不了雨水,因为不是封闭的, 每一列能存的雨水取决于左右柱子最底的那个, 木桶理论;

     * 按照列来计算，每一列雨水的宽度 = 1；
     * 该列接到的雨水体积 = 1 *（ Math.min(lHeight, rHeight) - height[i]）;
     * 1、for循环遍历整个数组，开头和结尾无法接雨水，因为没有形成封闭的区间；
     * 2、分别遍历当前列的左区间[i - 1, 0]和右区间[i+1, height.length]找到最高的左列和最高的右列；
     * 3、求当前列能够接到的雨水体积 = Math.min(lHeight, rHeight) - height[i]，
     * 4、如果该体积是正数，放入结果集中；
     **/
    public int trap1(int[] height) {
        int res = 0;
        // 从头到尾按列求；
        for (int i = 0; i < height.length; i++) {
            // 1. 第一个柱子和最后一个柱子不接雨水
            if (i == 0 || i == height.length - 1) continue;
            int left = height[i]; // 记录左边柱子的最高高度
            // 2. 找当前第i列的左侧最高列： 从i出发到0，找比当前height[i]高的；
            for (int l = i - 1; l >= 0; l--) {
                if (height[l] > left) left = height[l];
            }
            // 3. 找当前第i列的右侧最高咧；从i出发到length，找比当前height[i]高的；
            int right = height[i]; // 记录右边柱子的最高高度
            for (int r = i + 1; r < height.length; r++) {
                if (height[r] > right) right = height[r];
            }
            // 4. 求当前列装水体积；正数放入；
            int h = Math.min(left, right) - height[i];
            if (h > 0) res += h;
        }
        return res;
    }

    /*
     * 双指针解法思路：
     * 1、分别定义两个数组maxLeft和maxRight用来存放每一个柱子左边最高的柱子和右边最高的柱子；
     * 2、从左到右找左边最高的柱子，遍历数组从1到size，首先假设第一个柱子是最高的左柱子，放入到maxLeft中；
     *   如果当前柱子比已有的左边柱子记录还要高，那么就放入到maxLeft中，
     * 3、从右到左找右边最高的柱子，遍历数组从size - 2 到0，假设最后一个柱子是最高的右柱子，
     *   如果当前柱子比已有的右边最高的柱子还要高，那么就放入到maxRight中；
     * 4、找到之后，从0到size遍历整个数组，计算公式，计算1到size-1列的每一列能接到的雨水面积,累加到sum中；
     * h = Math.min(maxLeft[i], maxRight[i]) - height[i]
     * */
    public int trap2(int[] height) {
        if (height.length <= 2) return 0;

        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        int size = maxRight.length;

        // 记录每个柱子左边柱子最大高度
        maxLeft[0] = height[0];
        for (int i = 1; i < size; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }
        // 从右往左遍历, 比较当前柱子和右柱子数组的上一个元素的值, 取最高的;
        maxRight[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }

        // 求和, 越过开头和结尾的元素;
        int sum = 0;
        for (int i = 1; i < size - 1; i++) {
            int h = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if (h > 0) sum += h;
        }
        return sum;
    }

    /*
     *  单调栈思路：横向求解, 按行来求
     *  0  1  2  3  4
     * {60,20,20,10,30}
     *  __60__
     * │      │
     * │      │
     * │      │                     __30__
     * │      │__20__ __20__       │      │
     * │      │      │      │__10__│      │
     * │      │      │      │      │      │
          0      1      2      3       4
     * 1、定义一个单调栈，按照高度递增，首先放入数组的第一个元素的位置，也就是第一个柱子的位置. stack = [0:60] top is 0，这里0表示第一个柱子的下标，60表示高度；
     * 2、从1到size遍历整个数组，如果发现当前元素（高度）< 栈顶的元素的高度，入栈； 当前元素 == 栈顶元素高度， 更新为最右边的下标（先弹出，再入新）；
     *    stack = [3:10, 2:20, 0:60] top is 3;
     * 3、当前元素（高度）30大于栈顶元素对应的10，收获结果，30 > 10，出现凹槽
     *  1. 中间柱子：st弹出栈顶元素 mid = 3，柱高是10， st = [2:20, 0:60]
     *  2. 栈不为空, 中间柱子的左侧柱高 = 栈顶元素对应高度 = 20
     *      h = 当前柱高和左侧柱高取最小 - 中间柱子高度 = 20 - 10 = 10
     *      计算左右柱子宽度：w = 当前下标 - 中间柱子下标 - 1 = 4 - 2 - 1 = 1
     *      sum = 10 * 1 = 10;

     *     此时栈st = [2:20, 0:60]，下一个while循环，当前柱高30 和栈顶元素柱高20，出现凹槽
     *     1. 中间柱子弹出： 栈顶元素对应高度，mid = 20； 剩下栈st = [0:60]
     *     2. 栈不为空，记录左柱子：栈顶元素0柱高left = 60
     *                h = 当前和左柱子取min - mid = 30 - 20 = 10
     *                w = 当前 - left - 1 = 4 - 0 - 1 = 30;
     *          sum = 10 + 30 = 40;
     *
     *  3. 4入栈，当前栈是 st = [4: 30, 0:60]，i遍历到末尾结束循环；
     *
     *   while(!st.isEmpty() && height[i] > st.peek()) {
     *           int mid = st.peek(); //栈顶元素
     *           st.pop(); //弹出，因为该元素的下一个元素是它左侧最高列
     *           if (!st.isEmpty()) {
     *               int left = st.peek();
     *               int h = Math.min(height[i], height[left]) - height[mid]; //得到雨水的高度
     *               int w = i - left - 1;//
     *               if (h*w > 0) sum += h * w;
     *           }
     *    }
     *   st.push(i);

     * */
    public int trap3(int[] height) {
        int sum = 0;
        Deque<Integer> st = new LinkedList<>();
        st.push(0);
        for (int i = 1; i < height.length; i++) {
            if (height[i] < height[st.peek()]) {
                st.push(i);
            } else if (height[i] == height[st.peek()]) {
                // 高度相等的柱子，先弹出，再放入, 保证栈里面只放最右边柱子的下标；这个柱子离当前元素i最近。
                st.pop();
                st.push(i);
            } else {
                // height[i]是右边的柱子; 发现高柱子，对应出现凹槽；
                while (!st.isEmpty() && height[i] > height[st.peek()]) {
                    int mid = st.pop(); // 中间的柱子弹出去
                    if (!st.isEmpty()) {
                        int left = st.peek(); // 左边的柱子
                        int h = Math.min(height[i], height[left]) - height[mid]; // 高度;
                        int w = i - left - 1; // i - left表示左柱子和右柱子之间的距离，减1表示中间盛水的宽度；
                        sum += h * w;
                    }
                }
                st.push(i);
            }
        }

        return sum;
    }

    public int trapError(int[] height) {
        if (height == null || height.length == 0) return 0;
        int sum = 0;
        Deque<Integer> st = new LinkedList<>();
        st.push(0);
        for (int i = 1; i < height.length; i++) {
            if (height[i] < height[st.peek()]) {
                st.push(i);
            } else if (height[i] == height[st.peek()]) {
                st.pop();
                st.push(i);
            } else {
                while (!st.isEmpty() && height[i] > height[st.peek()]) {
                    int mid = st.pop();
                    if (!st.isEmpty()) {
                        int left = st.peek();
                        int h = Math.min(height[left], height[i]) - height[mid];
                        int w = i - left - 1;
                        if (h * w > 0) sum += w * h;
                    }
                    st.push(i); // 错误点：还没比较完，就把当前元素下标加入到栈中，应该依次把栈中的元素的首大给找完，在入栈。
                }
            }
        }

        return sum;
    }
    @Test
    public void testTrap() {
//        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};//6
        int[] nums = {60,20,20,10,30}; //
        System.out.println(Arrays.toString(nums));
//        System.out.println(trap2(nums));
//        System.out.println(trap3(nums));
        System.out.println(trapError(nums));
    }
}
