package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-24  10:44
 * @Description:
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 * 解法1：
 * 1、dp[i][j] 表示以i-1结尾的word1和以j-1结尾的word2需要删除的最小步数
 *
 * 2、if (word1[i - 1] == word2[j - 1]) dp[i][j] = dp[i - 1][j - 1]
 * 当此时两个元素相同时，不需要删除元素，所以此时的dp表示的最小步数就是上一个位置的最小步数
 *  else dp[i][j] = Math.min(dp[i -1][j] + 1, dp[i][j - 1] + 1)
 * 当两个元素不同时，需要从中删除最小的步数，要么word1后退一步，要么word2后退一步，分别得到一个新的dp
 * 还有一个word1和word2此时的元素都删除，即dp[i-1][j-1] + 2，这个可以被dp[i][j-1] + 1覆盖掉，这个表示的是删除了word2的j-1位置的元素，如果此时要删除word1的i - 1
 * 位置的元素，那么表示为dp[i-1][j-1] + 1 + 1 => 推出dp[i-1][j-1] + 2.同理，也可以由dp[i -1][j] + 1推出。
 * 3、初始化
 * dp[0][j]表示一个空的word1和j长度的word2，需要删除的最小步数，需要把word2全删了，就是j次
 * dp[i][0]表示i长度的word1和空的word2，需要删除的最小步数，删除word1需要i次
 * 所以初始化是首行需要全初始化为j，首列初始化为i
 * 4、i从1到word1.length(),j从1到word2.length();
 * 5、打印dp
 *
 * 解法2： 先求出最长公共子序列len，两个字符串的长度-2*len就是需要删除的最小步数。
 * @Version: 1.0
 */
public class L37_583_minDistance {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        // 一定要到word1和word2的长度，这样dp[i][j]表示为整个word1删除dp次才能得到整    个word2
        for (int i = 0; i <= len1; i++) dp[i][0] = i;
        for (int j = 0; j <= len2; j++) dp[0][j] = j;
        for (int[] res : dp) {
            System.out.println(Arrays.toString(res));
        }
        System.out.println("初始化结束");
        for (int i = 1; i <= len1; i++) {
            char ch1 = word1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char ch2 = word2.charAt(j - 1);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i -1][j] + 1, dp[i][j - 1] + 1);
                }

                for (int[] res : dp) {
                    System.out.println(Arrays.toString(res));
                }
                System.out.println("结束："+ i);
            }
        }
        return dp[len1][len2];
    }

    @Test
    public void testMinDistance() {
        String word1 = "leetcode", word2 = "etco"; //4
//        String word1 = "a", word2 = "b"; // 2
        System.out.println(minDistance(word1, word2));

    }
}
