package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-20  07:48
 * @Description:
 * bfs处理时，牵扯到两个：
 * 1. 标记小岛为markNum；
 * 2. 统计面积，所以每次加入小岛就要++
 * @Version: 1.0
 */

import java.util.*;

public class L7_largesetIsland_bfs {


    static int count = 0;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void bfs(int[][] grid, boolean[][] visited, int x, int y, int markNum) {
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        grid[x][y] = markNum;
        count++;
        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int curx = cur[0];
            int cury = cur[1];

            for (int[] d : dir) {
                int nextx = curx + d[0];
                int nexty = cury + d[1];

                if (nextx < 0 || nextx >= grid.length ||
                        nexty < 0 || nexty >= grid[0].length)
                    continue;
                if (visited[nextx][nexty] || grid[nextx][nexty] != 1) continue;

                queue.add(new int[]{nextx, nexty});
                grid[nextx][nexty] = markNum;
                visited[nextx][nexty] = true;
                count++;
            }
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
                    bfs(grid, visited, i, j, markNum);
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
