package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-24  21:04
 * @Description: 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * 1. dp[i][j]: 字符串s在[i,j]范围内最长的回文子串长度;
 * 2. 递推公式:
 *   如果s[i] == s[j] dp[i][j] = dp[i+1][j - 1] + 2; // 两头都要加一个字符;
 *   如果s[i] != s[j] s[i]和s[j]的同时加入 并不能增加[i,j]区间回文子序列的长度，那么分别加入s[i]、s[j]看看哪一个可以组成最长的回文子序列
 *   加入s[j]的回文子序列长度为dp[i + 1][j]。 加入s[i]的回文子序列长度为dp[i][j - 1]。
 *
 *  3. 初始化: 当i与j相同时, 最长的回文子串长度就是1, 不同的时候可以计算一下;
 *
 *  4. 遍历顺序: 从左,左下,下可以推出dp数组, 所以i从下往上遍历, j从左往右遍历; 结果集在首行最后一个元素位置;
 * @Version: 1.0
 */
public class L40_516_longestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < s.length(); i++) dp[i][i] = 1;

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }

    @Test
    public void testLongestPalin() {
        String s = "bbbab";
        System.out.println(longestPalindromeSubseq(s));
    }
}
