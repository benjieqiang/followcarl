package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-20  11:39
 * @Description:
 * 106. 岛屿的周长
 * 题目描述
 * 给定一个由 1（陆地）和 0（水）组成的矩阵，岛屿是被水包围，并且通过水平方向或垂直方向上相邻的陆地连接而成的。
 *
 *
 *
 * 你可以假设矩阵外均被水包围。在矩阵中恰好拥有一个岛屿，假设组成岛屿的陆地边长都为 1，请计算岛屿的周长。岛屿内部没有水域。
 *
 * 输入描述
 * 第一行包含两个整数 N, M，表示矩阵的行数和列数。之后 N 行，每行包含 M 个数字，数字为 1 或者 0，表示岛屿的单元格。
 * 输出描述
 * 输出一个整数，表示岛屿的周长。
 * 输入示例
 * 5 5
 * 0 0 0 0 0
 * 0 1 0 1 0
 * 0 1 1 1 0
 * 0 1 1 1 0
 * 0 0 0 0 0
 * 输出示例
 * 14
 *
 * 思路：
 * 遍历 找到小岛上下左右边界，即加入和，或者找到海水，就计入和；
 *463. Island Perimeter
 * @Version: 1.0
 */
import java.util.*;
public class L10_Islands_Perimeter {
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
        int[][] dir = {{-1, 0}, {1,0}, {0, -1}, {0, 1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // neglect sea;
                if (grid[i][j] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int nextx = i + dir[k][0];
                    int nexty = j + dir[k][1];

                    if (nextx < 0 || nextx >= m ||
                            nexty < 0 || nexty >= n ||
                            grid[nextx][nexty] == 0)
                        res++;
                    ;
                }
            }
        }
        System.out.println(res);
    }

    public int islandPerimeter2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        int sum = 0;    // 陆地数量
        int cover = 0;  // 相邻数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    sum++; // 统计总的陆地数量
                    // 统计上边相邻陆地
                    if(i - 1 >= 0 && grid[i - 1][j] == 1) cover++;
                    // 统计左边相邻陆地
                    if(j - 1 >= 0 && grid[i][j - 1] == 1) cover++;
                    // 为什么没统计下边和右边？ 因为避免重复计算
                }
            }
        }
        return sum * 4 - cover * 2;
    }
}
