package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-17  16:38
 * @Description: 股票卖出有手续费，求最大利润
 * @Version: 1.0
 */
public class L28_714_maxProfit {
    // dp
    /*
        dp[i][0]: 持有股票的状态。
        dp[i][1]：表示不持有股票的状态

        dp[i][0] = max(dp[i-1][0], dp[i - 1][1] - prices[i])
        2.1 dp[i-1][0]表示在第i天不买股票，那就维持前一天手上所持有的金额
        2.2 dp[i - 1][1] - prices[i]]， 前一天不持有股票的状态，减去第i天买股票花的钱prices[i]
        dp[i][1] = max(dp[i-1][1], dp[i-1][0] + prices[i] - fee)
        2.3 dp[i-1][1] 第i天不卖股票继续维持前一天状态，那么肯定在0-i-1这个区间把股票已经卖了；
        2.4 dp[i-1][0] + prices[i] 前i-1天一直持有该股票，到第i天卖赚了prices[i]的钱减去利润


     * */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int length = prices.length;
        int[][] dp = new int[length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return dp[length - 1][1];
    }

    @Test
    public void testMaxProfit() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit(prices, 2));
    }
}
