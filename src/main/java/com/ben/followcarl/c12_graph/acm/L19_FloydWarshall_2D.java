package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-24  10:48
 * @Description:
 * 空间复杂度：从三维数组 O(n^3) 降低到二维数组 O(n^2)，空间开销大大减少。
 * 时间复杂度：时间复杂度依然保持为 O(n^3)，因为每一次松弛操作仍然需要三重循环，但内存消耗减少。
 * @Version: 1.0
 */

import java.util.Arrays;
import java.util.Scanner;

public class L19_FloydWarshall_2D {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 城市数量
        int m = sc.nextInt(); // 道路数量
        // 处理查询
        int z = sc.nextInt();  // 查询次数

        int[][] dist = new int[n + 1][n + 1];  // 二维数组

        // 初始化
        int INF = 10005;  // 因为边的最大距离是10^4
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                dist[i][j] = (i == j) ? 0 : INF;  // 自己到自己距离为0，其他为无穷大
//            }
//        }
        Arrays.fill(dist, INF);

        // 读入边
        for (int i = 0; i < m; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();
            int val = sc.nextInt();
            dist[p1][p2] = val;
            dist[p2][p1] = val;  // 注意这里是双向图
        }

        // 开始 Floyd-Warshall 算法
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        while (z-- > 0) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            if (dist[start][end] == INF) {
                System.out.println(-1);
            } else {
                System.out.println(dist[start][end]);
            }
        }

        sc.close();
    }

}
