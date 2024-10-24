package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-23  08:30
 * @Description: 47. 参加科学大会（第六期模拟笔试）
 * 题目描述
 * 小明是一位科学家，他需要参加一场重要的国际科学大会，以展示自己的最新研究成果。
 * <p>
 * 小明的起点是第一个车站，终点是最后一个车站。然而，途中的各个车站之间的道路状况、交通拥堵程度以及可能的自然因素（如天气变化）等不同，这些因素都会影响每条路径的通行时间。
 * <p>
 * 小明希望能选择一条花费时间最少的路线，以确保他能够尽快到达目的地。
 * <p>
 * 输入描述
 * 第一行包含两个正整数，第一个正整数 N 表示一共有 N 个公共汽车站，第二个正整数 M 表示有 M 条公路。
 * <p>
 * 接下来为 M 行，每行包括三个整数，S、E 和 V，代表了从 S 车站可以单向直达 E 车站，并且需要花费 V 单位的时间。
 * <p>
 * 输出描述
 * 输出一个整数，代表小明从起点到终点所花费的最小时间。
 * 输入示例
 * 7 9
 * 1 2 1
 * 1 3 4
 * 2 3 2
 * 2 4 5
 * 3 4 2
 * 4 5 3
 * 2 6 4
 * 5 7 4
 * 6 7 9
 * 输出示例
 * 12
 * 提示信息
 * 能够到达的情况：
 * <p>
 * 理解：
 * 有向图，求起点到终点最短路径。
 * 如下图所示，起始车站为 1 号车站，终点车站为 7 号车站，绿色路线为最短的路线，路线总长度为 12，则输出 12。dijkstra算法：在有权图（权值非负数）中求从起点到其他节点的最短路径算法。注意：
 * <p>
 * dijkstra 算法可以同时求 起点到所有节点的最短路径
 * 权值不能为负数
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n^2)
 * @Version: 1.0
 */

import java.util.*;

public class L15_dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] grid = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++)
            Arrays.fill(grid[i], Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int time = sc.nextInt();
            grid[s][e] = time;
        }
        for (int i = 0; i <= n; i++)
            System.out.println(Arrays.toString(grid[i]));

        // minDist记录：源点到每个节点的最近距离；最后minDist[n]就是起点到终点的距离；
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0; // 源点到源点的距离就是0

        boolean[] isVisited = new boolean[n + 1];
        // 遍历所有节点；

        // 如果要打印边连接情况, parent[i] 表示从源点到达节点 i 的最短路径中，i 的父节点是谁
        int[] parent = new int[n + 1]; // 从节点1到节点n；
        Arrays.fill(parent, -1);

        for (int i = 1; i <= n; i++) {
            int minValue = Integer.MAX_VALUE;
            int cur = -1;

            for (int j = 1; j <= n; j++) {
                if (!isVisited[j] && minDist[j] < minValue) {
                    minValue = minDist[j];
                    cur = j;
                }
            }

            isVisited[cur] = true;


            for (int j = 1; j <= n; j++) {
                // 遍历所有节点，节点A未访问 && 当前指向节点A有效（权值非最大值） && 权值 + 源点距离当前节点的最小距离 < 源点距离节点A的最小距离
                // 更新源点到节点A的最小距离
                if (!isVisited[j] && grid[cur][j] != Integer.MAX_VALUE &&
                        grid[cur][j] + minDist[cur] < minDist[j]) {
                    minDist[j] = grid[cur][j] + minDist[cur];
                    parent[j] = cur; // parent[j] 其他节点j的父节点是cur；
                    System.out.println("cur: " + cur + "->" + j + " update minDist: ");
                    System.out.println(Arrays.toString(minDist));
                }

            }

            System.out.println("cur: " + cur + " result");
            System.out.println(Arrays.toString(isVisited));
            System.out.println(Arrays.toString(minDist));
        }

        // System.out.println(Arrays.toString(isVisited));
        // System.out.println(Arrays.toString(minDist));
        if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minDist[n]);
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(parent[i] + "->" + i);
        }
    }

}
