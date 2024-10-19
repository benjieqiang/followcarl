package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-18  10:50
 * @Description:
 * 小岛问题：dfs模板：
 * 1. 先遍历整个二维数组，只要当前坐标没有访问过且是小岛，进入dfs，
 * 2. 进来再给当前坐标标记以访问过
 * 3. 遍历上下左右，首先判断不越界，再符合当前坐标没有访问过且是小岛的条件，才下一层递归；
 * @Version: 1.0
 */

import java.util.*;

public class L2_numIslands_dfs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] grid = new int[m][n];
        // 1. get grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // 2. visited
        boolean[][] visited = new boolean[m][n];

        int res = 0;
        // 3. reverse grid, not visited and island dfs, res++;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    res++;
                    dfs(grid, visited, i, j);
                }
            }
        }

        System.out.println(res);

    }

    static int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    private static void dfs(int[][] grid, boolean[][] visited, int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int curx = x + dir[i][0];
            int cury = y + dir[i][1];

            if (curx < 0 || curx >= grid.length || cury < 0 || cury >= grid[0].length)
                continue;

            if (!visited[curx][cury] && grid[curx][cury] == 1) {
                dfs(grid, visited, curx, cury);
            }
        }
    }
}
