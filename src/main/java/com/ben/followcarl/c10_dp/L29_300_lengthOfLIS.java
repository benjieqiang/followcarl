package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-19  10:36
 * @Description: 最长递增子序列，不需要连续，所有是双层for循环
 * 1. dp[i] 表示i之前包括i的以nums[i]结尾的最长递增子序列的长度
 * 比较nums[i]和nums[j]大小，dp[i] = Math.max(dp[i], dp[j] + 1)
 * dp[i]初始化为1。
 * @Version: 1.0
 */
public class L29_300_lengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) return 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            System.out.println(Arrays.toString(dp));
            if (dp[i] > res) res = dp[i];
        }
        return res;
    }

    @Test
    public void testLengthOfLIS() {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }
}
