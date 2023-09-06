package com.ben.followcarl.c12_graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-06  20:04
 * @Description:
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 和51题飞地的数量不一致的部分:
 * 1, 这个是要求边上岛屿, 飞地的数量求的是不包括边界的岛里面的岛的面积;
 * 2, 思路还是一致: 从外向里去dfs找相邻的岛, 找到就给赋值成其他数;
 * 3, 我们遍历整个数组, 第2部如果是被x包围的o的话,那么把它转成x, 遇到A转成O;
 *
 * dfs和bfs的整体思路还是一致的:
 * 如果边界为O， 从外向里去用bfs或dfs去找相连的岛屿,找到就给置成A,
 * 再双层for循环来把所有的O变成X,A变成O;
 * @Version: 1.0
 */
public class L7_130_solve {
    class Solution {
        int[][] nums = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        private void dfs(char[][] board, int i, int j) {
            if (board[i][j] == 'X' || board[i][j] == 'A') return;

            board[i][j] = 'A';
            for (int k = 0; k < 4; k++) {
                int x = i + nums[k][0];
                int y = j + nums[k][1];
                if (x < 0 || x == board.length || y < 0 || y == board[0].length)
                    continue;
                dfs(board, x, y);
            }
        }

        public void solve(char[][] board) {
            int m = board.length;
            int n = board[0].length;

            // 左侧列  右侧列
            for (int i = 0; i < m; i++) {
                if (board[i][0] == 'O') dfs(board, i, 0);
                if (board[i][n - 1] == 'O') dfs(board, i, n - 1);
            }

            // 上行, 下行
            for (int j = 0; j < n; j++) {
                if (board[0][j] == 'O') dfs(board, 0, j);
                if (board[m - 1][j] == 'O') dfs(board, m - 1, j);
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') board[i][j] = 'X';
                    if (board[i][j] == 'A') board[i][j] = 'O';
                }
            }
        }
    }

    class Solution2 {
        int[][] nums = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        private void bfs(char[][] board, int i, int j) {
            Deque<int[]> queue = new LinkedList<>();
            queue.add(new int[]{i, j});
            board[i][j] = 'A';
            while (!queue.isEmpty()) {
                int[] tmp = queue.remove();
                int x = tmp[0];
                int y = tmp[1];
                for (int k = 0; k < 4; k++) {
                    int nextx = x + nums[k][0];
                    int nexty = y + nums[k][1];
                    if (nextx < 0 || nextx == board.length || nexty < 0 || nexty == board[0].length)
                        continue;
                    if (board[nextx][nexty] == 'X' || board[nextx][nexty] == 'A')
                        continue;
                    queue.add(new int[]{nextx, nexty});
                    board[nextx][nexty] = 'A';
                }
            }
        }
        public void solve(char[][] board) {
            int m = board.length;
            int n = board[0].length;

            for (int i = 0; i < m; i++) {
                if (board[i][0] == 'O') bfs(board, i, 0);
                if (board[i][n - 1] == 'O') bfs(board, i, n - 1);
            }

            for (int j = 0; j < n; j++) {
                if (board[0][j] == 'O') bfs(board, 0, j);
                if (board[m - 1][j] == 'O') bfs(board, m - 1, j);
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') board[i][j] = 'X';
                    if (board[i][j] == 'A') board[i][j] = 'O';
                }
            }
        }
    }
}
