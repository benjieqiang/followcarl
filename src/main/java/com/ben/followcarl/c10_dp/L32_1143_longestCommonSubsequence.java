package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-19  17:26
 * @Description: 最长公共子序列，不要求连续
 * abcde和ace，最长的为3，"ace"
 * 1. dp[i][j]：长度为[0, i - 1]的字符串text1与长度为[0, j - 1]的字符串text2的最长公共子序列为dp[i][j]
 * 2. 递推公式: text1[i - 1] 与 text2[j - 1]
 *      相同: 那么找到了一个公共元素，所以dp[i][j] = dp[i - 1][j - 1] + 1;
 *      不同：比如 text1 = "abc", text2 = "ace"，此时要么比较abc和ac的最长公共子序列，要么比较ab和ace的最长公共子序列。
 *             dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
 * 3. dp[i][0]? text1[0, i-1]和空串的最长公共子序列自然是0，所以dp[i][0] = 0;
 *    dp[0][j]同理也是0；
 *
 *4. 遍历顺序: 双层for循环从1到text1.length(), 1到text2.length()
 *   结果集在哪里放着? dp[text1.length()][text2.length()]
 * @Version: 1.0
 */
public class L32_1143_longestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            char char1 = text1.charAt(i - 1); //取出i-1位置的元素
            for (int j = 1; j <= text2.length(); j++) {
                char char2 = text2.charAt(j - 1); //取出j-1位置的元素
                if (char1 == char2) { //相同则是一起往后退一步的基础上加1；
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else { //不同说明
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }


    /**
     * @param text1:
     * @param text2:
     * @return int
     * @description 递推公式的推导:
     * 如果前一位元素相同,那么dp[i][j]就是在前一个基础上加1
     * 如果不同,那么得求这两个字符串分别退一位 与另一个字符串比较得到的最大公共子串;
     * @author benjieqiang
     * @date 2023/8/22 6:40 PM
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[len1][len2]; // 最后的末尾就是放最大子序列长度的地方;
    }

    @Test
    public void testLongestSubString() {
        String text1 = "abcde", text2 = "ace";
        System.out.println(longestCommonSubsequence(text1, text2));
    }
}
