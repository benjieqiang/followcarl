package com.ben.followcarl.c10_dp;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-20  19:17
 * @Description:
 * 给你两个 正 整数 n 和 x 。
 *
 * 请你返回将 n 表示成一些 互不相同 正整数的 x 次幂之和的方案数。换句话说，你需要返回互不相同整数 [n1, n2, ..., nk] 的集合数目，满足 n = n1x + n2x + ... + nkx 。
 *
 * 由于答案可能非常大，请你将它对 10^9 + 7 取余后返回。
 *
 * 比方说，n = 160 且 x = 3 ，一个表示 n 的方法是 n = 23 + 33 + 53 。
 *
 * 从1^x, 2^x, n^x的物品中选物品,装满容量为n的背包的方法数
 *
 * @Version: 1.0
 */
public class L11_2787_numberOfWays {
    public int numberOfWays(int n, int x) {
        int[] dp = new int[n + 1]; //
        dp[0] = 1;
        int v = 0;
        int mod = 1000000007;
        for (int i = 1; (v = (int) Math.pow(i, x)) <= n; i++) {
            for (int j = n; j >= v; j--) {
                dp[j] = (dp[j] + dp[j - v]) % mod;
            }
        }

        return dp[n];
    }
}

