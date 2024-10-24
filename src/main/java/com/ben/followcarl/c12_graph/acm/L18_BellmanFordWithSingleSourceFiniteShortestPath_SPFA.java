package com.ben.followcarl.c12_graph.acm;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-24  11:38
 * @Description: 96. 城市间货物运输 III
 * 题目描述
 * 某国为促进城市间经济交流，决定对货物运输提供补贴。共有 n 个编号为 1 到 n 的城市，通过道路网络连接，网络中的道路仅允许从某个城市单向通行到另一个城市，不能反向通行。
 * <p>
 * <p>
 * <p>
 * 网络中的道路都有各自的运输成本和政府补贴，道路的权值计算方式为：运输成本 - 政府补贴。权值为正表示扣除了政府补贴后运输货物仍需支付的费用；权值为负则表示政府的补贴超过了支出的运输成本，实际表现为运输过程中还能赚取一定的收益。
 * <p>
 * <p>
 * <p>
 * 请计算在最多经过 k 个城市的条件下，从城市 src 到城市 dst 的最低运输成本。
 * <p>
 * 输入描述
 * 第一行包含两个正整数，第一个正整数 n 表示该国一共有 n 个城市，第二个整数 m 表示这些城市中共有 m 条道路。
 * <p>
 * 接下来为 m 行，每行包括三个整数，s、t 和 v，表示 s 号城市运输货物到达 t 号城市，道路权值为 v。
 * <p>
 * 最后一行包含三个正整数，src、dst、和 k，src 和 dst 为城市编号，从 src 到 dst 经过的城市数量限制。
 * <p>
 * 输出描述
 * 输出一个整数，表示从城市 src 到城市 dst 的最低运输成本，如果无法在给定经过城市数量限制下找到从 src 到 dst 的路径，则输出 "unreachable"，表示不存在符合条件的运输方案。
 * 输入示例
 * 6 7
 * 1 2 1
 * 2 4 -3
 * 2 5 2
 * 1 3 5
 * 3 5 1
 * 4 6 4
 * 5 6 -2
 * 2 6 1
 * 输出示例
 * 0
 * 提示信息
 * 从 2 -> 5 -> 6 中转一站，运输成本为 0。
 * <p>
 * 1 <= n <= 1000；
 * <p>
 * 1 <= m <= 10000;
 * <p>
 * -100 <= v <= 100;
 * @Version: 1.0
 */
public class L18_BellmanFordWithSingleSourceFiniteShortestPath_SPFA {
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

        for (int i = 0; i <= n; i++) {
            grid.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            int v = sc.nextInt();
            // 1->{{from=1,to=2,value=1},{from =1,to=3,value=5}}
            grid.get(s).add(new Edge(s, t, v));
        }
        // 最后一行；求从src到dest的最多经过k个城市，也就是经过k+1条边的最小距离，决定了松弛多少条边
        int src = sc.nextInt();
        int dest = sc.nextInt();
        int k = sc.nextInt();

        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[src] = 0; // 源点到自己的最小距离就是0；

        // 队列记录已访问的节点；
        Deque<Integer> queue = new LinkedList<>();
        queue.add(src); // 将起点加入队列

        int queueSize = 0;
        // 记录上一次计算的minDist结果；
        int[] preMinDist = new int[n + 1];


        while (k-- > 0 && queue.isEmpty()) {
            boolean[] isVisited = new boolean[n + 1]; // 每一轮松弛中，控制节点不用重复入队列
            preMinDist = minDist.clone(); // clone创建一个新数组，两个变量指向不同的数组对象，修改一个不会影响另一个。
            queueSize = queue.size();
            while (queueSize-- > 0) {
                int cur = queue.remove();
                List<Edge> edges = grid.get(cur);
                for (Edge edge : edges) {
                    int from = edge.from;
                    int to = edge.to;
                    int value = edge.value;
                    // 防止从未计算过的节点出发，计算下一个的最小距离
                    if (preMinDist[from] != Integer.MAX_VALUE && value + preMinDist[from] < minDist[to]) {
                        minDist[to] = value + preMinDist[from];
                        if (isVisited[src]) continue;
                        isVisited[src] = true;
                        queue.add(to);
                    }
                }
            }
        }
        if (minDist[dest] == Integer.MAX_VALUE) {
            System.out.println("unreachable");
        } else {
            System.out.println(minDist[dest]);
        }
    }
}
