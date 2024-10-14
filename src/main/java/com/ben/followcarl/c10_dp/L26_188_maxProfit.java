package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-17  16:38
 * @Description: 股票买卖k次； hard
 * @Version: 1.0
 */
public class L26_188_maxProfit {
    // dp
    /*
      1. dp数组的含义
      dp[i][j]，其中：i 表示第 i 天。j 表示第 j 次交易操作，奇数表示持有（买入）股票的状态，偶数表示不持有（卖出）股票的状态。

      dp[i][0] 表示第i天持有的最大现金，不操作，
      dp[i][1] 表示第一次持有：在前i-1天就持有了，到了第i天不进行买入操作 + 到第i天才真正的买入了股票。
      dp[i][2] 表示第一次不持有：在前i-1天就卖了，到了第i天不操作 + 等到第i天才进行卖出股票；
      dp[i][3] 表示第二次持有；在前i-1天就完成了买入和卖出操作，一直维持到第i天+ 或者到了第i天先卖出第一次持有的股票进行第二次买入
      dp[i][4] 表示第二次不持有：在前i-1天完成了两次买入卖出的操作 + 到第i天先买入第二次股票，再卖出第二次
      2. dp[i][0] = dp[i - 1][0]
      dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
      dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + prices[i]);
      dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] - prices[i]);
      dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3] + prices[i]); //dp[i-1][3]表示到i的时候股票的状态是持有（可能在i-1之前已经加成了买卖买操作维持到现在，也可能到现在只是买卖到i准备卖）
      3. dp[0][0] = 0。第一天啥也不干就是0
      dp[0][1] = -prices[0]; 第一天买入股票花了prices[0]
      dp[0][2] = 0; 第一天买入立马卖出；
      dp[0][3] = -prices[0]; 第一天第二次持有的意思，买卖一次再买入花了prices[0]的钱；
      dp[0][4] = 0; 买卖两次

      发现规律:
      第一天可以买卖多次, 卖的时候, j为偶数卖出,最大利润为0, 奇数买入,最大利润为-prices[0]

      时间复杂度: O(n * k)，其中 n 为 prices 的长度
      空间复杂度: O(n * k)
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        // dp[i][j]，其中：i 表示第 i 天。j 表示第 j 次交易操作，奇数表示持有（买入）股票的状态，偶数表示不持有（卖出）股票的状态。
        // 每次交易包括一次买入和一次卖出，买入是奇数次操作，卖出是偶数次操作。
        // 对于 k 次交易，2 * k + 1 表示从0到2k的买入卖出操作；
        int[][] dp = new int[prices.length][2 * k + 1];
        // 在第一天时，如果是持有股票状态（即 j 为奇数的列），它的初始状态为 -prices[0]，表示买入一股股票后的利润是负数（即花费了钱）
        for (int j = 1; j < 2 * k; j += 2) {
            dp[0][j] = -prices[0]; //保证在j为奇数时是第j次持有；
        }
        for (int j = 0; j < 2 * k; j += 2) { // j += 2.保证了下面dp[i - 1][j]是卖出的操作;j为偶数才能卖出；
            for (int i = 1; i < prices.length; i++) {
                // 买入操作: 保持前一天持有股票的动作 vs 只有当我们在前一天不持有股票时+才能在i天买入
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                // 卖出操作：保持前一天不持有股票的状态 vs 前一天买入+i天卖出
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }

        return dp[prices.length - 1][2 * k]; //
    }

    @Test
    public void testMaxProfit() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfit(4, prices));
    }
}
