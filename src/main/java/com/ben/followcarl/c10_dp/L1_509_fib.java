package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-07  00:21
 * @Description: TODO
 * @Version: 1.0
 */
public class L1_509_fib {
    public int fib(int n) {
        if (n == 0 || n == 1) return n;
        // 1. 确定dp[i]的含义：第i个斐波那契数的值；
        int[] dp = new int[n + 1];
        // 2. 递推公式 dp[i] = dp[i - 1] + dp[i - 2];
        // 3. 初始化dp[0]和dp[1]
        dp[0] = 0;
        dp[1] = 1;
        // 4. 遍历顺序，从前往后一直加
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            // 5. 打印dp数组
            System.out.println(Arrays.toString(dp));
        }

        return dp[n];
    }

    @Test
    public void testFib() {
        int n = 10;
        System.out.println(fib(n));
    }
}
