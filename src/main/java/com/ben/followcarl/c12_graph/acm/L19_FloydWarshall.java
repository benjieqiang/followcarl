package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-24  10:17
 * @Description:
 * 7. 小明逛公园
 * 题目描述
 * 小明喜欢去公园散步，公园内布置了许多的景点，相互之间通过小路连接，小明希望在观看景点的同时，能够节省体力，走最短的路径。
 *
 *
 *
 * 给定一个公园景点图，图中有 N 个景点（编号为 1 到 N），以及 M 条双向道路连接着这些景点。每条道路上行走的距离都是已知的。
 *
 *
 *
 * 小明有 Q 个观景计划，每个计划都有一个起点 start 和一个终点 end，表示他想从景点 start 前往景点 end。由于小明希望节省体力，他想知道每个观景计划中从起点到终点的最短路径长度。 请你帮助小明计算出每个观景计划的最短路径长度。
 *
 * 输入描述
 * 第一行包含两个整数 N, M, 分别表示景点的数量和道路的数量。
 *
 * 接下来的 M 行，每行包含三个整数 u, v, w，表示景点 u 和景点 v 之间有一条长度为 w 的双向道路。
 *
 * 接下里的一行包含一个整数 Q，表示观景计划的数量。
 *
 * 接下来的 Q 行，每行包含两个整数 start, end，表示一个观景计划的起点和终点。
 *
 * 输出描述
 * 对于每个观景计划，输出一行表示从起点到终点的最短路径长度。如果两个景点之间不存在路径，则输出 -1。
 * 输入示例
 * 7 3
 * 2 3 4
 * 3 6 6
 * 4 7 8
 * 2
 * 2 3
 * 3 4
 * 输出示例
 * 4
 * -1
 * 提示信息
 * 从 2 到 3 的路径长度为 4，3 到 4 之间并没有道路。
 *
 * 1 <= N, M, Q <= 1000.
 *
 * 1 <= w <= 10000.
 * 多源最短路，即 求多个起点到多个终点的多条最短路径。*
 * Floyd 算法对边的权值正负没有要求，都可以处理。
 *
 * Floyd算法核心思想是动态规划。
 *
 *
 *
 * @Version: 1.0
 */
import java.util.*;
public class L19_FloydWarshall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 城市数量
        int m = sc.nextInt(); // 道路数量
        int INF = 10005;  // 因为边的最大距离是10^4

        // grid[i][j][k] 表示从城市 i 到城市 j， 以[1...k] 集合为中间节点的最短距离
        // [1...k] ，表示节点1 到 节点k 一共k个节点的集合。
        int[][][] grid = new int[n + 1][n + 1][n + 1];  // 三维数组

        // 初始化，i = j 表示同一个城市，距离就是0；
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k <= n; k++) {
                    grid[i][j][k] = (i == j) ? 0 : INF;  // 自己到自己距离为0，其他为无穷大
                }
            }
        }

        // 读入边
        for (int i = 0; i < m; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();
            int val = sc.nextInt();
            grid[p1][p2][0] = val;
            grid[p2][p1][0] = val;  // 注意这里是双向图
        }

        // 开始 Floyd-Warshall 算法
        // 1. 节点i 到 节点j 的最短路径经过节点k。 城市i-----城市k-----城市j，不经过城市k，只统计i到城市k 经过前k-1中间城市的最短路径；grid[i][k][k - 1]
        // 城市k到城市j，经过前k-1中间城市的最短路径，grid[k][j][k - 1]
        // 2. 节点i 到 节点j 的最短路径不经过节点k，保持上一把的最短路径，中间节点集合[1...k-1]
        // 遍历的顺序是从底向上 一层一层去遍历。i 和j 是平层，而k 是 垂直向上 的。
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    grid[i][j][k] = Math.min(grid[i][j][k - 1], grid[i][k][k - 1] + grid[k][j][k - 1]);
                }
            }
        }

        // 处理查询
        int z = sc.nextInt();  // 查询次数
        while (z-- > 0) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            if (grid[start][end][n] == INF) {
                System.out.println(-1);
            } else {
                System.out.println(grid[start][end][n]);
            }
        }

        sc.close();
    }
}
