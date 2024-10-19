package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-19  11:41
 * @Description: 103. 水流问题
 * 题目描述
 * 现有一个 N × M 的矩阵，每个单元格包含一个数值，这个数值代表该位置的相对高度。矩阵的左边界和上边界被认为是第一组边界，而矩阵的右边界和下边界被视为第二组边界。
 * <p>
 * <p>
 * <p>
 * 矩阵模拟了一个地形，当雨水落在上面时，水会根据地形的倾斜向低处流动，但只能从较高或等高的地点流向较低或等高并且相邻（上下左右方向）的地点。我们的目标是确定那些单元格，从这些单元格出发的水可以达到第一组边界和第二组边界。
 * <p>
 * 输入描述
 * 第一行包含两个整数 N 和 M，分别表示矩阵的行数和列数。
 * <p>
 * 后续 N 行，每行包含 M 个整数，表示矩阵中的每个单元格的高度。
 * <p>
 * 输出描述
 * 输出共有多行，每行输出两个整数，用一个空格隔开，表示可达第一组边界和第二组边界的单元格的坐标，输出顺序任意。
 * 输入示例
 * 5 5
 * 1 3 1 2 4
 * 1 2 1 3 2
 * 2 4 7 2 1
 * 4 5 6 1 1
 * 1 4 1 2 1
 * 输出示例
 * 0 4
 * 1 3
 * 2 2
 * 3 0
 * 3 1
 * 3 2
 * 4 0
 * 4 1
 * <p>
 * 思路：
 * 两个boolean数组，board1， board2
 * 分别从上边，左边找到最大的那个数，找到board1标记为true，
 * 从右边和下标找最大数，找到board2标记为true；
 * <p>
 * 最后统计同时访问过的就是从左上能流到右下的边的水流；
 * @Version: 1.0
 */

import java.util.*;

public class L6_pacificAtlantic_dfs {

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void dfs(int[][] grid, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        for (int[] d : dir) {
            int nextx = x + d[0];
            int nexty = y + d[1];
            if (nextx < 0 || nextx >= grid.length ||
                    nexty < 0 || nexty >= grid[0].length)
                continue;
            if (grid[x][y] > grid[nextx][nexty]) continue;

            if (!visited[nextx][nexty]) dfs(grid, visited, nextx, nexty);
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

        boolean[][] board1 = new boolean[m][n];
        boolean[][] board2 = new boolean[m][n];

        // left,right;
        for (int i = 0; i < m; i++) {
            dfs(grid, board1, i, 0);
            dfs(grid, board2, i, n - 1);
        }

        // top , bottom
        for (int i = 0; i < n; i++) {
            dfs(grid, board1, 0, i);
            dfs(grid, board2, m - 1, i);
        }


        // out
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board1[i][j] && board2[i][j]) {
                    System.out.println(i + " " + j);
                }
            }
        }

    }
}
