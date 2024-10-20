package com.ben.followcarl.c12_graph;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-06  22:29
 * @Description:
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 *
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * 思路:
 * 遍历整个二维数组, 如果越界或遇到海水,则贡献一条边;
 * 最后的和就是所求;
 *
 * @Version: 1.0
 */
public class L12_463_islandPerimeter {
    int[][] nums = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int x = i + nums[k][0];
                        int y = j + nums[k][1];
                        // 如果越界或遇到海水,则贡献一条边;
                        if (x < 0 || x == m || y < 0 || y == n || grid[x][y] == 0)
                            res++;
                    }
                }
            }
        }
        return res;
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
