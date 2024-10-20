package com.ben.followcarl.c12_graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-09  11:05
 * @Description:
 * 827. Making A Large Island
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 * @Version: 1.0
 */
public class L9_827_largestIsland {
    class Solution {
        private int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int count;
        private void dfs(int[][] grid, int i, int j, boolean[][] visited, int mark) {
            if (visited[i][j] || grid[i][j] == 0) return;
            visited[i][j] = true;
            grid[i][j] = mark;
            count++;
            for (int[] tmp : dir) {
                int x = i + tmp[0];
                int y = j + tmp[1];
                if (x < 0 || x == grid.length || y < 0 || y == grid[0].length) continue;
                dfs(grid, x, y, visited, mark);
            }
        }
        public int largestIsland(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            HashMap<Integer, Integer> map = new HashMap<>();
            int mark = 2; // 标记每个岛;
            boolean isAllGrid = true; // 遍历完二维数组,发现还是true,说明全是1;

            // 1. dfs/bfs 遍历二维数组,并编号记录到map数组,key是岛屿编号, value是岛屿面积;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) isAllGrid = false;
                    if (!visited[i][j] && grid[i][j] == 1) {
                        count = 0;
                        dfs(grid, i, j, visited, mark);
                        map.put(mark, count);
                        mark++;
                    }
                }
            }
            for (int[] num : grid) {
                System.out.println(Arrays.toString(num));
            }
            System.out.println(map);
            if (isAllGrid) return m * n;
            int res = 0;
            // 2. 遍历二维数组, 找0, 统计其相邻岛屿面积，最后取一个最大值。
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    HashSet<Integer> set = new HashSet<>(); //记录小岛0周围计算过的小岛
                    if (grid[i][j] != 0) continue; //找0, 遇到非0则跳过;
                    int num = 1; // 统计当前0的上下左右的最大面积;
                    for (int[] tmp : dir) {
                        int x = i + tmp[0];
                        int y = j + tmp[1];
                        if (x < 0 || x == m || y < 0 || y == n) continue;
                        int curMark = grid[x][y];
                        // set中有该标记,说明之前加过了,不用添加,
                        // map中get不到该标记,说明该标记不合法;
                        if (set.contains(curMark) || !map.containsKey(curMark)) continue;
                        set.add(curMark);
                        num = num + map.get(curMark);
                    }
                    res = Math.max(res, num);
                }
            }
            return res;
        }
    }
    class Solution2 {
        int count = 0;
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
        private void dfs(int[][] grid, boolean[][] visited, int x, int y, int markNum) {
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


        public int largestIsland(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            boolean[][] visited = new boolean[m][n];

            int markNum = 2;
            // markNum:maxSize;
            HashMap<Integer, Integer> map = new HashMap<>();
            boolean isAllIlands = true;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) isAllIlands = false;
                    // 只
                    if (!visited[i][j] && grid[i][j] == 1) {
                        count = 0;
                        // maxSize, mark i,j 1->markNum;
                        dfs(grid, visited, i, j, markNum);
                        map.put(markNum, count);
                        markNum++; // 下次小岛从markNum++开始
                    }
                }
            }
             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) {
                     System.out.print(grid[i][j] + " ");
                 }
                 System.out.println("");
             }
             System.out.println(map);

            int res = 0;
            if (isAllIlands) {
                return n * m;
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
            return res;
        }
    }

    @Test
    public void testLargestIsland(){
        int[][] grid = {{0,0},{1,1}};
//        System.out.println(new Solution().largestIsland(grid));
        System.out.println(new Solution2().largestIsland(grid));
    }

    @Test
    public void testIsLand() {
        int[][] grid = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0}
        };
//        System.out.println(new Solution().largestIsland(grid));
        System.out.println(new Solution2().largestIsland(grid));
    }

}
