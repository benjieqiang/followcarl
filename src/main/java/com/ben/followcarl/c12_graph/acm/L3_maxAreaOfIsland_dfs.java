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
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

    private static void bfs(int[][] grid, boolean[][] visited, int i, int j) {
        Deque<int[]> queue = new LinkedList<>();

        queue.add(new int[]{i,j});
        visited[i][j] = true;

        count++;

        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int curx = cur[0];
            int cury = cur[1];

            for (int k = 0; k < 4; k++) {
                int nextx = curx + dir[k][0];
                int nexty = cury + dir[k][1];

                if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length)
                    continue;
                if (!visited[nextx][nexty] && grid[nextx][nexty] == 1) {
                    bfs(grid, visited, nextx, nexty);
                }
            }
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
        int res = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count = 0;
                if (!visited[i][j] && grid[i][j] == 1) {
                    bfs(grid, visited, i, j);
                }
                res = Math.max(res, count);
            }
        }

        System.out.println(res);
    }
}
