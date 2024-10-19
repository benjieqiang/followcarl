package com.ben.followcarl.c12_graph;

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
public class L3_200_numIslands_dfs {

    /**
     * @param grid:
     * @return int
     * @description 方法1: 感染思路, 核心思路:
     * 为了不让岛屿重复记录, 每次找到一个岛,把他淹没(置成0),这样在dfs判断的时候如果该岛是0.说明已经找过了,直接return;
     * 每座岛屿只能从水平和垂直方向搜索, 上下左右 去搜;
     * @author benjieqiang
     * @date 2023/8/31 6:44 PM
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i - 1, j); // 上
        dfs(grid, i + 1, j); // 下
        dfs(grid, i, j - 1); // 左
        dfs(grid, i, j + 1); // 右
    }


    /**
     * @author benjieqiang
     * @return int
     * @description 利用visited数组标记, 如果访问过,就不会再去访问该岛屿;
     * 时间复杂度：O(MN)，其中 M 和 N 分别为行数和列数。
     * <p>
     * 空间复杂度：O(MN)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到 MNM NMN。
     * @date 2023/9/6 10:16 AM
     */
    class Solution {
        int[][] nums = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };

        public int numIslands(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];

            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && grid[i][j] == '1') {
                        res++;
                        dfs(grid, visited, i, j); // dfs结束意思是：以i，j为中心的小岛都找到并且标记了。
                    }
                }
            }
            return res;
        }

        private void dfs(char[][] grid, boolean[][] visited, int i, int j) {

            visited[i][j] = true;

            for (int k = 0; k < 4; k++) {
                int numsX = i + nums[k][0];
                int numsY = j + nums[k][1];
                if (numsX < 0 || numsX >= grid.length || numsY < 0 || numsY >= grid[0].length) {
                    continue;
                }
                if (!visited[numsX][numsY] && grid[numsX][numsY] == '1') {
                    dfs(grid, visited, numsX, numsY);
                }
            }
        }
    }
}
