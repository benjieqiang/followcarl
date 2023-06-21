package com.ben.followcarl.c10_dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-21  20:17
 * @Description: 最大子序列之和
 * 贪心算法见L3_53_maxSubArray
 * dp[i] 以nums[i]结尾的最大子序列和；
 * 1. dp[i] = Math.max(dp[i-1] + nums[i], nums[i])
 * dp[i-1] + nums[i]: 延续这前面的和加上nums[i]的值
 * nums[i]: 从i这里开始计算；
 * 2. 初始化： dp[0] = nums[0];
 * @Version: 1.0
 */
public class L34_53_maxSubArray {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > res) res = dp[i];
        }
        return res;
    }

    @Test
    public void testMaxSubArray() {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        Assert.assertEquals(6, maxSubArray(nums));
    }
}
