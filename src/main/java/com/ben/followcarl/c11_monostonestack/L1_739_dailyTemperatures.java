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
 * 2.第二个for循环用来找最大元素的位置，用res[i]记录最大元素到最小元素的距离；
 *
 * 单调栈解法：
 * 通常是一维数组，要寻找任一个元素的右边或者左边第一个比自己大或者小的元素的位置，此时我们就要想到可以用单调栈了。时间复杂度为O(n)。
 * 作用：存放遍历过的元素，以某种形式存放起来了；
 * @Version: 1.0
 */
public class L1_739_dailyTemperatures {
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
        System.out.println(Arrays.toString(res));
        return res;
    }
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        Deque<Integer> st = new LinkedList<>();

        st.push(0);
        for (int i = 1; i < len; i++) {
            if (temperatures[i] <= temperatures[st.peek()]) {
                st.push(i);
            } else {
                while (!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
                    res[st.peek()] = i - st.peek();
                    st.pop();
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
    @Test
    public void tesDailyTemp() {
        int[] temp = {73,74,75,71,69,72,76,73};
        int[] res = dailyTemperatures(temp);
        System.out.println(Arrays.toString(res));
    }
}
