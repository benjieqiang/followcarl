package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-19  10:36
 * @Description:
 * @Version: 1.0
 */
public class L30_674_findLengthOfLCIS {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int res = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = Math.max(dp[i - 1] + 1, dp[i]);
            }
            if (res < dp[i]) res = dp[i];
        }
        return res;
    }

    public int findLengthOfLCIS2(int[] nums) {
        // 贪心算法
        int count = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) { // 连续记录
                count++;
            } else { //不连续记录
                count = 1;
            }
            if (count > res) res = count;
        }
        return res;
    }

    @Test
    public void testFindLengthOfLCIS() {
//        int[] nums = {2,2,2,2,2};
//        int[] nums = {1,3,5,7};
        int[] nums = {1,3,5,4,7};
        System.out.println(findLengthOfLCIS(nums));
    }
}
