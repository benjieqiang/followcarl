package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-17  16:38
 * @Description: 股票只能买卖一次
 * @Version: 1.0
 */
public class L23_121_maxProfit {
    // 贪心算法：
    // 局部最优：从左到右开始，找到最小的那个数买，买到之后再找能让第i天卖出去的价格-low最大就是所求；
    public int maxProfit(int[] prices) {
        int res = 0;
        int low = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            low = Math.min(low, prices[i]); //找到左边最小的值
            res = Math.max(res, prices[i] - low);
        }
        return res;
    }

    // dp
    /*
      1. 用二维数组来表示持有和不持有第i天的股票所拥有的最大现金；
      1.1 dp[i][0] 持有股票最大金额：表示在[0,i]这个范围内，我可以任选一天买入这个股票，如果在第i天买
      股票，那么说明在0-i-1这几天里啥也没动，一直放着，手上现金一直为0；买入股票就会花-prices[i];
      1.2 dp[i][1] 不持股票最大金额：表示在[0,i]这个范围内，任意一天把卖股票，可以在第i天卖这个股票，
      也可以在i天之前就卖了，那么一直保持这个卖之后的手上现金的状态。
      结果就是dp[len -1][0] 和dp[len - 1][1]中最大值
      2. dp[i][0] = max(dp[i-1][0], -prices[i])
       2.1 dp[i-1][0]表示在第i天不买股票，那就维持前一天手上所持有的金额
       2.2 -prices[i]表示在第i天买股票，-prices[i]。只能买一次，手上现金只有0元，所以买入就相当于花了
            -prices[i];
      dp[i][1] = max(dp[i-1][1], dp[i-1][0] + prices[i])
       2.3 dp[i-1][1] 第i天不卖股票继续维持前一天状态，那么肯定在0-i-1这个区间把股票已经卖了；
       2.4 dp[i-1][0] + prices[i] 第i天卖赚了prices[i]的钱，加上前一天的持有这个股票的现金流

      3. dp[0][0] = -prices[0]; //第一天买不买这个股票，买的话得花-prices[0]的钱，
      dp[0][1] = 0；// 第一天卖股票，还没买股票怎么能卖呢？所以是0；
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int length = prices.length;
        // dp[i][0]代表第i天持有股票的最大收益
        // dp[i][1]代表第i天不持有股票的最大收益
        int[][] dp = new int[length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return dp[length - 1][1];
    }
    @Test
    public void testMaxProfit() {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit2(prices));
    }
}
