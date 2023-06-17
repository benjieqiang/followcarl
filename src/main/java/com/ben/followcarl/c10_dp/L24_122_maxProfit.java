package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-17  16:38
 * @Description: 股票可以买卖多次；
 * @Version: 1.0
 */
public class L24_122_maxProfit {
    // 贪心算法：寻找正数区间和

    // dp
    /*
      1. 用二维数组来表示持有和不持有第i天的股票所拥有的最大现金；
      1.1 dp[i][0] 持有股票最大金额：表示在[0,i]这个范围内，我可以任选一天买入这个股票，如果在第i天买
          股票，那么说明在0-i-1这几天里啥也没动，一直放着，手上现金不再是0了，而是第i-1天不持有股票的最大现金。买入股票就会花 dp[i - 1][1] - prices[i];
      1.2 dp[i][1] 不持股票最大金额：表示在[0,i]这个范围内，任意一天买入了股票在第i天卖出股票，
          也可以在第i天之前就卖了，那么一直保持这个卖之后的手上现金的状态。
          结果就是dp[len -1][0] 和dp[len - 1][1]中最大值
      2. dp[i][0] = max(dp[i-1][0], dp[i - 1][1] - prices[i])
           2.1 dp[i-1][0]表示在第i天不买股票，那就维持前一天手上所持有的金额
           2.2 dp[i - 1][1] - prices[i]]， 前一天不持有股票的状态，减去第i天买股票花的钱prices[i]
         dp[i][1] = max(dp[i-1][1], dp[i-1][0] + prices[i])
           2.3 dp[i-1][1] 第i天不卖股票继续维持前一天状态，那么肯定在0-i-1这个区间把股票已经卖了；
           2.4 dp[i-1][0] + prices[i] 前i-1天一直持有该股票，到第i天卖赚了prices[i]的钱

      3. dp[0][0] = -prices[0]; //第一天买不买这个股票，买的话得花-prices[0]的钱，
         dp[0][1] = 0；// 第一天卖股票，还没买股票怎么能卖呢？所以是0；
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int length = prices.length;
        int[][] dp = new int[length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return dp[length - 1][1];
    }

    @Test
    public void testMaxProfit() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit2(prices));
    }
}
