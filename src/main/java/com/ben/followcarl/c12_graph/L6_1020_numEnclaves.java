package com.ben.followcarl.c12_graph;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-06  11:42
 * @Description: 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 *
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 *
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 *
 * 思路: 如果我们发现这个岛挨着边界,就说明不是我们要找的,就把连着的这一片岛屿全置为0.
 * 采用淹没岛屿的方法, 不需要再定一个visited数组来统计是否走过了;
 * 1. 从左侧到右侧, 如果发现左列和右列有为1的就往网格里面去dfs找岛屿的最大面积; 并把所有的岛屿置为0;
 *      从左侧到右侧去遍历[i][0] [i][m - 1] => i从0到n - 1
 * 2. 从上侧到下侧, 如果发现上边和下边有为1的就往网格里dfs找岛屿的最大面积; 并把所有的岛屿置为0;
 *
 * 3. 这样最后一遍遍历时, 遍历整个grid数组,找最大的岛屿面积就是所求;
 * @Version: 1.0
 */
public class L6_1020_numEnclaves {
    class Solution {
        int count;
        int[][] nums = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        private void dfs(int[][] grid, int i, int j) {
            if (grid[i][j] == 0) return;

            grid[i][j] = 0;
            count++;
            for (int k = 0; k < 4; k++) {
                int x = i + nums[k][0];
                int y = j + nums[k][1];

                if (x < 0 || x == grid.length || y < 0 || y == grid[0].length) continue;
                dfs(grid, x, y);
            }
        }

        public int numEnclaves(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            for (int i = 0; i < m; i++) {
                if (grid[i][0] == 1) dfs(grid, i, 0);
                if (grid[i][n - 1] == 1) dfs(grid, i, n - 1);
            }
            for (int i = 0; i < n; i++) {
                if (grid[0][i] == 1) dfs(grid, 0, i);
                if (grid[m - 1][i] == 1) dfs(grid, m - 1, i);
            }
            count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) dfs(grid, i, j);
                }
            }
            return count;
        }
    }

    class Solution2 {
        int count = 0; // count必须在主函数里进行初始化,直接定义成int count = 0;结果变成4
        int[][] nums = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        private void bfs(int[][] grid, int i, int j) {
            Deque<int[]> queue = new LinkedList<>();
            queue.add(new int[]{i, j});
            grid[i][j] = 0;
            count++;
            System.out.println("queue size: " + queue.size());
            while (!queue.isEmpty()) {
                int[] tmp = queue.remove();
                int x = tmp[0];
                int y = tmp[1];
                for (int k = 0; k < 4; k++) {
                    int nextX = x + nums[k][0];
                    int nextY = y + nums[k][1];
                    if (nextX < 0 || nextX == grid.length || nextY < 0 || nextY == grid[0].length)
                        continue;
                    if (grid[nextX][nextY] == 0) continue;
                    queue.add(new int[]{nextX, nextY});
                    count++;
                    grid[nextX][nextY] = 0;
                    System.out.println("queue size: " + queue.size());
                    System.out.println("add (" + nextX +","+ nextY + ") in queue");
                }
            }
        }
        public int numEnclaves(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            // 左侧边 -》 右侧边
            for (int i = 0; i < m; i++) {
                if (grid[i][0] == 1) bfs(grid, i, 0);
                if (grid[i][n - 1] == 1) bfs(grid, i, n - 1);
            }
            // 上侧边 -》 下侧边
            for (int j = 0; j < n; j++) {
                if (grid[0][j] == 1) bfs(grid, 0, j);
                if (grid[m - 1][j] == 1) bfs(grid, m - 1, j);
            }
//            count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) bfs(grid, i, j);
                }
            }
            return count;
        }
    }
    @Test
    public void testNumEnclaves() {
        int[][] grid = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        int[][] grid2 = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};

        System.out.println(new Solution().numEnclaves(grid));
        System.out.println(new Solution2().numEnclaves(grid2));
    }

    /*
    *
    queue size: 1
    queue size: 1
    queue size: 1
    add (2,2) in queue
    queue size: 1
    add (2,1) in queue
    4
    *
    queue size: 1
    queue size: 1
    queue size: 1
    add (2,2) in queue
    queue size: 1
    add (2,1) in queue
    3
*
    *
    * */
}
