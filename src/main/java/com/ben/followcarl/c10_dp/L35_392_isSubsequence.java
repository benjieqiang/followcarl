package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-21  22:38
 * @Description: s = "abc", t = "ahbgdc",判断s是否是t的子序列，是
 * 非连续就行
 * 与1143题区别就是，如果删元素一定是字符串t
 * @Version: 1.0
 */
public class L35_392_isSubsequence {
    public boolean isSubsequence(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if (len1 > len2) return false;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            char ch1 = s.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char ch2 = t.charAt(j - 1);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
                for (int[] res : dp) {
                    System.out.println(Arrays.toString(res));
                }
            }
        }
        return dp[len1][len2] == len1;
    }

    @Test
    public void testIsSub() {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }
}
