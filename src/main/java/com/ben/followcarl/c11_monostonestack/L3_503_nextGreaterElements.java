package com.ben.followcarl.c11_monostonestack;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-25  18:13
 * @Description:
 * 给定一个循环数组nums（nums[nums.length - 1]的下一个元素是nums[0]），返回nums中每个元素的 下一个更大元素 。
 * 数字 x的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 通俗解释：
 * 数组现在是个环，让求下一个第一次出现的最大元素，
 * for循环两倍数组长度，取模来模拟成环，nums[i % nums.size()]
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
            int val = nums[ i % nums.length];
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

    @Test
    public void textNextGre() {
        int[] nums = {1,2,1}; //{2,-1,2}
        int[] res = nextGreaterElements(nums);
        System.out.println(Arrays.toString(res));
    }
}
