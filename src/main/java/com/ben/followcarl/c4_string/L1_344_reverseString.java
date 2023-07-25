package com.ben.followcarl.c4_string;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-25  23:36
 * @Description: 反转字符串
 * @Version: 1.0
 */
public class L1_344_reverseString {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
        System.out.println(Arrays.toString(s));
    }

    @Test
    public void testReverse() {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
    }
}
