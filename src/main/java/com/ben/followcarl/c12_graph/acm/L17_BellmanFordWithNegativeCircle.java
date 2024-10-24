package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-24  09:21
 * @Description:
 *
 * 方法1: 在 bellman_ford 算法中，松弛 n-1 次所有的边 就可以求得 起点到任何节点的最短路径，松弛 n 次以上，
 * minDist数组（记录起到到其他节点的最短距离）中的结果也不会有改变。
 * 在原来的基础上，再多松弛一次，看minDist数组 是否发生变化。
 * @Version: 1.0
 */

import java.util.*;

public class L17_BellmanFordWithNegativeCircle {

    static class Edge {
        int from;
        int to;
        int value;

        Edge(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Edge> edges = new ArrayList<>(n + 1);

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            int v = sc.nextInt();
            edges.add(new Edge(s, t, v));
        }

        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // true is circle;
        boolean flag = false;


        for (int i = 1; i <= n; i++) {
            for (Edge edge : edges) {
                int from = edge.from;
                int to = edge.to;
                int value = edge.value;
                if (i < n) {
                    if (minDist[from] != Integer.MAX_VALUE && minDist[from] + value < minDist[to]) {
                        minDist[to] = minDist[from] + value;
                    }
                } else {
                    if (minDist[from] != Integer.MAX_VALUE && minDist[from] + value < minDist[to]) {
                        flag = true;
                    }
                }

            }
        }

        if (flag) {
            System.out.println("circle");
            return;
        }
        if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected");
            return;
        }

        System.out.println(minDist[n]);

    }
}
