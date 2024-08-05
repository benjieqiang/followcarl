package com.ben.followcarl.c4_string;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-10-07  20:58
 * @Description:
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 *
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 *
 * 示例 1：
 *
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 * 示例 2：
 *
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 *
 * 解法：
 * 遍历字符串，每2k个去反转字符串，看剩下的字符串的长度与k相比
 * length - 1 - left 与k
 * 得先转成字符数组，再交换元素，最后new String(数组)
 * @Version: 1.0
 */
public class L2_541_reverseStr {
    class Solution {
        public String reverseStr(String s, int k) {
            char[] cs = s.toCharArray();
            int length = s.length();
            for (int left = 0; left < length; left += 2 * k) {
                // 每2k个翻转前k个，剩余字符判断够不够k个来解决，如果是right = length-1说明剩下的字符串长度小于k，不足k则需要反转；
                // 如果是left + k - 1说明是剩下的字符串长度大于k个，只反转前k个
                int start = left;
                int right = Math.min(length - 1, start + k - 1);
                while (start < right) {
                    char temp = cs[start];
                    cs[start] = cs[right];
                    cs[right] = temp;
                    start ++;
                    right --;
                }
            }
            return new String(cs);
        }
    }

    class Solution2 {
        /**
         * @param s:
         * @param k:
         * @return String
         * @description 借用异或的交换律和0与任何数异或都是任何数的性质；
         * @author benjieqiang
         * @date 2023/10/7 9:23 PM
         */
        public String reverseStr(String s, int k) {
            char[] cs = s.toCharArray();
            for (int i = 0; i < cs.length; i += 2 * k) {
                int left = i;
                int right = Math.min(cs.length - 1, i + k - 1);
                while (left < right) {
                    cs[left] ^= cs[right];
                    cs[right] ^= cs[left];
                    cs[left] ^= cs[right];
                    left++;
                    right--;
                }
            }
            return new String(cs);
        }
    }

    @Test
    public void testStr() {
        String s = "abcdefg";
        int k = 2;

        System.out.println(new Solution().reverseStr(s, k));
    }

}
