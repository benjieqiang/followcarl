package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-19  10:36
 * @Description:
 * dp[i]以下标i结尾的最长连续子序列长度；连续的，因此只需要一层for循环。
 * dp[i] = Math.max(dp[i], dp[i- 1] + 1);
 * @Version: 1.0
 */
public class L30_674_findLengthOfLCIS {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int res = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = Math.max(dp[i - 1] + 1, dp[i]);
            }
            if (dp[i] > res) res = dp[i];
        }
        return res;
    }

    public int findLengthOfLCIS2(int[] nums) {
        if (nums.length <= 1) return nums.length;
        // 贪心算法
        int count = 1;
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) { // 连续记录
                count++;
            } else { // 如果当前数值不超过后一个数，那么就得重新更新count为1，从这里开始记录
                count = 1;
            }
            // 最后选最大的count就是所求的最长连续子序列
            if (count > res) res = count;
        }
        return res;
    }

    @Test
    public void testFindLengthOfLCIS() {
//        int[] nums = {2,2,2,2,2};
//        int[] nums = {1,3,5,7};
        int[] nums = {1,3,5,4,7};
        System.out.println(findLengthOfLCIS2(nums));
    }
}
