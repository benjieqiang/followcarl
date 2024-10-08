package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-17  16:38
 * @Description: 股票只能买卖一次
 * @Version: 1.0
 */
public class L23_121_maxProfit {

    /**
     * @param prices:
     * @return int
     * @description 暴力解法: 以每一个股票为起点, 遍历剩下的元素, 谁的差值最大就是最大的利润;
     * O n^2的复杂度;
     * @author benjieqiang
     * @date 2023/8/21 1:48 PM
     */
    public int maxProfit0(int[] prices) {
        if (prices == null || prices.length == 1) return 0;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                res = Math.max(res, prices[j] - prices[i]);
            }
        }
        return res == Integer.MIN_VALUE ? 0 : res;
    }

    // 贪心算法：
    // 局部最优：从左到右开始遍历数组: 找到最小价格那天买，最大价格那天卖出, 最大利润就是: max-low放入res中;
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
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
      1. 确定dp数组的含义: 用二维数组来表示持有和不持有第i天的股票所拥有的最大现金；
      1.1 dp[i][0] 持有(不代表第i天买入)股票最大金额：表示在[0,i]这个范围内，我可以任选一天买入这个股票，如果在第i天买
      股票，那么说明在0-i-1这几天里啥也没动，一直放着，手上现金一直为0；买入股票就会花-prices[i];
      1.2 dp[i][1] 不持股票最大金额：表示在[0,i]这个范围内，任意一天把卖股票，可以在第i天卖这个股票，
      也可以在i天之前就卖了，那么一直保持这个卖之后的手上现金的状态。

      结果在dp[len - 1][1]中最大值, 表示的是最后一天不持有该股票手上所拥有的现金.
      2. 确定递推公式:
      dp[i][0] = max(dp[i-1][0], -prices[i])
       - dp[i-1][0]表示在第i天不买股票，那就维持前一天手上所持有的金额
       - -prices[i]表示在第i天买股票，-prices[i]。只能买一次，手上现金只有0元，所以买入就相当于花了-prices[i];
      dp[i][1] = max(dp[i-1][1], dp[i-1][0] + prices[i])
       - dp[i-1][1] 第i天不卖股票继续维持前一天状态，那么肯定在0-i-1这个区间把股票已经卖了；
       - dp[i-1][0] + prices[i] 在前i-1天持有股票, 到第i天卖赚了prices[i];

      3. 初始化: 看递推公式从哪里推出来;
      dp[0][0] = -prices[0]; // 第0天持有这个股票，买的话得花-prices[0]的钱，
      dp[0][1] = 0；// 第0天卖股票，还没买股票怎么能卖呢？所以是0；
      4. 遍历顺序:
      i => [0, len]

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
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]); // 前i-1天持有维持到第i天 + 第i天才买入;
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]); // 前i-1天不持有持续到第i天 + 前i-1天一直持有直到第i天卖出
        }
        return dp[length - 1][1];
    }
    @Test
    public void testMaxProfit() {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit0(prices));
        System.out.println(maxProfit2(prices));
    }
}
