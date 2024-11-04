package com.ben.followcarl.c4_string;

import org.junit.Test;

import java.util.Arrays;

/**
 * @description
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 *
 *
 * Example 1:
 *
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * Example 2:
 *
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 * @author benjieqiang
 * @date 2024/8/5 7:05 PM
 */
public class L6_28_strStr {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (n < m || m == 0) return -1;
        int[] next = new int[m]; // next数组用来存放模式串的前缀表，也就是每个字符串对应的最长相等前后缀。
        getNext(next, needle); // 获取模式串的前缀表

        int j = 0;
        for (int i = 0; i < n; i++) {
            //i从0开始遍历文本串，j从0到m进行遍历，如果i和j字符不等，那就j往前跳；
            while (j > 0 && needle.charAt(j) != haystack.charAt(i))
                j = next[j - 1];
            if (needle.charAt(j) == haystack.charAt(i))
                //i和j对应的字符一样，i和j同时向后加，直到要么到模式串的长度，
                // 要么找到一个不等的字符，那么就需要执行上面while的逻辑
                j++;
            if (j == m) // 举例：a, a. i = 0, m = 1
                return i - m + 1;
        }
        return -1;
    }

    private void getNext(int[] next, String s) {
        //1. 初始化，i指向后缀末尾，j指向当前匹配的最长相等前后缀的末尾位置。
        int j = 0; //第一个字符串的最长相等前后缀肯定为0，原因看最长相等前后缀的定义。
        next[0] = 0;
        //2. 循环遍历模式字符串，来更新next[i]的值，next[i]表示的就是i之前的字符串的最长相等前后缀；
        for (int i = 1; i < s.length(); i++) {
            //3. 当前后缀不同的时候，跳回去直至相同且j>0，此时j根据前一位字符所对应的next[j-1]的值来决定会退几步。
            while (j > 0 && s.charAt(j) != s.charAt(i)) {
                j = next[j - 1];
            }
            // 4. 前后缀相同，更新next数组
            if (s.charAt(j) == s.charAt(i))
                j++;
            next[i] = j;
        }
    }

    public int strStr1(String haystack, String needle) {
        // 获取两个字符串的长度
        int haystackLength = haystack.length();
        int needleLength = needle.length();

        // 如果 needle 是空字符串，返回 0
        if (needleLength == 0) return 0;

        // 遍历 haystack，尝试在每个位置开始匹配 needle
        for (int i = 0; i <= haystackLength - needleLength; i++) {
            // 检查从 i 开始的子串是否与 needle 匹配
            int j;
            for (j = 0; j < needleLength; j++) {
                // haystack.charAt(i + j): 表示 haystack 中从位置 i 开始向后偏移 j 的字符。
                //needle.charAt(j): 表示 needle 中位置 j 的字符。
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break; // 如果不匹配，退出当前匹配尝试
                }
            }
            // 检查内层循环是否完成并且j等于needle的长度。 说明完全匹配
            if (j == needleLength) {
                return i;
            }
        }

        // 如果没有匹配成功，返回 -1
        return -1;
    }
    @Test
    public void test_Str() {
        String haystack = "aabaabaaf";
        String needle = "aabaaf";

//        int i = strStr(haystack, needle);
        int[] next = new int[needle.length()];
        getNext(next, needle);
        System.out.println(Arrays.toString(next));
//        int i2 = strStr1(haystack, needle); // O((n -m) * m)
//        System.out.println(i2);

    }

}
