package com.ben.followcarl.c12_graph.acm;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-24  09:21
 * @Description: 方法1: 在 bellman_ford 算法中，松弛 n-1 次所有的边 就可以求得 起点到任何节点的最短路径，松弛 n 次以上，minDist数组（记录起到到其他节点的最短距离）中的结果也不会有改变。
 * 在原来的基础上，再多松弛一次，看minDist数组 是否发生变化。
 *
 *
 * @Version: 1.0
 */
public class L17_BellmanFordWithNegativeCircle_SPFA {
    static class Edge {
        int from;
        int to;
        int value;

        Edge(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Edge{");
            sb.append("from=").append(from);
            sb.append(", to=").append(to);
            sb.append(", value=").append(value);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        // 邻接表+SPFA. 高效存储+快速算法, 图越稀疏，SPFA的效率就越高。
        List<List<Edge>> grid = new ArrayList<>(n + 1);

        for(int i = 0; i <= n; i++) {
            grid.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            int v = sc.nextInt();
            // 1->{{from=1,to=2,value=1},{from =1,to=3,value=5}}
            grid.get(s).add(new Edge(s, t, v));
        }

        // 最短距离数组初始化
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // 队列记录已访问的节点；
        Deque<Integer> queue = new LinkedList<>();
        queue.add(1); // 将起点加入队列

        // 如果图中节点访问过，则置true
        boolean[] isVisited = new boolean[n + 1];

        // true is circle;
        boolean flag = false;
        // 记录每个节点加了几次；
        int[] count = new int[n + 1];
        count[1]++; // 节点1加1次

        // record
        while(!queue.isEmpty()) {
            int cur = queue.remove();
            // 从队列中移除一个节点 cur 时，这个节点标记为false，不在队列了
            isVisited[cur] = false;

            List<Edge> edges = grid.get(cur);

            for (Edge edge : edges) {
                int from = edge.from;
                int to = edge.to;
                int value = edge.value;

                if (minDist[to] > minDist[from] + value) { // 开始松弛
                    minDist[to] = minDist[from] + value;
                    count[to]++;
                    if (count[to] == n) {
                        flag = true;
                        while (!queue.isEmpty()) queue.remove();
                        break;
                    }
                    if (isVisited[to] == false) {  // 如果节点不在队列中，则加入队列
                        queue.add(to);
                        isVisited[to] = true;
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
