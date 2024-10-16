package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-17  16:38
 * @Description: 买卖多次, 股票卖出有手续费，求最大利润
 * @Version: 1.0
 */
public class L28_714_maxProfit {
    // dp
    /* 1. dp数组含义
        dp[i][0]: 持有股票的状态。
        dp[i][1]：不持有股票的状态
       2. 递推公式
        dp[i][0] = max(dp[i-1][0], dp[i - 1][1] - prices[i])
            dp[i-1][0]表示在第i天不买股票，那就维持前一天手上所持有的金额
            dp[i - 1][1] - prices[i]]， 前一天不持有股票的状态，减去第i天买股票花的钱prices[i]
        dp[i][1] = max(dp[i-1][1], dp[i-1][0] + prices[i] - fee)
            dp[i-1][1] 第i天不卖股票继续维持前一天状态，那么肯定在0-i-1这个区间把股票已经卖了；
            dp[i-1][0] + prices[i] 前i-1天一直持有该股票，到第i天卖赚了prices[i]的钱减去利润

        3. 初始化:
            dp[0][0] = -prices[0];
            dp[0][1] = 0;  一买一卖没赚钱;//一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
         4. 遍历顺序:
            i => [1,len)
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

    public int maxProfit2(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int length = prices.length;
        //hold, nothold
        int hold = -prices[0];
        int nothold = 0;
        for (int i = 1; i < length; i++) {
            hold = Math.max(hold, nothold - prices[i]);
            nothold = Math.max(nothold, hold + prices[i] - fee);
        }
        return nothold;
    }
    @Test
    public void testMaxProfit() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit(prices, 2));
    }
}
