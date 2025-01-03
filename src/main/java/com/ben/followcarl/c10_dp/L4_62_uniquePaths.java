package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-10  21:17
 * @Description:
 * 1. dp[i][j]代表在第i行j列的位置有这么些走法
 * 2. 递推公式： 取决于上面和左面走了多少种走法，加一起就是当前元素的走法 dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
 * 3. 初始化首列和首行容易出现问题: 现在是m行n列
 *      初始化首行: 0到n-1, dp[0][0] dp[0][1]
 *      初始化首列: 0到m-1. dp[m][0]
 * 4. 遍历顺序, 从(1,1)到(m-1, n-1)
 * 5. 举例:
 *
 *
 * 注意: i行j列, 对应遍历顺序m行n列;
 * @Version: 1.0
 */
public class L4_62_uniquePaths {
    // O(m*n)遍历m*n的二维数组；
    public int uniquePaths(int m, int n) {
        if (m == n && n == 1) return m;
        // 1. dp[i][j]的含义，代表在第i行j列的位置有这么些走法；
        // 2. 递推公式： 取决于上面和左面走了多少种走法，加一起就是当前元素的走法 dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
        int[][] dp = new int[m][n];
        // 3. 初始化首行和首列;
        for (int i = 0; i < m; i++) dp[i][0] = 1; // m行0列； 首列
        for (int j = 0; j < n; j++) dp[0][j] = 1; // 0行n列; 首行
        // 4. 从（1，1）位置开始走，到最后；i是从1到m行，j从1到n列；
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * @param m:
     * @param n:
     * @return int
     * @description 降维成一维数组，滚动数组，dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
     * dp[i][j]只和上侧和左侧有关，压缩一下，就是把dp[i]去掉，把和行有关的参数去掉
     * dp[j] = dp[j] + dp[j - 1];
     * O(n)空间
     * @author benjieqiang
     * @date 2024/12/14 10:13 AM
     */
    public int uniquePaths2(int m, int n) {
        // dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        // i [0, m - 1] j [0, n - 1]
        // dp[m - 1][n - 1]
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) dp[i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }
    @Test
    public void testUniquePaths() {
        int m = 3, n = 7;
        System.out.println(uniquePaths(m, n));
    }
}
