package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-17  16:38
 * @Description: todo : 2023-08.28 11:37
 * 股票买卖多次, 但是买卖含有冷冻期，卖出股票第二天就是冷冻期，不能交易。
 * <p>
 * 对于dp[0][1]不能确定该初始化什么，带入dp公式中进行分析；
 * @Version: 1.0
 */
public class L27_309_maxProfit {
    // dp
    /* 1. dp数组的含义: 包含4个状态: 持有股票, 卖出股票, 第i天卖出股票, 冷冻期(只有在前一天卖出了才能进入)
        dp[i][0]: 第i天持有股票的状态。
        dp[i][1]: 第i天保持卖出股票的状态：前一天卖出；前一天是冷冻期；冷冻期持续了好几天；(注意：和第122题区别：dp[i][1]无冷冻期，表示不持有股票的状态)
        dp[i][2]: 第i天卖出股票的状态, 卖出操作必须在第i天举行
        dp[i][3]: 第i天是冷冻期，前一天即i-1天肯定卖出了股票；卖出第二天就是冷冻期;

        dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][3] - prices[i], dp[i-1][1] - prices[i]));
            前i-1天持有股票:
                dp[i-1][0] : 表示前一天就是持有股票（买入操作发生在前i-1天的任何一天），到第i天没有任何操作；
            第i天买入股票分成两种情况：从i-1天是冷冻期到i天买入或从前i-1天任意一天卖出股票到第i天买入;
                1. 前一天(i-1天)是冷冻期，第二天也就是第i天买入股票操作；dp[i-1][3] - prices[i]
                2. 前i-1天卖出股票进入冷冻期, 到第i天再买入 dp[i-1][1] - prices[i]
        dp[i][1] = Math.max(dp[i-1][1], dp[i-1][3]);
            前一天卖出股票保持到现在 + 冷冻期的下一天，一直持续到现在
        dp[i][2] = dp[i][0] + prices[i];
            第i天持有这个股票 + 卖出利润;
        dp[i][3] = dp[i-1][2];
            前一天卖出状态，第i天就成冷冻期；

        dp[0][0] = -prices[0]; 第0天持有股票当天买入了；
        dp[0][1] = 0 ; 用在这里，dp[1][0] = dp[0][1] - prices[1]，dp[1][0]表示第一天持有股票的动态；
        dp[0][2] = 0 ； 第0天卖出股票，或者想象成当天买当天卖；
        dp[0][3] = 0 ；

        最后的结果是卖出的时候的最大值：


     * */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int length = prices.length;
        int[][] dp = new int[length][4];
        dp[0][0] = -prices[0];

        for (int i = 1; i < length; i++) {
            // 1. 前i-1天持有 vs 前i-1天是冷冻期，到第i天买入 vs 前i-1天卖出，到第i天买入；
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3] - prices[i], dp[i - 1][1] - prices[i]));
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]); //2. 前i-1天卖出股票保持到现在 + 前i-1交易了，导致第i-1天是冷冻期，一直持续到现在
            dp[i][2] = dp[i][0] + prices[i]; // 第i天持有再卖出；
            dp[i][3] = dp[i - 1][2]; // 前i-1天是卖出了，接着第i天就是冷冻期；
        }

        return Math.max(dp[length - 1][1], Math.max(dp[length - 1][2], dp[length - 1][3]));
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int length = prices.length;

        // 用变量替代dp数组
        int hold = -prices[0];     // 持有股票状态
        int freeze = 0;            // 冷冻期状态
        int sell = 0;              // 卖出状态
        int nonFreeze = 0;         // 不持有股票且不在冷冻期的状态

        for (int i = 1; i < length; i++) {
            int newHold = Math.max(hold, Math.max(nonFreeze - prices[i], freeze - prices[i]));
            int newFreeze = Math.max(freeze, nonFreeze);
            int newSell = hold + prices[i];
            int newNonFreeze = sell;

            hold = newHold;
            freeze = newFreeze;
            sell = newSell;
            nonFreeze = newNonFreeze;
        }

        return Math.max(freeze, Math.max(sell, nonFreeze));
    }

    @Test
    public void testMaxProfit() {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit2(prices));
    }
}
