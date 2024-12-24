package com.ben.neetcode.string;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-24  10:47
 * @Description:
 * A palindrome is a string that reads the same forward and backward
 * case-insensitive and ignores all non-alphanumeric characters
 *
 * "A man, a plan, a canal: Panama"
 * 忽略大小写，忽略非字母和数字字符，比如忽略空格，符号。
 * 考察api：
 * 大小写字符转换
 * Character.isLetterOrDigit(char ch)；
 * Character.toLowerCase()转小写；
 * 也可以直接把s先转成小写，s.toLowerCase().toCharArray();
 * 再遍历；
 * @Version: 1.0
 */
public class N1_125_isPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return false;
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            char cl = Character.toLowerCase(s.charAt(left));
            char cr = Character.toLowerCase(s.charAt(right));
            if (cl != cr) return false;
            left++;
            right--;
        }
        return true;
    }

    public boolean isLetterOrDigit(char c) {
        return (c >= 'A' && c <= 'Z' ||
                c >= 'a' && c <= 'z' ||
                c >= '0' && c <= '9');
    }

    public boolean isPalindrome2(String s) {
        if (s == null || s.length() == 0) return false;
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (!Character.isLetterOrDigit(ch)) continue;
            sb.append(Character.toLowerCase(ch));
        }
        return sb.toString().equals(sb.reverse().toString());
    }

    @Test
    public void test_isPalindrome() {
//        String s = "race a car";
        String s = "A man, a plan, a canal: Panama";
        boolean palindrome = isPalindrome(s);
        boolean palindrome2 = isPalindrome2(s);
        System.out.println(palindrome);
        System.out.println(palindrome2);

    }

}
