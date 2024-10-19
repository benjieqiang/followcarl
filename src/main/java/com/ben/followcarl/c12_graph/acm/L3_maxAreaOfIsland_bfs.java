package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-19  09:54
 * @Description: 100. 岛屿的最大面积
 * 题目描述
 * 给定一个由 1（陆地）和 0（水）组成的矩阵，计算岛屿的最大面积。岛屿面积的计算方式为组成岛屿的陆地的总数。岛屿由水平方向或垂直方向上相邻的陆地连接而成，并且四周都是水域。你可以假设矩阵外均被水包围。
 * 输入描述
 * 第一行包含两个整数 N, M，表示矩阵的行数和列数。后续 N 行，每行包含 M 个数字，数字为 1 或者 0，表示岛屿的单元格。
 * 输出描述
 * 输出一个整数，表示岛屿的最大面积。如果不存在岛屿，则输出 0。
 * 输入示例
 * 4 5
 * 1 1 0 0 0
 * 1 1 0 0 0
 * 0 0 1 0 0
 * 0 0 0 1 1
 * 输出示例
 * 4
 * @Version: 1.0
 */

import java.util.*;

public class L3_maxAreaOfIsland_bfs {

    static int count = 0;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void dfs(int[][] grid, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        count++;
        for (int k = 0; k < 4; k++) {
            int curx = i + dir[k][0];
            int cury = j + dir[k][1];

            if (curx < 0 || curx >= grid.length || cury < 0 || cury >= grid[0].length) {
                continue;
            }
            if (!visited[curx][cury] && grid[curx][cury] == 1)
                dfs(grid, visited, curx, cury);
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

        int res = 0; // 利用res统计每一个ij的最大面积；
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    count = 0;
                    dfs(grid, visited, i, j);

                    res = Math.max(res, count);
                }
            }
        }

        System.out.println(res);

    }
}
