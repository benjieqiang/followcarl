package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-19  10:45
 * @Description: 102. 沉没孤岛
 * 题目描述
 * 给定一个由 1（陆地）和 0（水）组成的矩阵，岛屿指的是由水平或垂直方向上相邻的陆地单元格组成的区域，且完全被水域单元格包围。孤岛是那些位于矩阵内部、所有单元格都不接触边缘的岛屿。
 * <p>
 * <p>
 * <p>
 * 现在你需要将所有孤岛“沉没”，即将孤岛中的所有陆地单元格（1）转变为水域单元格（0）。
 * <p>
 * 输入描述
 * 第一行包含两个整数 N, M，表示矩阵的行数和列数。
 * <p>
 * 之后 N 行，每行包含 M 个数字，数字为 1 或者 0，表示岛屿的单元格。
 * <p>
 * 输出描述
 * 输出将孤岛“沉没”之后的岛屿矩阵。 注意：每个元素后面都有一个空格
 * 输入示例
 * 4 5
 * 1 1 0 0 0
 * 1 1 0 0 0
 * 0 0 1 0 0
 * 0 0 0 1 1
 * 输出示例
 * 1 1 0 0 0
 * 1 1 0 0 0
 * 0 0 0 0 0
 * 0 0 0 1 1
 * <p>
 * 思路：
 * 1. bfs/dfs从上下左右边出发，把遇到的相连岛标记成2；
 * 2. for循环遍历，把遇到1的孤岛先sink；遇到2的变成1；
 * 3. 注意for循环打印二维数组，每一层完了利用sout换行；
 * @Version: 1.0
 */
import java.util.*;

public class L5_sinkisolatedIslands_dfs {

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void dfs(int[][] grid, int x, int y) {
        grid[x][y] = 2; //island

        for (int k = 0; k < 4; k++) {
            int nextx = x + dir[k][0];
            int nexty = y + dir[k][1];

            if (nextx < 0 || nextx >= grid.length ||
                    nexty < 0 || nexty >= grid[0].length)
                continue;
            if (grid[nextx][nexty] == 1) dfs(grid, nextx, nexty);
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

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][n - 1] == 1) dfs(grid, i, n - 1);
        }

        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1) dfs(grid, 0, i);
            if (grid[m - 1][i] == 1) dfs(grid, m - 1, i);
        }

        // mark isolated island 1->0, 2->1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) grid[i][j] = 0;
                if (grid[i][j] == 2) grid[i][j] = 1;
            }
        }

        // out
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        // 上面合成一步，遇到2，打印1，遇到1打印0；
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) System.out.print(1 + " ");
                else System.out.print(0 + " ");
            }
            System.out.println();
        }
    }
}
