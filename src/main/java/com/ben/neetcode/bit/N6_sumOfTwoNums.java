package com.ben.neetcode.bit;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-02  10:57
 * @Description:
 * a + b = 进位 + 无进位
 * 6 + 4 =
 * 0110
 * 0100
 * 1010  = 10
 * 进位：1000 =》 (a ^ b) << 1
 * 不进位：0010 =》 a = a ^ b
 * 相加就是和：1010
 * b == c != 0, c =
 * @Version: 1.0
 */
public class N6_sumOfTwoNums {
    public int getSum(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1; // 进位；
            a = a ^ b; // 无进位
            b = c; // b = 进位；
        }
        return a;
    }
}
