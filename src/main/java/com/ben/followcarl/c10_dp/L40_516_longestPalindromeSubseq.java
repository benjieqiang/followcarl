package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-24  21:04
 * @Description: 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * @Version: 1.0
 */
public class L40_516_longestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < s.length(); i++) dp[i][i] = 1;
        for (int[] res : dp) {
            System.out.println(Arrays.toString(res));
        }
        System.out.println("初始化完成");

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
            for (int[] res : dp) {
                System.out.println(Arrays.toString(res));
            }
            System.out.println("****");
        }
        return dp[0][len - 1];
    }

    @Test
    public void testLongestPalin() {
        String s = "bbbab";
        System.out.println(longestPalindromeSubseq(s));
    }
}
