package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-11  21:54
 * @Description: dp[i] = max(dp[i], max((i - j) * j, dp[i - j] * j))
 * 重点在递推公式：
 * 1. dp[i]含义，第i个数拆了之后得到的最大乘积
 * 2. 对于数i可以拆成两个数：j和i-j（j从1到i ）
 *          可以拆成三个数及以上：j和dp[i-j]
 * @Version: 1.0
 */
public class L6_343_integerBreak {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        // 结果是dp[n]所以需要遍历到n
        for (int i = 3; i <= n ; i++) {
            // j <=i/2是因为从1*3和3*1一样，只需要计算前半段的切法；
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
            }
        }
        return dp[n];
    }

    //数学方法，求函数y=(n/x)^x(x>0)的最大值，可得x=e时y最大，但只能分解成整数，故x取2或3，
    // 由于6=2+2+2=3+3，显然2^3=8<9=3^2,故应分解为多个3
    public int integerBreak2(int n) {
        if(n == 2)
            return 1;
        if(n == 3)
            return 2;
        int a = 1;
        while(n > 4){
            n = n - 3;
            a = a * 3;
        }
        return a * n;
    }
    @Test
    public void testIntegerBreak() {
        System.out.println(integerBreak2(10));
    }
}
