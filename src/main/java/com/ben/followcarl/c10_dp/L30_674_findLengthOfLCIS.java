package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-19  10:36
 * @Description: 连续的最长子序列
 * dp[i]以下标i结尾的最长连续子序列长度；连续的，因此只需要一层for循环。
 * dp[i] = Math.max(dp[i], dp[i- 1] + 1);
 * 如果 nums[i] > nums[i - 1]，那么以 i 为结尾的连续递增的子序列长度 一定等于 以i - 1为结尾的连续递增的子序列长度 + 1;
 * 即：dp[i] = dp[i - 1] + 1;
 * 如果 nums[i] <= nums[i-1]，说明当前序列不再是递增的，但你不需要显式地将长度重置为0，因为代码逻辑通过 Arrays.fill(dp, 1) 和不更新 dp[i]，
 * 已经隐含地处理了这个情况。每当 nums[i] <= nums[i-1] 时，dp[i] 保持为 1，相当于从当前元素重新开始计算新的递增序列长度。
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
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * @param nums:
     * @return int
     * @description 贪心算法
     * @author benjieqiang
     * @date 2023/8/21 6:34 PM
     */
    public int findLengthOfLCIS2(int[] nums) {
        if (nums.length <= 1) return nums.length;
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
