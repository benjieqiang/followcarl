package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-10  21:42
 * @Description: 这道题需要注意的
 * 1. m行n列的二维数组,m 怎么取 m = nums.length; n = nums[0].length;
 * 2. dp数组的含义, 当没有障碍物的时候, dp[i][j]是源于上方和左方的求和;
 * @Version: 1.0
 */
public class L5_63_uniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 比如num[3][2], m = 3, n = 2.
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n -1] == 1) return 0;// 起点或终点有障碍物就为0，走不了；
        // 1. if（obstacleGrid[i][j] == 0）dp[i][j] = dp[i][j - 1] + dp[i - 1][j] 取决于上面和左边的步数；
        int[][] dp = new int[m][n];
        // 2. 初始化还是首行和首列，只不过遇到障碍物，后面就不走了初始化为0；
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) dp[i][0] = 1; //首列
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) dp[0][j] = 1; //首行

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) continue;
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * @param obstacleGrid:
     * @return int
     * @description 有障碍物，从 j=0 开始更新路径数以确保正确性。
     * @author benjieqiang
     * @date 2024/12/14 10:45 AM
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) return 0;
        // if (obstacleGrid[i][j] == 0) dp[i][j] = dp[i - 1][j] + dp[i][j - 1];

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) break;
            dp[i] = 1;
        }
//        System.out.println(Arrays.toString(dp));
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0){
                    dp[j] += dp[j - 1];
                }
            }
        }
//        System.out.println(Arrays.toString(dp));
        return dp[n - 1];
    }

    @Test
    public void testObstacle() {
        int[][] obs = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        System.out.println(uniquePathsWithObstacles2(obs));
    }
    @Test
    public void testObstacle2() {
        int[][] obs = {
                {0,0},
                {1,0},
        };
        System.out.println(uniquePathsWithObstacles2(obs));
    }
}
