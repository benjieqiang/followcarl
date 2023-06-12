package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-10  20:57
 * @Description: 1. 站在顶楼应该是比数组的长度大于1，
 * 2. cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。站在0或1下标台阶不花钱，只有跳了一层或两层就会花钱。
 *                               ____
 *                               │ 顶 │
 *                       __20____|___|
 *                __15__│
 *         __10__│
 * __起点__│  0      1      2      3
 *
 * 到达下标i所需的最小花费为dp[i];
 * dp[i] = min(dp[i-1] + cost[i - 1], dp[i-2] + cost[i - 2])
 *
 * dp[0] = 0
 * dp[1] = 0
 * @Version: 1.0
 */
public class L3_746_minCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        // 因为要到顶层，所以遍历到比数组长度要大1的位置
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
            System.out.println(Arrays.toString(dp));
        }
        return dp[cost.length];
    }

    @Test
    public void testClimb() {
        int[] cost = {10,15,20};
        System.out.println(minCostClimbingStairs(cost));
    }
}
