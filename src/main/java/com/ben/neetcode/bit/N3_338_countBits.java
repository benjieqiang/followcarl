package com.ben.neetcode.bit;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-27  11:33
 * @Description: TODO
 * @Version: 1.0
 */
public class N3_338_countBits {
    // 调用库函数，从1开始，0的二进制就是0，所以不用计算；
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            result[i] = Integer.bitCount(i);
        }
        return result;
    }

    // 思路和192一样，自己实现countbit计算方法；
    public int[] countBits2(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num != 0) {
                res[i]++;
                num &= (num - 1);
            }
        }
        return res;
    }

    // dp
    public int[] countBits3(int n) {
        // dp[i]表示i的二进制中1的个数
        // dp[i-1] 表示bit[i]去掉一个1之后的值，
        // i & (i - 1)就是去掉最低位的一个1.
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i & (i - 1)] + 1;
        }
        return dp;
    }
}

