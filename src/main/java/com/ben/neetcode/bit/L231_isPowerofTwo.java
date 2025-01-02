package com.ben.neetcode.bit;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-27  12:07
 * @Description: TODO
 * @Version: 1.0
 */
public class L231_isPowerofTwo {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n;
    }
}
