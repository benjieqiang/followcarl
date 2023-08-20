package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-13  08:29
 * @Description: 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 从[1,5,5,11]中找到一组元素相加和为11，如果为11，则剩下的元素的和也是11（因为是平分这22，得到的是两个子集）
 * 进一步抽象：
 * 从[1,5,5,11]这些物品中找到一些物品，装入背包容量为11的背包，使其最大的价值就是11。
 *
 * 转化为01背包问题
 * 1. 背包容量是bagSize = sum / 2， sum是所有元素之和
 * 2. 物品重量 = 物品价值 = nums中的元素值
 * 3. 背包满: dp[bagSize] = bagSize;
 * 4. 不可重复取数
 * 转为有nums.length个物品，sum/2的背包，weight[i] = value[i] 第i件物品的价值和重量一样多都是nums[i]
 * 求该背包中能装的最大物品的价值；
 * 最大物品价值得和sum/2一致；
 * @Version: 1.0
 */
public class L9_416_canPartition {
    public boolean canPartition(int[] nums) {
        // 背包的最大容量是sum/2
        if (nums.length <= 1) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 == 1) return false; // 不能被整数，不可能分成两个子序列
        int bagSize = sum / 2; // 背包大小；
        int[] dp = new int[bagSize + 1];
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        System.out.println(Arrays.toString(dp));

        return dp[bagSize] == bagSize;
    }

    public boolean canPartition2(int[] nums) {
        if (nums.length <= 1) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        int bagSize = sum / 2;

        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
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
