package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-31  21:02
 * @Description: 给定一个 m 行 n 列的二维地图，初始化每个单元格都是海洋，二维地图外也全是海洋。
 * <p>
 * 操作 addLand 会将单元格（col, row）变为陆地。定义一系列相连的被海洋包围的陆地为岛屿，横向相邻或者纵向相连的岛屿才算相连（斜着相邻的不算）。
 * <p>
 * 现有一系列的 addLand 操作，给出每次 addLand 操作后岛屿的个数。
 * <p>
 * 输入
 * 第一行为正整数 m
 * <p>
 * 第二行为正整数 n
 * <p>
 * 第三行表示 addLand 的操作次数 k
 * <p>
 * 后续有 k 行输入，每行分别为两个整数，代表 addLand 操作的坐标
 * 3
 * 3
 * 4
 * 0 0
 * 0 1
 * 1 2
 * 2 1
 * <p>
 * 输出
 * 输出为一行，k个整数，代表每次 addLand 后地图中岛屿的个数，整数之间用一个空格隔开，最后一个整数后没有空格
 * 1 1 2 3
 *
 * 测试用例如下：
 * 4
 * 4
 * 7
 * 0 0
 * 1 1
 * 1 2
 * 2 3
 * 0 1
 * 3 2
 * 3 3
 * 预期输出：1 2 2 3 2 3 2
 * @Version: 1.0
 */

// 本题使用dfs
// 总体思路如下:
// 每次进行一个操作, 进行判断
// 如果该操作越界或要操作的地方已经是岛屿了
// 那么跳过此操作, ans不变并且输出ans
// 如果这个操作没问题, 我们将该岛屿编号为ans
// 就检查该操作的上下左右是否有岛屿
// 根据岛屿编号的不同, 我们每发现一个新的编号
// 就说明该编号的岛屿和新添加的岛屿连通了
// 那么岛屿数量 - 1
// 检查完上下左右以后, 我们得到新的ans
// 然后利用dfs把与当前我们操作的岛屿连通的所有岛屿都编号为ans

import org.junit.Test;

import java.util.*;

public class L41_islandsNum {
    private static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 表示方向
    private static ArrayList<Integer> list = new ArrayList<>();
    private static void dfs(int[][] grid, boolean[][] visited, int x, int y, int num) {
        // 遍历四个方向
        for (int i = 0; i < 4; ++i) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            // 跳过越界的情况和海洋
            if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || visited[nx][ny] || grid[nx][ny] == 0) {
                continue;
            }
            // 标记为已经访问过
            visited[nx][ny] = true;
            // 将该岛屿编号
            grid[nx][ny] = num;
            dfs(grid, visited, nx, ny, num);
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int count = scanner.nextInt();
            int[][] grid = new int[m][n]; // 地图
            Integer res = 0;
            Set<Integer> st = new HashSet<>(); // 用来储存不同的岛屿
            while (count-- > 0) {
                int i = scanner.nextInt();
                int j = scanner.nextInt();
                // 如果该操作越界或要操作的地方已经是岛屿就直接输出答案
//                if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 0) {
//                    System.out.print(res + " ");
//                    continue;
//                }
                // 岛屿数量增加
                res++;
                // 将新岛屿编号
                grid[i][j] = res;
                // 遍历四个方向
                for (int k = 0; k < 4; ++k) {
                    int nx = i + dir[k][0];
                    int ny = j + dir[k][1];
                    // 跳过越界的情况和海洋
                    if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || grid[nx][ny] == 0) {
                        continue;
                    }
                    // 看4个方向有没有新的岛屿, 有的话,就说明这个岛屿与我们要操作的地方构成连通
                    // 岛屿数量 - 1 并且将新岛屿加入set
                    if (!st.contains(grid[nx][ny])) {
                        st.add(grid[nx][ny]);
                        res--;
                    }
                }
                boolean[][] visited = new boolean[grid.length][grid[0].length]; // 储存访问情况
                visited[i][j] = true; // 标记为已经访问
                grid[i][j] = res;  // 将编号改为我们新得到的ans
                dfs(grid, visited, i, j, res); // 将所有与当前操作的岛屿连通的岛屿都编号为我们新得到的ans
                list.add(res);
            }

            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) System.out.println(list.get(i));
                else System.out.print(list.get(i) + " ");
            }
        }

    }


    public void resFinal(Integer res) {
        res--;
    }
    @Test
    public void testRes() {
        Integer res = 5;
        resFinal(res);
        System.out.println(res);
    }
}
