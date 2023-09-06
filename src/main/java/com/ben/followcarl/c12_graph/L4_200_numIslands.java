package com.ben.followcarl.c12_graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-31  18:27
 * @Description: 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * <p>
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * @Version: 1.0
 */
public class L4_200_numIslands {

    /**
     * @param grid:
     * @return int
     * @description TODO
     * @author benjieqiang
     * @date 2023/9/6 10:31 AM
     */
    int[][] nums = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] used = new boolean[m][n];
        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!used[i][j] && grid[i][j] == '1') {
                    res++;
                    bfs(grid, used, i, j);
                }
            }
        }
        return res;
    }

    private void bfs(char[][] grid, boolean[][] used, int i, int j) {
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        used[i][j] = true; // 加入队列, 立即标记;
        while (!queue.isEmpty()) {
            int[] tmp = queue.remove();
            int m = tmp[0];
            int n = tmp[1];
            for (int k = 0; k < 4; k++) {
                int x = m + nums[k][0];
                int y = n + nums[k][1];
                if (x < 0 || x == grid.length || y < 0 || y == grid[0].length) continue;
                if (!used[x][y] && grid[x][y] == '1') {
                    queue.add(new int[]{x, y});
                    used[x][y] = true; // 加入队列表示已经访问过;
                }
            }
        }
    }
}