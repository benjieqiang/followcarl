package com.ben.followcarl.c4_string;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-25  23:36
 * @Description: 反转字符串
 *
 *
 * 异或操作有以下性质：
 *
 * 任何数与自己异或的结果都是0，即 x ^ x = 0。
 * 0 与任何数异或的结果都是这个数本身，即 0 ^ x = x。
 * 异或操作满足交换律，即 a ^ b = b ^ a。
 * 下面是代码的解释：
 *
 * 1. `s[i] ^= s[j];`：这行代码将数组 `s` 中的第 `i` 个元素与第 `j` 个元素进行异或操作。这将导致 `s[i]` 的值变为 `s[i] ^ s[j]`。
 *
 * 2. `s[j] ^= s[i];`：接下来，它将数组 `s` 中的第 `j` 个元素与第 `i` 个元素进行异或操作。这将导致 `s[j]` 的值变为 `s[j] ^ (s[i] ^ s[j])`。由于异或操作是可交换的，s[j] ^ s[j] = 0, 0 ^ s[i] = s[i], 即s[j] = s[i]。
 *
 * 3. `s[i] ^= s[j];`：最后，它再次将数组 `s` 中的第 `i` 个元素与第 `j` 个元素进行异或操作。这将导致 `s[i]` 的值变为 `(s[i] ^ s[j]) ^ s[j]`。由于异或操作是自反的（即 `x ^ x` 等于0），这将导致 `s[i]` 的值变为 `s[j]`。
 *
 * 这样，经过这三行代码的操作，数组 `s` 中的第 `i` 个元素和第 `j` 个元素的值完成了交换，而不需要额外的临时变量。这是一种巧妙的交换方法，但要注意，如果 `i` 和 `j` 相同，就会导致元素变为0，因为 `x ^ x` 等于0。所以在实际使用时，需要确保 `i` 和 `j` 不相同。
 * @Version: 1.0
 */
public class L1_344_reverseString {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) { // 为什么不用left <= right，当s为奇数长度时，最后left=right，此时属于无效交换，所以不需要相等；
            // 当s为长度是偶数时， 自然left和right直接交换，之后不满足条件跳出while循环；
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
