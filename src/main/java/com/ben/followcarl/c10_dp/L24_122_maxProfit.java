package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-17  16:38
 * @Description: 股票可以买卖多次；
 * @Version: 1.0
 */
public class L24_122_maxProfit {

    /**
     * @param prices:
     * @return int
     * @description 贪心算法, 不断寻找正区间, 最后的和就是所求买卖多次得到的最大利润;
     * @author benjieqiang
     * @date 2023/8/21 3:00 PM
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1) return 0;
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) res += prices[i] - prices[i - 1];
        }
        return res;
    }

    // dp
    /*
      1. dp数组的定义: 用二维数组来表示持有和不持有第i天的股票所拥有的最大现金；
      1.1 dp[i][0] 持有股票最大金额：表示在[0,i]这个范围内任选一天买入这个股票，
            两种情况：前i-1天一直持有+前i-1天任意一天卖出，第i天买入
                买入股票就会花 dp[i - 1][1] - prices[i];
                dp[i - 1][1]: 前i-1任意一天卖出股票：如果在第i天不买股票,那就说明在前i-1天去买了股票,持有到现在;
                - prices[i]： 如果在第i天买股票，那么说明在0-i-1这几天里啥也没动，一直放着，手上现金不再是0了，而是第i-1天不持有股票的最大现金(因为可以买卖多次)。
      1.2 dp[i][1] 不持股票最大金额：
            d
          表示第i天卖出, 在[0,i]这个范围内，任意一天买入了股票在第i天卖出股票，dp[i - 1][0] + prices[i]
          也可以在第i天之前就卖了，那么一直保持这个卖之后的手上现金的状态。dp[i - 1][1]

      2. 递推公式: 结果返回最后一天不持有股票的最大金额: dp[length - 1][1];
        dp[i][0] = max(dp[i-1][0], dp[i - 1][1] - prices[i])
           2.1 dp[i-1][0]： 第i天不买股票， 维持前一天手上所持有的金额，第i天不买股票；
           2.2 dp[i - 1][1] - prices[i]]， 第i天买股票，前一天不持有股票的状态，减去第i天买股票花的钱prices[i]
         dp[i][1] = max(dp[i-1][1], dp[i-1][0] + prices[i])
           2.3 dp[i-1][1] 第i天不卖股票继续维持前一天状态，那么肯定在0-i-1这个区间把股票已经卖了；
           2.4 dp[i-1][0] + prices[i] 前i-1天一直持有该股票，到第i天卖赚了prices[i]的钱

      3. 初始化:
         dp[0][0] = -prices[0]; //第一天买不买这个股票，买的话得花-prices[0]的钱，
         dp[0][1] = 0；// 第一天卖股票，还没买股票怎么能卖呢？所以是0；
      4. 遍历顺序: i => [1, length)
     */

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 1) return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0]; // 第0天买入,利润为-prices[0];
        dp[0][1] = 0; //第0天先买后卖,利润为0
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }

    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int hold = -prices[0];
        int nothold = 0;
        for (int i = 1; i < prices.length; i++) {
            hold = Math.max(hold, nothold - prices[i]);
            nothold = Math.max(nothold, hold + prices[i]);
        }

        return nothold;
    }
    @Test
    public void testMaxProfit() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit2(prices));
        System.out.println(maxProfit3(prices));
    }
}
