package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-13  08:29
 * @Description: 划分和相等的子序列问题转化为01背包问题
 * 1. 背包容量是target = sum / 2， sum是所有元素之和
 * 2. 物品重量 = 物品价值 = nums中的元素值
 * 3. 背包满: dp[target] = target;
 * 4. 不可重复取数
 * 转为有nums.length个物品，sum/2的背包，weight[i] = value[i] 第i件物品的价值和重量一样多都是nums[i]
 * 求该背包中能装的最大物品的价值；
 * @Version: 1.0
 */
public class L9_416_canPartition {
    public boolean canPartition(int[] nums) {
        // 背包的最大容量是sum/2
        if (nums.length <= 1) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 == 1) return false; //不能被整数，不可能分成两个子序列
        int bagSize = sum / 2; //背包大小；
        int[] dp = new int[bagSize + 1];
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
//                System.out.println(Arrays.toString(dp));
                if (dp[bagSize] == bagSize) return true;
            }
        }

        return dp[bagSize] == bagSize;
    }

    @Test
    public void testPartition() {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums));
    }
}
