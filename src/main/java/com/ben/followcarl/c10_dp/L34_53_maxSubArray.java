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
 * 3. 遍历顺序,从1到nums.length-1,dp数组最大取到nums.length-1, 而且dp[nums.length - 1]一定存放的是最大值吗?
 *   不一定, nums = [-2,1,-3,4,-1,2,1,-5,4],连续子数组 [4,-1,2,1] 的和最大，为 6 所以要记录最大的dp值;
 * @Version: 1.0
 */
public class L34_53_maxSubArray {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0]; // res保存最大的dp[i]
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > res) res = dp[i];
        }
        return res;
    }

    /**
     * @param nums:
     * @return int
     * @description 贪心算法, 和为负数只会拉低结果,所以置为0,重新开始;
     * @author benjieqiang
     * @date 2023/8/22 7:07 PM
     */
    public int maxSubArray2(int[] nums) {
        if (nums.length == 1) return nums[0];
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res = Math.max(res, sum);
            if (sum < 0) sum = 0;
        }
        return res;
    }
    @Test
    public void testMaxSubArray() {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray2(nums));
    }
}
