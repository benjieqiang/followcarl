package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-07  00:21
 * @Description:
 * 1. 确定dp[i]的含义：第i个斐波那契数的值；int[] dp = new int[n + 1]; 举例: n为2, dp[2]表示第2个fib, 如果数组长度为2, 取不到dp[2].所以加1
 * 2. 递推公式题目已给;
 * 3. dp[0]和dp[1]题目也给了
 * 4. 遍历顺序,显然从小到大;
 * 5. 举例, dp[2]表示n=2时fib数, dp[2] = dp[1] + dp[0]. 那么应该返回dp[2], n 返回dp[n]
 * @Version: 1.0
 */
public class L1_509_fib {
    public int fib(int n) {
        if (n == 0 || n == 1) return n;
        // 1. 确定dp[i]的含义：第i个斐波那契数的值；
        int[] dp = new int[n + 1]; // 举例: n为2, dp[2]表示第2个fib, 如果数组长度为2, 取不到dp[2].所以加1
        // 2. 递推公式 dp[i] = dp[i - 1] + dp[i - 2];
        // 3. 初始化dp[0]和dp[1]
        dp[0] = 0;
        dp[1] = 1;
        // 4. 遍历顺序，从前往后一直加
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
//            // 5. 打印dp数组
//            System.out.println(Arrays.toString(dp));
        }

        return dp[n];
    }
    /**
     * @param n:
     * @return int
     * @description 优化空间复杂度为o1
     * @author benjieqiang
     * @date 2023/8/20 10:03 AM
     */
    int fib3(int n) {
        if (n == 0 || n == 1) return n;
        int dp1 = 0;
        int dp2 = 1;
        for (int i = 2; i <= n; i++) {
            int sum = dp1 + dp2;
            dp1 = dp2;
            dp2 = sum;
        }
        return dp2;
    }


    /**
     * @param n:
     * @return int
     * @description 递归解法:
     * 1. 递归函数的入参和返回值;
     * 2. 终止条件
     * 3. 单层递归逻辑, 计算n-1时的fib 和n-2时的fib
     * @author benjieqiang
     * @date 2023/8/18 10:19 PM
     */
    int fib2(int n) {
        if (n < 2) return n;
        return fib2(n - 1) + fib2(n - 2);
    }
    @Test
    public void testFib() {
        int n = 10;
        System.out.println(fib(n));
    }
}
