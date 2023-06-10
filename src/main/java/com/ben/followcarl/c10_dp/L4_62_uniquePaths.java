package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-10  21:17
 * @Description:
 * @Version: 1.0
 */
public class L4_62_uniquePaths {
    public int uniquePaths(int m, int n) {
        // 1. dp[i][j]的含义，代表在第i行j列的位置有这么些走法；
        // 2. 递推公式： 取决于上面和左面走了多少种走法，加一起就是当前元素的走法 dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
        int[][] dp = new int[m][n];
        // 3. 初始化第一列和第一行
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;
        // 4. 从（1，1）位置开始走，到最后；i是从1到m行，j从1到n列；
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void testUniquePaths() {
        int m = 3, n = 7;
        System.out.println(uniquePaths(m, n));
    }
}
