package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-22  08:20
 * @Description:
 * 53. 寻宝（第七期模拟笔试）
 * 题目描述
 * 在世界的某个区域，有一些分散的神秘岛屿，每个岛屿上都有一种珍稀的资源或者宝藏。国王打算在这些岛屿上建公路，方便运输。
 *
 * 不同岛屿之间，路途距离不同，国王希望你可以规划建公路的方案，如何可以以最短的总公路距离将 所有岛屿联通起来（注意：这是一个无向图）。
 *
 * 给定一张地图，其中包括了所有的岛屿，以及它们之间的距离。以最小化公路建设长度，确保可以链接到所有岛屿。
 *
 * 输入描述
 * 第一行包含两个整数V 和 E，V代表顶点数，E代表边数 。顶点编号是从1到V。例如：V=2，一个有两个顶点，分别是1和2。
 *
 * 接下来共有 E 行，每行三个整数 v1，v2 和 val，v1 和 v2 为边的起点和终点，val代表边的权值。
 *
 * 输出描述
 * 输出联通所有岛屿的最小路径总距离
 *
 * 输入示例
 * 7 11
 * 1 2 1
 * 1 3 1
 * 1 5 2
 * 2 6 1
 * 2 4 2
 * 2 3 2
 * 3 4 1
 * 4 5 1
 * 5 6 2
 * 5 7 1
 * 6 7 1
 * 输出示例
 * 6
 * 测试用例2:
 * 10 11
 * 1 2 15
 * 2 7 65
 * 2 3 88
 * 4 5 48
 * 5 7 45
 * 6 7 78
 * 6 9 40
 * 7 8 22
 * 8 9 38
 * 8 10 35
 * 9 10 52
 *
 * 396
 *
 * 思路：
 * 1. 使用一个邻接矩阵接收无向图，两个节点互连 初始化大小为v+1
 * 2.v个节点只需要循环v-1次，即建立v-1条边，就能把v个节点串联；
    1. 选择距离生成树最近的节点，遍历节点1-节点v，这也是为啥isInTree, minDist初始化长度是v+1
        不在最小生成树中 && 距离最小生成树最近的节点；
    2. 将最近的节点加入生成树，刚开始还没有最小生成树，所以先加入节点1，即isInTree[1] = true;
    3. 更新非生成树节点到生成树的距离: 不在最小生成树中 && 最小生成树的节点cur到其他节点的距离 小于 这个其他节点到最小生成数的距离（这个距离只要没有被更新过就是（最大节点值+1）初始化的值）
    4. 统计结果， 从2号节点出发直到v节点。统计每个节点到最小生成树的最小距离之和；
 * @Version: 1.0
 */

import java.util.*;


public class L13_minDist_prim {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int e = scanner.nextInt();

        // 初始化邻接矩阵，所有值初始化为一个大值，表示无穷大
        int[][] grid = new int[v + 1][v + 1];
        for (int i = 0; i <= v; i++) {
            Arrays.fill(grid[i], 10001); // 顶点数：2 <= V <= 10000;
        }

        // 读取边的信息并填充邻接矩阵，x <->y 权值是k；
        for (int i = 0; i < e; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int k = scanner.nextInt();
            grid[x][y] = k;  // 无向图，两个节点互指；
            grid[y][x] = k;
        }

        for (int i = 0; i <= v; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
        int[] parent = new int[v + 1];
        Arrays.fill(parent, -1); // -1表明某个节点不指向他；

        // minDist[] 所有节点到最小生成树的最小距离.
        int[] minDist = new int[v + 1];
        Arrays.fill(minDist, 10001); // 初始化成最大节点值，这个值是某个节点到最小树的最小距离
        // ，如果初始化是节点数+1，那么当前最小树距离某个节点的距离是grid[cur][j], 如果大于节点值，则没法去记录最小距离。

        // 记录节点是否在树里
        boolean[] isInTree = new boolean[v + 1];

        // Prim算法主循环， v个节点只需要循环v-1次，即建立v-1条边，就能把v个节点串联；
        for (int i = 1; i < v; i++) {
            int cur = -1;
            int minVal = Integer.MAX_VALUE;

            // 1. 选择距离生成树最近的节点，遍历节点1-节点v，这也是为啥isInTree, minDist初始化长度是v+1
            for (int j = 1; j <= v; j++) {
                // 不在最小生成树中 && 距离最小生成树最近的节点；
                if (!isInTree[j] && minDist[j] < minVal) {
                    minVal = minDist[j];
                    cur = j;
                }
            }

            // 2. 将最近的节点加入生成树，刚开始还没有最小生成树，所以先加入节点1，即isInTree[1] = true;
            isInTree[cur] = true;

            // 3. 更新非生成树节点(其他节点）到生成树的距离: 不在最小生成树中 && 最小生成树的节点cur到其他节点A的距离 小于 这个其他节点A到最小生成数的距离
            for (int j = 1; j <= v; j++) {
                if (!isInTree[j] && grid[cur][j] < minDist[j]) {
                    minDist[j] = grid[cur][j];
                    parent[j] = cur; // 为啥不是parent[cur] = j？ 因为cur有可能有多个指向j的可能，这样做是在不断更新parent[cur]为最后指向的j节点；
                }
            }
        }

        // 4. 统计结果， 从2号节点出发直到v节点。统计每个节点到最小生成树的最小距离之和；
        int result = 0;
        for (int i = 2; i <= v; i++) {
            result += minDist[i];
        }
        // 输出 最小生成树边的链接情况
        for (int i = 1; i <= v; i++) {
            System.out.println(i + "->" + parent[i]);
        }
        System.out.println(result);
    }

}
