package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-23  11:27
 * @Description: 94. 城市间货物运输 I
 * 题目描述
 * 某国为促进城市间经济交流，决定对货物运输提供补贴。共有 n 个编号为 1 到 n 的城市，通过道路网络连接，网络中的道路仅允许从某个城市单向通行到另一个城市，不能反向通行。
 * <p>
 * <p>
 * <p>
 * 网络中的道路都有各自的运输成本和政府补贴，道路的权值计算方式为：运输成本 - 政府补贴。权值为正表示扣除了政府补贴后运输货物仍需支付的费用；权值为负则表示政府的补贴超过了支出的运输成本，实际表现为运输过程中还能赚取一定的收益。
 * <p>
 * <p>
 * <p>
 * 请找出从城市 1 到城市 n 的所有可能路径中，综合政府补贴后的最低运输成本。如果最低运输成本是一个负数，它表示在遵循最优路径的情况下，运输过程中反而能够实现盈利。
 * <p>
 * <p>
 * <p>
 * 城市 1 到城市 n 之间可能会出现没有路径的情况，同时保证道路网络中不存在任何负权回路。
 * <p>
 * 输入描述
 * 第一行包含两个正整数，第一个正整数 n 表示该国一共有 n 个城市，第二个整数 m 表示这些城市中共有 m 条道路。
 * <p>
 * 接下来为 m 行，每行包括三个整数，s、t 和 v，表示 s 号城市运输货物到达 t 号城市，道路权值为 v （单向图）。
 * <p>
 * 输出描述
 * 如果能够从城市 1 到连通到城市 n， 请输出一个整数，表示运输成本。如果该整数是负数，则表示实现了盈利。如果从城市 1 没有路径可达城市 n，请输出 "unconnected"。
 * 输入示例
 * 6 7
 * 5 6 -2
 * 1 2 1
 * 5 3 1
 * 2 5 2
 * 2 4 -3
 * 4 6 4
 * 1 3 5
 * 输出示例
 * 1
 * 提示信息
 * <p>
 * <p>
 * 示例中最佳路径是从 1 -> 2 -> 5 -> 6，路上的权值分别为 1 2 -2，最终的最低运输成本为 1 + 2 + (-2) = 1。
 * <p>
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 4 2
 * 1 2 -1
 * 3 4 -1
 * <p>
 * 在此示例中，无法找到一条路径从 1 通往 4，所以此时应该输出 "unconnected"。
 * <p>
 * <p>
 * <p>
 * 数据范围：
 * <p>
 * 1 <= n <= 1000；
 * 1 <= m <= 10000;
 * <p>
 * -100 <= v <= 100;
 *
 * 4 5
 * 1 2 -1
 * 2 3 3
 * 2 5 2
 * 2 4 2
 * 4 5 -3
 *
 * @Version: 1.0
 */

import java.util.*;

public class L16_Bellman_ford_SPFA {
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
                    if (isVisited[to] == false) {  // 如果节点不在队列中，则加入队列
                        queue.add(to);
                        isVisited[to] = true;
                    }
                }
            }
        }

        if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected");
        } else {
            System.out.println(minDist[n]);
        }
    }

}
