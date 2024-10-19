package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-18  10:02
 * @Description:
 * acm注意点：
 * 1. 接收一个二维数组；
 * 2. 小岛是整型数组，所以比较时比较的时==1;
 * 3. bfs，遍历整个小岛，如何没有访问且时小岛才进去bfs看他的上下左右是不是相连小岛；
 * 4. bfs使用的队列，先加入当前元素，再使用visited数组表示已经访问过
 * 5.
 * @Version: 1.0
 */

import java.util.*;

public class L2_numIsLands_bfs {
    static int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};


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

        boolean[][] visited = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    res++;
                    bfs(grid, visited, i, j);
                }
            }
        }
        System.out.println(res);
    }

    private static void bfs(int[][] grid, boolean[][] visited, int x, int y) {
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int curx = cur[0];
            int cury = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextx = curx + dir[i][0];
                int nexty = cury + dir[i][1];
                if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length)
                    continue;
                if (!visited[nextx][nexty] && grid[nextx][nexty] == 1) {
                    queue.add(new int[]{nextx, nexty});
                    visited[nextx][nexty] = true;
                }
            }
        }
    }

}
