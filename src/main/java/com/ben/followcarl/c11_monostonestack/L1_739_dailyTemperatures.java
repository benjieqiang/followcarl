package com.ben.followcarl.c11_monostonestack;

import org.junit.Test;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-25  11:11
 * @Description: 每日温度
 * 给定一个整数数组temperatures，表示每天的温度，返回一个数组answer，
 * 其中answer[i]是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果气温在这之后都不会升高，请在该位置用0 来代替。
 * 暴力解法：
 * 双层for循环来遍历：
 * 1.第一个for循环从0开始到最后，用来遍历整个数组；
 * 2.第二个for循环用来找首个最大元素的位置，用res[i]记录最大元素到最小元素的距离；
 *
 * 单调栈解法：
 * 通常是一维数组，要寻找任一个元素的右边或者左边第一个比自己大或者小的元素的位置，此时我们就要想到可以用单调栈了。时间复杂度为O(n)。
 * 作用：存放遍历过的元素，以某种形式存放起来了；
 * @Version: 1.0
 */
public class L1_739_dailyTemperatures {
    // 暴力解法
    public int[] dailyTemperatures1(int[] temperatures) {
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] - temperatures[i] > 0) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }
    // 单调栈, 栈里放的是递减的下标，但是元素是递增的
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        Deque<Integer> st = new LinkedList<>(); // 存放温度的索引

        st.push(0); // 栈里放首位元素下标，之后从1开始遍历整个数组, top is left.
        for (int i = 1; i < len; i++) {
            // 当前元素 vs 栈顶元素（下标）对应的元素temperatures[st.peek()]
            // <= 就入栈, 这样维护了一个从栈顶到栈头的单调递增栈（从小到大）
            if (temperatures[i] <= temperatures[st.peek()]) {
                st.push(i);
            } else {
                // >= 则持续比较,直至遇到当前元素比栈顶元素大时，找到栈顶元素的首大值；
                // 出栈得判断一下栈是否为空;
                while (!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
                    res[st.peek()] = i - st.peek(); // 注意: res[st.peek()]
                    st.pop(); // 比较完就弹出;
                }
                st.push(i);
            }
        }
        return res;
    }
    //精简版
    public int[] dailyTemperatures2(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        Deque<Integer> st = new LinkedList<>();

        st.push(0);
        for (int i = 1; i < len; i++) {
            while (!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
                res[st.peek()] = i - st.peek();
                st.pop();
            }
            st.push(i);
        }
        return res;
    }

    public int[] dailyTemperatures_error(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return temperatures;
        int length = temperatures.length;
        int[] res = new int[length];
        Deque<Integer> st = new LinkedList<>();
        st.push(0);
        for (int i = 1; i < length; i++) {
            int cur = temperatures[i];
            int top = temperatures[st.peek()]; //
            if (cur <= top) {
                st.push(i);
            } else {
                while (!st.isEmpty() && cur > top) {
                    // 问题出在这里，如果是stack = [4:69, 3:71, 2:75] i = 5 nums[5] = 72, 第一轮比较完后会找到69的首大72，计算res[4]=1
                    // 第二轮，cur不变，还是当前元素72，但是top变了，这里top没变，导致错误；
                    res[st.peek()] = i - st.peek();
                    st.pop();
                }
                st.push(i);
            }
        }

        return res;
    }
    @Test
    public void tesDailyTemp() {
        int[] temp = {73,74,75,71,69,72,76,73};
        int[] res = dailyTemperatures_error(temp);
        System.out.println(Arrays.toString(res));
    }
    @Test
    public void tesDailyTempOn2() {
        int[] temp = {30,40,50,60};
        int[] res = dailyTemperatures1(temp);
        System.out.println(Arrays.toString(res));
    }
}
