package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-21  22:38
 * @Description:
 * s = "abc", t = "ahbgdc",判断s是否是t的子序列，是
 *
 * 非连续
 * 与1143题区别就是，如果删元素一定是字符串t
 * 1. dp[i][j] 表示以下标i-1为结尾的字符串s，和以下标j-1为结尾的字符串t，相同子序列的长度为dp[i][j]。
 *    s的长度一定小于等于t
 * 2. s[i-1]和t[i-1]比较:
 *      相同: s,t共同后退一步基础上加上1, dp[i -1][j - 1] + 1;
 *      不同: 只能把t往前回退, s是不变的,所以dp[i][j - 1]表示以下标i-1结尾的字符串s和以下标j-2结尾的字符串t,相同的子序列长度为
 * 3. 初始化: dp[i][0]和dp[0][j]都为0; 空串无意义;
 * 4. 遍历顺序: 从1到s.length(),包括末尾吗? 包括,所以定义dp数组长度要加1;
 *            从1到t.length(),
 *            dp[s.length()][t.length()]
 * 5. 带值
 * @Version: 1.0
 */
public class L35_392_isSubsequence {

    /**
     * @param s:
     * @param t:
     * @return boolean
     * @description dp数组[0, i-1]的字符串s和[0, j-1]结尾的字符串t所拥有的最长公共子序列长度;
     * @author benjieqiang
     * @date 2023/8/22 8:09 PM
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) return false;
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for (int i = 1; i <= s.length(); i++) {
            char ch1 = s.charAt(i - 1);
            for (int j = 1; j <= t.length(); j++) {
                char ch2 = t.charAt(j - 1);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[s.length()][t.length()] == s.length();
    }

    /**
     * @param s:
     * @param t:
     * @return boolean
     * @description 双指针解法, 循环两个字符串,遇到相同的字符,就给s的指针加1,否则只前进t的指针,
     * 最后判断s的指针长度是否是所求的s的长度;
     * @author benjieqiang
     * @date 2023/8/22 8:13 PM
     */
    public boolean isSubsequence2(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if (len1 > len2) return false;

        int a = 0, b = 0;
        while (a < len1 && b < len2) {
            if (s.charAt(a) == t.charAt(b)) {
                a++;
            }
            b++;
        }
        return a == len1;
    }
    @Test
    public void testIsSub() {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }
}
