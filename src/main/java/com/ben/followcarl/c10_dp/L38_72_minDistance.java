package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-24  13:38
 * @Description: 1、dp[i][j] 表示以i-1结尾的word1和以j-1结尾的word2，最少操作dp[i][j]步才能相等；
 * 这里的操作包括：不操作，增删换；
 * 2、dp公式
 * if (word1[i - 1] == word2[j - 1])
 *     不操作操作，就是前一个元素i-2和j-2的结尾的word1和word2。最少的操作次数为保持上一步的dp值；dp[i][j] = dp[i - 1][j - 1]
 * if (word1[i - 1] != word2[j - 1])
 *     word1 = "horse",  horse ->换h为r： rorse ->删r： rose -> 删e：ros
 *     word2 = "ros"; ros -》增e： rose -》增r： rorse -》换r： horse
 *     增和删操作是相对的，抛开更换操作，word1删两次就能变成word2，word2增加两次就能变成word1；
 *     dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
 *     增
 *     删: dp[i - 1][j] + 1和dp[i][j - 1] + 1都是分别不考虑word1当前的元素和word2当前的元素时再进一步操作一步增加或删除就能得到相同的元素
 *     比如：ab和a，a和ab
 *     换: 比如ab和ac，要替换b为c才能保证word1与word2相同，那么b，c的前一个元素是a，说明得在相同元素的基础上增加一个删除操作就能得到
 *     dp[i - 1][j - 1] + 1
 * 3、
 * 4、i从1到word1.length(), j从1到word2.length()
 * @Version: 1.0
 */
public class L38_72_minDistance {
    public int minDistance(String word1, String word2) {
        if (word2.length() > word1.length()) return 0;
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) dp[i][0] = i;
        for (int j = 0; j <= len2; j++) dp[0][j] = j;
        for (int[] res : dp) {
            System.out.println(Arrays.toString(res));
        }
        System.out.println("初始化完成");
        for (int i = 1; i <= len1; i++) {
            char ch1 = word1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char ch2 = word2.charAt(j - 1);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }

                for (int[] res : dp) {
                    System.out.println(Arrays.toString(res));
                }
                System.out.println("第i次"+i);
            }
        }
        return dp[len1][len2];
    }
    @Test
    public void testMinDistance() {
        String word1 = "horse", word2 = "ros"; // 3

        System.out.println(minDistance(word1, word2));


    }

}
