package com.ben.followcarl.c12_graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-06  20:41
 * @Description:
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 *
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 *
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 *
 * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
 *
 * 思路:
 *
 * 其实就是找到哪些点 可以同时到达太平洋和大西洋。 流动的方式只能从高往低流。
 *
 * 只要参数传入的是 数组pacific，那么地图中 每一个节点其实就遍历一次，无论你调用多少次。
 *
 * 同理，调用 dfs函数，只要 参数传入的是 数组atlantic，地图中每个节点也只会遍历一次。
 *
 * 所以，以下这段代码的时间复杂度是 2 * n * m。 地图用每个节点就遍历了两次，参数传入pacific的时候遍历一次，参数传入atlantic的时候遍历一次。
 *
 * 那么本题整体的时间复杂度其实是： 2 * n * m + n * m ，所以最终时间复杂度为 O(n * m) 。
 *
 * 空间复杂度为：O(n * m) 这个就不难理解了。开了几个 n * m 的数组。
 *
 *
 * @Version: 1.0
 */
public class L8_417_pacificAtlantic {

    public class Solution {
        private int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 保存四个方向

        // 从低向高遍历，注意这里visited是引用，即可以改变传入的pacific和atlantic的值
        private void dfs(int[][] heights, boolean[][] visited, int x, int y) {
            if (visited[x][y]) return;
            visited[x][y] = true;
            int m = heights.length; // 行数
            int n = heights[0].length; // 列数

            for (int i = 0; i < 4; i++) { // 向四个方向遍历
                int nextX = x + dir[i][0];
                int nextY = y + dir[i][1];
                // 超过边界
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;
                // 高度不合适，注意这里是从低向高判断
                if (heights[x][y] > heights[nextX][nextY]) continue;

                dfs(heights, visited, nextX, nextY);
            }
        }

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            List<List<Integer>> result = new ArrayList<>();
            int m = heights.length; // 行数
            int n = heights[0].length; // 列数

            // 记录从太平洋边出发，可以遍历的节点
            boolean[][] pacific = new boolean[m][n];

            // 记录从大西洋出发，可以遍历的节点
            boolean[][] atlantic = new boolean[m][n];

            // 左侧和右侧
            for (int i = 0; i < m; i++) {
                dfs(heights, pacific, i, 0);
                dfs(heights, atlantic, i, n - 1);
            }

            // 上行和下行
            for (int j = 0; j < n; j++) {
                dfs(heights, pacific, 0, j);
                dfs(heights, atlantic, m - 1, j);
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 如果这个节点，从太平洋和大西洋出发都遍历过，就是结果
                    if (pacific[i][j] && atlantic[i][j]) {
                        List<Integer> coordinates = new ArrayList<>();
                        coordinates.add(i);
                        coordinates.add(j);
                        result.add(coordinates);
                    }
                }
            }

            return result;
        }
    }

}
