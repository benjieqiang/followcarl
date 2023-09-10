package com.ben.followcarl.c12_graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-09  11:05
 * @Description: TODO
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
                    if (visited[i][j] == false && grid[i][j] == 1) {
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
            if (isAllGrid) return m * n;
            int res = 0;
            // 2. 遍历二维数组, 找0, 统计其相邻岛屿面积，最后取一个最大值。
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    HashSet<Integer> set = new HashSet<>(); //todo
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

//    @Test
//    public void testLargestIsland(){
//        int[][] grid = {{1,0},{0,1}};
//        System.out.println(new Solution().largestIsland(grid));
//    }
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
        System.out.println(new Solution().largestIsland(grid));
    }

}
