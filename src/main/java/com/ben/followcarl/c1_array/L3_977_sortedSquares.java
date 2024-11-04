package com.ben.followcarl.c1_array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-02  22:05
 * @Description:
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 *
 * 二刷：left<=right， 还要看清题意，是升序还是降序排列；
 * 三刷: left <= right: 因为能取到left和right相等的情况;
 * @Version: 1.0
 */
public class L3_977_sortedSquares {
    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length];
        int right = nums.length - 1;
        int i = nums.length - 1;
        int left = 0;
        while(left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                res[i--] = nums[left] * nums[left];
                left++;
            } else {
                res[i--] = nums[right] * nums[right];
                right--;
            }
        }
        return res;
    }

    class Solution {
        public int[] sortedSquares(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            int[] res = new int[nums.length];
            int index = nums.length - 1;
            while (left <= right) {
                int lval = nums[left] * nums[left];
                int rval = nums[right] * nums[right];

                if (lval > rval) {
                    res[index] = lval;
                    left++;
                } else {
                    res[index] = rval;
                    right--;
                }
                index--;
            }

            return res;
        }
    }
    @Test
    public void testSortedSquares() {
        int[] nums = {-4,-1,0,4,10};
        System.out.println(Arrays.toString(sortedSquares(nums)));
    }
}
