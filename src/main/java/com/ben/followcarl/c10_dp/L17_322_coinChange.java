package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-11  08:04
 * @Description:
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 *
 *
 * 1. 确定dp数组以及下标的含义
 * dp[j]：凑足总额为j所需钱币的最少个数为dp[j]
 *
 * 2. 确定递推公式
 * dp[j] = min(dp[j], dp[j - coins[i]] + 1);
 * 考虑coins[i]: 凑足总额为j - coins[i]的最少个数为dp[j - coins[i]]，再加上一个钱币coins[i]即dp[j - coins[i]] + 1
 * 不考虑coins[i]: 如果不使用 coins[i]，dp[j] 会保留它之前的值，不进行更新
 * 递推公式：dp[j] = min(dp[j - coins[i]] + 1, dp[j]);
 *
 * 3. dp数组如何初始化
 * 首先凑足总金额为0所需钱币的个数一定是0，那么dp[0] = 0;
 *
 * 其他下标对应的数值呢？
 * 考虑到递推公式的特性，dp[j]必须初始化为一个最大的数，否则就会在min(dp[j - coins[i]] + 1, dp[j])比较的过程中被初始值覆盖。 所以下标非0的元素都是应该是最大值。
 *
 * 4. 确定遍历顺序
 * 本题求钱币最小个数，那么钱币有顺序和没有顺序都可以，都不影响钱币的最小个数。
 * 所以本题并不强调集合是组合还是排列。
 *
 * 如果求组合数先物品后背包
 * 如果求排列数先背包后物品，考虑顺序
 *
 * 求组合数是动态规划：518.零钱兑换II (opens new window)，
 * 求排列数是动态规划：377. 组合总和 Ⅳ (opens new window)。
 *
 * 本次使用先物品后背包：不考虑顺序，所以是先遍历coins，再遍历背包；
 * 5. 举例推导dp数组
 * 以输入：coins = [1, 2, 5], amount = 5为例
 * @Version: 1.0
 */
public class L17_322_coinChange {
    class Solution {
        public int coinChange(int[] coins, int amount) {
            // 背包: amount, [coins[i],amount]; coins: 物品 [0,coins.length)
            int[] dp = new int[amount + 1];
            int max = Integer.MAX_VALUE;
            for (int j = 0; j < dp.length; j++) {
                dp[j] = max;
            }
            dp[0] = 0; //和为0所需最少硬币数目是0;
            for (int i = 0; i < coins.length; i++) {
                for (int j = coins[i]; j <= amount; j++) {
                    // 只有当 dp[j - coins[i]] 是可行解（即不是最大值时），才更新 dp[j]。
                    // 前i-1个物品凑成的最小硬币数加上dp[j- coins[i]]加上1个钱币
                    if (dp[j - coins[i]] != max)  dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
                System.out.println(Arrays.toString(dp));
            }
            return dp[amount] == max ? -1 : dp[amount];
        }
    }
    // [0, 1, 2, 3, 4, 5] i = 0时，使用coins[0]拼凑amount需要的最小硬币数；
    //[0, 1, 1, 2, 2, 3]
    //[0, 1, 1, 2, 2, 1]


    @Test
    public void test_coinChange() {
        int[] coins = {1,2,5};
        int amount = 5;

        int res = new Solution().coinChange(coins, amount);
        System.out.println(res);
    }
}
