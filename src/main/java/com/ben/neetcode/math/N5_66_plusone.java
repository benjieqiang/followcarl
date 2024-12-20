package com.ben.neetcode.math;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-18  19:32
 * @Description: Plus One
 * Solved
 * You are given an integer array digits, where each digits[i] is the ith digit of a large integer. It is ordered from most significant to least significant digit, and it will not contain any leading zero.
 *
 * Return the digits of the given integer after incrementing it by one.
 *
 * Example 1:
 *
 * Input: digits = [1,2,3,4]
 *
 * Output: [1,2,3,5]
 * Explanation 1234 + 1 = 1235.
 *
 * Example 2:
 *
 * Input: digits = [9,9,9]
 *
 * Output: [1,0,0,0]
 * @Version: 1.0
 */
public class N5_66_plusone {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) return new int[]{};
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            // 末尾第一个9，置0，
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                // 首个不为9的元素加1
                digits[i]++;
                return digits;
            }
        }
        // 全是9，直接长度+1，首个元素置1
        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }
}
