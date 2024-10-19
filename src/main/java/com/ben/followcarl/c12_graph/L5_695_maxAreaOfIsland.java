package com.ben.followcarl.c12_graph;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-06  10:44
 * @Description: 给你一个大小为 m x n 的二进制矩阵 grid 。
 * <p>
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * <p>
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0
 *
 * 思路:
 * dfs/bfs
 * @Version: 1.0
 */
public class L5_695_maxAreaOfIsland {
    int[][] nums = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };
    int count;


    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] used = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!used[i][j] && grid[i][j] == 1) {
                    count = 0; // 每一次进来都给赋值成0，dfs表示从ij出发找到了一片相连小岛，找到就返回了。
                    dfs(grid, used, i, j);
                    res = Math.max(res, count);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, boolean[][] used, int i, int j) {
        if (used[i][j] || grid[i][j] == 0) return;

        used[i][j] = true;
        count++;
        for (int k = 0; k < 4; k++) {
            int numX = i + nums[k][0];
            int numY = j + nums[k][1];
            if (numX < 0 || numX == grid.length || numY < 0 || numY == grid[0].length) continue;
            dfs(grid, used, numX, numY);
        }
    }


    class Solution {
        int[][] nums = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
        int count;
        private void bfs(int[][] grid, boolean[][] used, int i, int j) {
            Deque<int[]> queue = new LinkedList<>();
            queue.add(new int[]{i, j});
            used[i][j] = true;
            count++;
            while (!queue.isEmpty()) {
                int[] tmp = queue.remove();
                int x = tmp[0];
                int y = tmp[1];

                for (int k = 0; k < 4; k++) {
                    int nextx = x + nums[k][0];
                    int nexty = y + nums[k][1];
                    if (nextx < 0 || nextx == grid.length || nexty < 0 || nexty == grid[0].length)
                        continue;
                    if (used[nextx][nexty] == true || grid[nextx][nexty] == 0) continue;
                    queue.add(new int[]{nextx, nexty});
                    used[nextx][nexty] = true;
                    count++;
                }
            }
        }
        public int maxAreaOfIsland(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] used = new boolean[m][n];
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!used[i][j] && grid[i][j] == 1) {
                        count = 0;
                        bfs(grid, used, i, j);
                        res = Math.max(res, count);
                    }
                }
            }
            return res;
        }
    }


    @Test
    public void testNumsColAndRow() {
        int[][] nums = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
        System.out.println(nums.length);
        System.out.println(nums[0].length);


    }
}
