package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-19  10:06
 * @Description:
 *
 * 101. 孤岛的总面积
 * 题目描述
 * 给定一个由 1（陆地）和 0（水）组成的矩阵，岛屿指的是由水平或垂直方向上相邻的陆地单元格组成的区域，且完全被水域单元格包围。孤岛是那些位于矩阵内部、所有单元格都不接触边缘的岛屿。
 *
 *
 *
 * 现在你需要计算所有孤岛的总面积，岛屿面积的计算方式为组成岛屿的陆地的总数。
 *
 * 输入描述
 * 第一行包含两个整数 N, M，表示矩阵的行数和列数。之后 N 行，每行包含 M 个数字，数字为 1 或者 0。
 * 输出描述
 * 输出一个整数，表示所有孤岛的总面积，如果不存在孤岛，则输出 0。
 * 输入示例
 * 4 5
 * 1 1 0 0 0
 * 1 1 0 0 0
 * 0 0 1 0 0
 * 0 0 0 1 1
 * 输出示例
 * 1
 *
 * 思路：
 * 1. 还是bfs的框架，
 * 上边，下边，左边，右边开始，就把遇到的岛屿都淹没成0；这样剩下的就是孤岛，最后遍历整个数组就是孤岛总面积；
 * 2. 上边：0行i列，下边：m-1行i列；左边：i行0列；右边：i行n-1列；
 * 3. 进入dfs就把当前的小岛给赋值成0；
 * @Version: 1.0
 */

import java.util.*;

public class L4_lonelyIslandArea_dfs {


    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void dfs(int[][] grid, int x, int y) {
        grid[x][y] = 0;

        for (int k = 0; k < 4; k++) {
            int nextx = x + dir[k][0];
            int nexty = y + dir[k][1];

            if (nextx < 0 || nextx >= grid.length
                    || nexty < 0 || nexty >= grid[0].length)
                continue;
            if (grid[nextx][nexty] == 1)
                dfs(grid, nextx, nexty);
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] grid = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1) dfs(grid, 0, i);
            if (grid[m - 1][i] == 1) dfs(grid, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][n - 1] == 1) dfs(grid, i, n - 1);
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) count++;
            }
        }
        System.out.println(count);
    }
}
