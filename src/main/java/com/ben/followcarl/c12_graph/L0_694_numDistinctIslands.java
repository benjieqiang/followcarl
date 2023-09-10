package com.ben.followcarl.c12_graph;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-06  23:59
 * @Description: 给定一个非空01二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 1 组成，你可以认为网格的四周被海水包围。
 * <p>
 * 请你计算这个网格中共有多少个形状不同的岛屿。
 * 两个岛屿被认为是相同的，当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合。
 * <p>
 * 样例 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * 给定上图，返回结果 1。
 * <p>
 * 样例 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * 给定上图，返回结果 3。
 * <p>
 * 注意:
 * 11
 * 1
 * 和
 * 1
 * 11
 * 是不同的岛屿，因为我们不考虑旋转、翻转操作。
 * <p>
 * 注释 :  二维数组每维的大小都不会超过50。
 *
 * 给每个小岛遍历记录一下遍历顺序,
 * @Version: 1.0
 */
public class L0_694_numDistinctIslands {
    String encoding = "";

    public int numDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    encoding = "";
                    dfs(grid, i, j, "s");
                    set.add(encoding);
                }
            }
        }

        return set.size();
    }

    private void dfs(int[][] grid, int i, int j, String direction) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0)
            return;

        grid[i][j] = 0;
        encoding += direction;
        dfs(grid, i - 1, j, "u");
        dfs(grid, i + 1, j, "d");
        dfs(grid, i, j - 1, "l");
        dfs(grid, i, j + 1, "r");
        encoding += "e"; // 每次dfs结束时加上一个标记; 这样能区分两种不同的遍历顺序
        System.out.println(encoding);
    }

    @Test
    public void testNum() {
        int[][] grid = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };
        System.out.println(numDistinctIslands(grid)); //1
    }
}
