package com.ben.neetcode.math;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-19  09:01
 * @Description:
 * n & 1 是按位与操作。二进制中，判断一个数是否为奇数，只需要看它的最低位（最低有效位，Least Significant Bit, LSB）：
 *
 * 如果最低位是 1，则 n 是奇数。
 * 如果最低位是 0，则 n 是偶数。
 * 例如：
 *
 * 奇数：5 的二进制是 101，5 & 1 得到 1。
 * 偶数：4 的二进制是 100，4 & 1 得到 0。
 *
 *
 * @Version: 1.0
 */
public class N6_50_power {
    // O(n), O(1)
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;
        double res = 1;
        for (int i = 1; i <= Math.abs(n); i++) {
            res *= x;
        }
        return n < 0 ? 1/res : res;
    }

    // recursive O(logn) O(logn)
    public double myPow2(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;

        double res = helper(x, Math.abs((long) n));

        return (n < 0) ? 1/res : res;
    }
    private double helper(double x, long n) {
        if (n == 0) return 1;
        double half = helper(x, n/2);
        double res = half * half;
        return n % 2 == 0 ? res : x * res;
    }

    // Ologn, O1
    public double myPow3(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;
        // n = -2^31 取反 越界，所以使用转型long；
        long power = Math.abs((long)n);
        double res = 1;
        while (power > 0) { // 从低到高枚举 power 的每个比特位
            if ((power & 1) == 1) res *= x; // power是奇数，把 x 乘到 res 中
            x *= x; // x 自身平方
            power >>= 1; // 继续枚举下一个比特位
        }
        return n < 0 ? 1/res : res;
    }
}
