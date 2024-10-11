package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-17  16:38
 * @Description: 股票只能买卖两次； hard
 * @Version: 1.0
 */
public class L25_123_maxProfit {
    // dp
    /*
      1. dp数组的含义 定义5种状态;
      dp[i][0] 表示第i天持有的最大现金，不操作，
      dp[i][1] 表示第一次持有：在前i-1天就持有了，到了第i天不进行买入操作 + 到第i天才真正的买入了股票。
      dp[i][2] 表示第一次不持有：在前i-1天就卖了，到了第i天不操作 + 前i-1天持有，等到第i天才进行卖出股票；
      dp[i][3] 表示第二次持有；在前i-1天就完成了买入和卖出操作，一直维持到第i天+ 到了第i天先卖出第一次持有的股票进行第二次买入
      dp[i][4] 表示第二次不持有：在前i-1天完成了两次买入卖出的操作 + 到第i天先买入第二次股票，再卖出第二次
      2.
      dp[i][0] = dp[i - 1][0]
      dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
      dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + prices[i]);
      dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] - prices[i]);
      dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3] + prices[i]); //dp[i-1][3]表示到i的时候股票的状态是持有（可能在i-1之前已经加成了买卖买操作维持到现在，也可能到现在只是买卖到i准备卖）
      3. 初始化
      dp[0][0] = 0。第一天啥也不干就是0
      dp[0][1] = -prices[0]; 第一天买入股票花了prices[0]
      dp[0][2] = 0; 第一天买入又卖出,净赚0元;
      dp[0][3] = -prices[0]; 第一天第二次持有的意思，买卖一次再买入, 花了prices[0]的钱；
      dp[0][4] = 0; 买卖两次净赚0元;
      4. 遍历顺序: 从1到length-1;
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[][] dp = new int[prices.length][5];

        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]); // 要么保持前i-1天状态,即买入的状态, 要么前i-1天卖出了,再买入;
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + prices[i]); // 要么保持前i-1天卖出的状态, 要么前i-1天买入了,到今天卖出;
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3] + prices[i]);
        }

        return dp[prices.length - 1][4];
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int buy1 = -prices[0];  // 第一次买入时的收益
        int sell1 = 0;          // 第一次卖出时的收益
        int buy2 = -prices[0];  // 第二次买入时的收益
        int sell2 = 0;          // 第二次卖出时的收益

        for (int i = 1; i < prices.length; i++) {
            // 状态转移
            buy1 = Math.max(buy1, -prices[i]);              // 保持第一次买入，或者在今天买入
            sell1 = Math.max(sell1, buy1 + prices[i]);      // 保持第一次卖出，或者今天卖出
            buy2 = Math.max(buy2, sell1 - prices[i]);       // 保持第二次买入，或者今天买入
            sell2 = Math.max(sell2, buy2 + prices[i]);      // 保持第二次卖出，或者今天卖出
        }

        return sell2;  // 返回完成两次交易后的最大收益
    }


    @Test
    public void testMaxProfit() {
        int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit2(prices));
    }
}
