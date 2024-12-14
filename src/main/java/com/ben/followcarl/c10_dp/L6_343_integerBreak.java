package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-11  21:54
 * @Description:
 * 重点在递推公式：
 * 1. dp[i]含义，第i个数拆后得到的最大乘积
 * 2. 首先数i可以拆成两个数：整数j和整数i-j（j从1到i )
 *    dp[i]这个最大乘积可以由两方面得到, 一方面是2个数相乘j* (i - j) (表示i-j不能拆), 一方面是两个以及两个以上的相乘j * dp[i - j](表示i-j这个数的最大乘积)
 *    dp[i] = max(dp[i], max((i - j) * j, dp[i - j] * j))
 *    j为何不拆? j从1开始遍历到i, j如果拆的话,dp[j] 表示要计算dp[1],dp[2],dp[i]等;
 *    dp[i]在计算过程中已经把dp[1],dp[2]...dp[i]计算过了，所以不需要拆j
 *    取最大值的时候，为什么还要比较dp[i]呢？因为在递推公式推导的过程中，每次计算dp[i]，取最大的而已。
 *
 * 3. 初始化, n从2开始,所以n=2时,dp[2] = 1
 * 4. 遍历顺序: i从3到n开始,j从1到i/2;
 * 1 5
 * 2 4
 * 3 3
 * 4 2
 * 5 1
 * 对于一个整数 i 的分割，分割点 j 和 (i - j) 的乘积是对称的。例如：
 * 如果 i = 6，j = 2 时，分割为 (6 - 2) × 2；
 * 如果 j = 4，分割为 (6 - 4) × 4；
 * 这两种情况是等价的，因此只需要计算到一半即可。
 *
 * 所以说j只需遍历前半段就能得到从1到6的最大乘积是多少了;
 * 最后结果要返回dp[n], 所以定义数组要定义成dp[n + 1]

 * @Version: 1.0
 */
public class L6_343_integerBreak {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        // 结果是dp[n]所以需要遍历到n
        for (int i = 3; i <= n; i++) {
            // j <=i/2是因为从1*3和3*1一样，只需要计算前半段的切法；
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
            }
        }
        return dp[n];
    }

    //数学方法，求函数y=(n/x)^x(x>0)的最大值，求二次导，可得x=e时y最大，但只能分解成整数，故x取2或3，
    // 由于6=2+2+2=3+3，显然2^3=8<9=3^2,故应分解为多个3
    public int integerBreak2(int n) {
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        int a = 1;
        while (n > 4) {
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
