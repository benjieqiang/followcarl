package com.ben.followcarl.c11_monostonestack;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-25  18:13
 * @Description: 给定一个循环数组nums（nums[nums.length - 1]的下一个元素是nums[0]），返回nums中每个元素的 下一个更大元素 。
 * 数字 x的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 通俗解释：
 * 数组现在是个环，让求下一个第一次出现的最大元素，
 * for循环2倍数组长度，取模来模拟成环，nums[i % nums.size()]
 * 最后的值只取原来数组的长度的数组;
 * 剩余思路和每日温度一样，
 * @Version: 1.0
 */
public class L3_503_nextGreaterElements {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> st = new LinkedList<>();
        st.push(0);

        for (int i = 1; i < nums.length * 2; i++) {
            int val = nums[i % nums.length]; // 取模就模拟完成转圈的过程;
            if (val <= nums[st.peek()]) {
                st.push(i % nums.length);
            } else {
                while (!st.isEmpty() && val > nums[st.peek()]) {
                    res[st.peek()] = val;
                    st.pop();
                }
                st.push(i % nums.length);
            }
        }

        return res;
    }

    /**
     * @param nums:
     * @return int
     * @description st存放递增的处理过的下标
     * @author benjieqiang
     * @date 2023/8/17 5:23 PM
     */
    public int[] nextGreaterElements2(int[] nums) {
        int[] res = new int[nums.length];
        if (nums.length == 0) return res;
        Arrays.fill(res, -1);
        Deque<Integer> st = new LinkedList<>();
        st.push(0);
        for (int i = 1; i < nums.length * 2; i++) {
            int val = nums[i % nums.length];
            if (val <= nums[st.peek()]) {
                st.push(i % nums.length);
            } else {
                while (!st.isEmpty() && val > nums[st.peek()]) {
                    // 当前值比栈顶下标对应的值大, 收获结果
                    res[st.peek()] = val;
                    st.pop();
                }
                st.push(i % nums.length);
            }
        }
        return res;
    }
    @Test
    public void textNextGre() {
        int[] nums = {1, 2, 1}; //{2,-1,2}
        int[] res = nextGreaterElements2(nums);
        System.out.println(Arrays.toString(res));
    }
}
