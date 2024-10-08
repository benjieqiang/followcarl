package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-01  09:48
 * @Description: 股票买卖多次取正值相加就是所求;
 * @Version: 1.0
 */
public class L4_122_maxProfits {
    // 假如第 0 天买入，第 3 天卖出，那么利润为：prices[3] - prices[0]。
    //相当于(prices[3] - prices[2]) + (prices[2] - prices[1]) + (prices[1] - prices[0])。
    // 每天收入正的利润就是所求。
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i - 1];
            if (profit > 0) res += profit;
        }
        return res;
    }


    @Test
    public void testMaxProfit() {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
}
