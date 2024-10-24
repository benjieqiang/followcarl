package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-21  09:19
 * @Description: 107. 寻找存在的路径
 * 题目描述
 * 给定一个包含 n 个节点的无向图中，节点编号从 1 到 n （含 1 和 n ）。
 * <p>
 * <p>
 * <p>
 * 你的任务是判断是否有一条从节点 source 出发到节点 destination 的路径存在。
 * <p>
 * 输入描述
 * 第一行包含两个正整数 N 和 M，N 代表节点的个数，M 代表边的个数。
 * <p>
 * 后续 M 行，每行两个正整数 s 和 t，代表从节点 s 与节点 t 之间有一条边。
 * <p>
 * 最后一行包含两个正整数，代表起始节点 source 和目标节点 destination。
 * <p>
 * 输出描述
 * 输出一个整数，代表是否存在从节点 source 到节点 destination 的路径。如果存在，输出 1；否则，输出 0。
 * 输入示例
 * 5 4
 * 1 2
 * 1 3
 * 2 4
 * 3 4
 * 1 4
 * 输出示例
 * 1
 * <p>
 * 无向图，求从节点 source 到节点 destination 的路径是否存在；
 * 就是看这两个顶点是否在同一个集合里。想象并查集join的过程，
 * 此时我们就可以直接套用并查集模板。
 * <p>
 * 使用 join(int u, int v)将每条边加入到并查集。
 * <p>
 * 最后 isSame(int u, int v) 判断是否是同一个根 就可以了。
 * @Version: 1.0
 */

import java.util.*;

public class L11_validPath {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // union find
        UnionFind union = new UnionFind(n + 1);

        for (int i = 1; i <= m; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            union.join(s, t);
        }

        int source = sc.nextInt();
        int dest = sc.nextInt();


        if (union.isSame(source, dest)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }
}

class UnionFind {
    private int[] father;

    UnionFind(int n) {
        father = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            father[i] = i;
        }
    }


    int find(int u) {
        return u == father[u] ? u : (father[u] = find(father[u]));
    }

    boolean isSame(int u, int v) {
        return find(u) == find(v);
    }

    void join(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return;
        father[v] = u;
    }
}