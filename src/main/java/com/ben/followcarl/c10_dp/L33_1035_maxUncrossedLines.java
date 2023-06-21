package com.ben.followcarl.c10_dp;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-21  18:19
 * @Description: 本质是最长公共子序列问题。
 * @Version: 1.0
 */
public class L33_1035_maxUncrossedLines {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len1][len2];
    }
}
