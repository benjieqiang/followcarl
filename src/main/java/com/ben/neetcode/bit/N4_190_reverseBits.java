package com.ben.neetcode.bit;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-27  11:41
 * @Description: Reverse bits of a given 32 bits unsigned integer.
 * @Version: 1.0
 */
public class N4_190_reverseBits {
    // 1. 求n的最低位，2.如果是1则移动到31-i的位置；
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int bit = (n >> i) & 1;  // 获取 n 中第 i 位的值（0 或 1）
            if (bit == 1) res += (bit << (31 - i));
            // 将 bit 左移 (31 - i) 位。由于 bit 只有两种可能：0 或 1，左移 (31 - i) 位相当于把 bit 放到 31 - i 这个位置
        }
        return res;
    }
}
