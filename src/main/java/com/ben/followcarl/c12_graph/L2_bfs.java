package com.ben.followcarl.c12_graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-31  18:18
 * @Description: TODO
 * @Version: 1.0
 */
public class L2_bfs {
    static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; // 表示四个方向

    // grid 是地图，也就是一个二维数组
    // visited标记访问过的节点，不要重复访问
    // x,y 表示开始搜索节点的下标
    static void bfs(char[][] grid, boolean[][] visited, int x, int y) {
        Deque<int[]> que = new LinkedList<>(); // 定义队列
        que.add(new int[]{x, y}); // 起始节点加入队列
        visited[x][y] = true; // 只要加入队列，立刻标记为访问过的节点
        while (!que.isEmpty()) { // 开始遍历队列里的元素
            int[] cur = que.remove(); // 从队列取元素
            int curx = cur[0];
            int cury = cur[1]; // 当前节点坐标
            for (int i = 0; i < 4; i++) { // 开始向当前节点的四个方向上右左下去遍历
                int nextx = curx + dir[i][0];
                int nexty = cury + dir[i][1]; // 获取周边四个方向的坐标
                if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length) continue;  // 坐标越界直接跳过
                if (!visited[nextx][nexty]) { // 如果节点没被访问过
                    que.add(new int[]{nextx, nexty});  // 队列添加该节点为下一轮要遍历的节点
                    visited[nextx][nexty] = true; // 只要加入队列立刻标记，避免重复访问
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    bfs(grid, visited, i, j);
                    count++;
                }
            }
        }
        System.out.println(count);
    }


}
