package com.ben.followcarl.c12_graph;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-08  19:03
 * @Description:
 * 并查集解决的问题:
 * 主要就是集合问题，两个节点在不在一个集合，也可以将两个节点添加到一个集合中。
 * 并查集主要有三个功能。
 *
 * 1. 寻找根节点，find(int u)，也就是判断这个节点的祖先节点是哪个
 * 2. 将两个节点接入到同一个集合，join(int u, int v)，将两个节点连在同一个根节点上
 * 3. 判断两个节点是否在同一个集合，函数：isSame(int u, int v)，就是判断两个节点是不是同一个根节点
 *
 * 这道题:题目说是无向图，返回一条可以删去的边，使得结果图是一个有着N个节点的树（即：只有一个根节点）。
 * 如果有多个答案，则返回二维数组中最后出现的边。
 *
 * 从前向后遍历每一条边（因为优先让前面的边连上），边的两个节点如果不在同一个集合，就加入集合（即：同一个根节点）。
 *
 * @Version: 1.0
 */
public class L15_684_findRedundantConnection {
    class Solution {
        private int n = 1005;
        private int[] father = new int[n];

        private void init() {
            for (int i = 0; i < n; i++) father[i] = i;
        }
        private int find(int u) {
            return u == father[u] ? u : (father[u] = find(father[u]));
        }

        private boolean isSame(int u, int v) {
            return find(u) == find(v);
        }

        private void join(int u, int v) {
            u = find(u);
            v = find(v);
            if (u == v) return;
            father[v] = u;
        }

        public int[] findRedundantConnection(int[][] edges) {
            init();
            for (int i = 0; i < edges.length; i++) {
                if (isSame(edges[i][0], edges[i][1])) return edges[i];
                else join(edges[i][0], edges[i][1]);
            }

            return null;
        }
    }
}
