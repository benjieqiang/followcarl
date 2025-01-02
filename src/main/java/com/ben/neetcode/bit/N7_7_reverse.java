package com.ben.neetcode.bit;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-02  10:41
 * @Description: 整数反转
 * @Version: 1.0
 */
public class N7_7_reverse {
    public int reverse(int x) {
        // [-2^23, 2^23-1] 2147483647
        // 2147483647 <= res*10 + digit <= 2147483647
        int res = 0;
        while (x != 0) {
            if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10; // 求余
            x /= 10; // 移动x
            res = res * 10 + digit;
        }
        return res;
    }

    @Test
    public void test_value() {


        System.out.println(Integer.MAX_VALUE); // 2147483647
        System.out.println(Integer.MIN_VALUE); //-2147483648
    }
}
