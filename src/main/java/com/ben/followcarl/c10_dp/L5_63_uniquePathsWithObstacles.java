package com.ben.followcarl.c10_dp;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-10  21:42
 * @Description: TODO
 * @Version: 1.0
 */
public class L5_63_uniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 比如num[3][2], m = 3, n = 2.
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n -1] == 1) return 0;// 起点或终点有障碍物就为0，走不了；
        // 1. if（dp[i][j] == 0）dp[i][j] = dp[i][j - 1] + dp[i - 1][j] 取决于上面和左边的步数；
        int[][] dp = new int[m][n];
        // 2. 初始化还是首行和首列，只不过遇到障碍物，后面就不走了初始化为0；
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) dp[i][0] = 1; //首列
        for (int j = 1; j < n && obstacleGrid[0][j] == 0; j++) dp[0][j] = 1; //首行

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void testObstacle() {
        int[][] obs = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        System.out.println(uniquePathsWithObstacles(obs));
    }
}
