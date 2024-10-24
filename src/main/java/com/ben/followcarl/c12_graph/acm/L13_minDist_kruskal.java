package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-22  11:30
 * @Description: kruscal的思路：
 * <p>
 * 边的权值排序，因为要优先选最小的边加入到生成树里
 * 遍历排序后的边
 * 如果边首尾的两个节点在同一个集合，说明如果连上这条边图中会出现环，则跳过。
 * 如果边首尾的两个节点不在同一个集合，加入到最小生成树，并把两个节点加入同一个集合
 * 并查集就是来处理两个节点是否是一个集合的问题的。通过find找根，如果根不一样，则说明不在一个集合，加入树，加入这两个节点。res加值；
 * <p>
 * 怎么存？每个节点的连接情况
 * 使用类，定义left，right， 权值value 表明left指向right，权值是value;
 * <p>
 * 整体存储成一个List结构
 *
 * 1->2
 * 1->3
 * 2->6
 * 3->4
 * 4->5
 * 5->7
 * @Version: 1.0
 */

import java.util.*;

public class L13_minDist_kruskal {

    static class Edge {
        int l;
        int r;
        int value;

        Edge(int l, int r, int value) {
            this.l = l;
            this.r = r;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int value = sc.nextInt();

            edges.add(new Edge(v1, v2, value));
        }

        // System.out.println(edges);

        edges.sort((e1, e2) -> (e1.value - e2.value));

        UnionFind union = new UnionFind(v + 1);

        // 存储最小生成树的边
        List<Edge> resList = new ArrayList<>(); // 存储最小生成树的边

        int res = 0;
        for (Edge edge : edges) {
            int l = edge.l;
            int r = edge.r;
            int value = edge.value;

            if (union.find(l) != union.find(r)) {
                res += value;
                union.join(l, r);
                resList.add(edge);
            }
        }

        System.out.println(res);

        // 打印最小生成树的边
        for(Edge edge : resList) {
            System.out.println(edge.l + "->" + edge.r);
        }
    }

    static class UnionFind {
        private int[] father;

        UnionFind(int n) {
            father = new int[n + 1];
            for (int i = 0; i <= n; i++) father[i] = i;
        }

        public int find(int u) {
            return u == father[u] ? u : (father[u] = find(father[u]));
        }

        public void join(int u, int v) {
            u = find(u);
            v = find(v);
            if (u == v) return;
            father[v] = u;
        }
    }
}
