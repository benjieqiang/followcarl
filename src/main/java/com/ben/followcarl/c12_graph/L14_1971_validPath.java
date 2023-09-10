package com.ben.followcarl.c12_graph;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-08  18:44
 * @Description:
 * 有一个具有 n 个顶点的 双向 图，其中每个顶点标记从 0 到 n - 1（包含 0 和 n - 1）。图中的边用一个二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和顶点 vi 之间的双向边。 每个顶点对由 最多一条 边连接，并且没有顶点存在与自身相连的边。
 *
 * 请你确定是否存在从顶点 source 开始，到顶点 destination 结束的 有效路径 。
 *
 * 给你数组 edges 和整数 n、source 和 destination，如果从 source 到 destination 存在 有效路径 ，则返回 true，否则返回 false 。
 *
 * 思路:
 * 并查集主要有三个功能。
 *
 * 1. 寻找根节点，函数：find(int u)，也就是判断这个节点的祖先节点是哪个
 * 2. 将两个节点接入到同一个集合，函数：join(int u, int v)，将两个节点连在同一个根节点上
 * 3. 判断两个节点是否在同一个集合，函数：isSame(int u, int v)，就是判断两个节点是不是同一个根节点
 *
 * 各个点是双向图链接，那么判断 一个顶点到另一个顶点有没有有效路径其实就是看这两个顶点是否在同一个集合里。
 *
 * 如何算是同一个集合呢，有边连在一起，就算是一个集合。
 *
 * 此时我们就可以直接套用并查集模板。
 *
 * 使用join(int u, int v)将每条边加入到并查集。
 *
 * 最后 isSame(int u, int v) 判断是否是同一个根 就可以了。
 *
 * @Version: 1.0
 */
public class L14_1971_validPath {
    class Solution {
        int n = 200005;
        int[] father = new int[n];;
        private void init() {
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }

        private int find(int u) {
            return u == father[u] ? u : (father[u] = find(father[u]));
        }

        private void join(int u, int v) {
            u = find(u);
            v = find(v);
            if (u == v) return;
            father[u] = v;
        }

        private boolean isSame(int u, int v) {
            return find(u) == find(v);
        }

        public boolean validPath(int n, int[][] edges, int source, int destination) {
            init();
            for (int i = 0; i < edges.length; i++) {
                join(edges[i][0], edges[i][1]);
            }
            return isSame(source, destination);
        }
    }
}
