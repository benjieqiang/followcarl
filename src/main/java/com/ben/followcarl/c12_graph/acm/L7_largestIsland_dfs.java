package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-20  07:03
 * @Description: 104. 建造最大岛屿
 * 题目描述
 * 给定一个由 1（陆地）和 0（水）组成的矩阵，你最多可以将矩阵中的一格水变为一块陆地，在执行了此操作之后，矩阵中最大的岛屿面积是多少。
 * <p>
 * <p>
 * <p>
 * 岛屿面积的计算方式为组成岛屿的陆地的总数。岛屿是被水包围，并且通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设矩阵外均被水包围。
 * <p>
 * 输入描述
 * 第一行包含两个整数 N, M，表示矩阵的行数和列数。之后 N 行，每行包含 M 个数字，数字为 1 或者 0，表示岛屿的单元格。
 * 输出描述
 * 输出一个整数，表示最大的岛屿面积。
 * 输入示例
 * 4 5
 * 1 1 0 0 0
 * 1 1 0 0 0
 * 0 0 1 0 0
 * 0 0 0 1 1
 * 输出示例
 * 6
 * <p>
 * 暴力思路：
 * 1. 遍历矩阵，将每一个0改成1，然后去dfs/bfs求岛屿的最大面积（把count置为0，dfs/bfs求该坐标上下左右扩展结束后的count值，就是最大面积）；时间复杂度为 n * n。
 * <p>
 * 优化思路：
 * 1. 遍历矩阵，使用map<islandNum, maxSize> 统计独立小岛的面积，存储小岛编号：面积；
 * 2. 遍历矩阵，只要是海水，则进入统计海水周围的独立小岛面积和，最后求最大；
 * @Version: 1.0
 */

import java.util.*;

public class L7_largestIsland_dfs {

    static int count = 0;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void dfs(int[][] grid, boolean[][] visited, int x, int y, int markNum) {
        if (visited[x][y] || grid[x][y] == 0) return;

        visited[x][y] = true;
        grid[x][y] = markNum;
        count++;

        for (int[] d : dir) {
            int nextx = x + d[0];
            int nexty = y + d[1];

            if (nextx < 0 || nextx >= grid.length ||
                    nexty < 0 || nexty >= grid[0].length)
                continue;
            dfs(grid, visited, nextx, nexty, markNum);
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

        boolean[][] visited = new boolean[m][n];

        int markNum = 1;
        // markNum:maxSize;
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean isAllIlands = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) isAllIlands = false;
                // 只统计没有被访问过的小岛1；
                if (!visited[i][j] && grid[i][j] == 1) {
                    count = 0;
                    markNum++;
                    // maxSize, mark i,j 1->markNum;
                    dfs(grid, visited, i, j, markNum);
                    map.put(markNum, count);
                }
            }
        }
        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         System.out.print(grid[i][j] + " ");
        //     }
        //     System.out.println("");
        // }
        // System.out.println(map);

        int res = 0;
        if (isAllIlands) {
            System.out.print(n * m);
            return;
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) continue;
                //记录小岛0周围计算过的小岛
                HashSet<Integer> set = new HashSet<>();
                int num = 1; // 统计当前0的上下左右的最大面积; 初值是1，因为假设这个就是小岛，面积就是1
                for (int[] d : dir) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    int curMark = grid[x][y];
                    // set中有该标记,说明之前加过了,不用添加,
                    // map中get不到该标记,说明该标记不合法;
                    if (set.contains(curMark) || !map.containsKey(curMark)) continue;
                    set.add(curMark);
                    num += map.get(curMark);
                }
                res = Math.max(res, num);
            }
        }

        System.out.println(res);
    }
}
