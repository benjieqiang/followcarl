package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-19  09:54
 * @Description: #L5_695_maxAreaOfIsland
 * @Version: 1.0
 */
import java.util.*;

public class L3_maxAreaOfIsland_dfs {

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
