package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-21  09:36
 * @Description: 题目描述
 * https://programmercarl.com/kamacoder/0108.%E5%86%97%E4%BD%99%E8%BF%9E%E6%8E%A5.html#%E6%80%9D%E8%B7%AF
 * 有一个图，它是一棵树，他是拥有 n 个节点（节点编号1到n）和 n - 1 条边的连通无环无向图（其实就是一个线形图），如图：
 * 现在在这棵树上的基础上，添加一条边（依然是n个节点，但有n条边），使这个图变成了有环图，如图
 * 输入描述
 * 第一行包含一个整数 N，表示图的节点个数和边的个数。
 * <p>
 * 后续 N 行，每行包含两个整数 s 和 t，表示图中 s 和 t 之间有一条边。
 * <p>
 * 输出描述
 * 输出一条可以删除的边。如果有多个答案，请删除标准输入中最后出现的那条边。
 * 输入示例
 * 3
 * 1 2
 * 2 3
 * 1 3
 * 输出示例
 * 1 3
 * 提示信息
 * <p>
 * 图中的 1 2，2 3，1 3 等三条边在删除后都能使原图变为一棵合法的树。但是 1 3 由于是标准输出里最后出现的那条边，所以输出结果为 1 3
 * 数据范围：
 * 1 <= N <= 1000.
 * <p>
 * 思路：
 * 并查集解决的问题：：两个节点是否在一个集合，也可以将两个节点添加到一个集合中。
 * <p>
 * 题目说是无向图，删一条边 = 有着N个节点的树（即：只有一个根节点）。
 * 如果有多个答案，则返回二维数组中最后出现的边。
 * <p>
 * 从前向后遍历每一条边（因为优先让前面的边连上，即加入集合），边的两个节点如果不在同一个集合，就加入集合（即：同一个根节点）。
 * 如果边的两个节点已经出现在同一个集合里，说明着边的两个节点已经连在一起了，再加入这条边一定就出现环了。
 * <p>
 * 已经判断 节点A 和 节点B 在同一个集合（同一个根），如果将 节点A 和 节点B 连在一起就一定会出现环。
 * @Version: 1.0
 */

import java.util.*;

public class L12_RedundantUndirectedConnection {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        UnionFind union = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();

            // 不再一个集合就join成一个集合，也就是拥有共同根，如果在同一个集合，就有同一个根（路径压缩，所有父节点会指向根节点），那再join进去，就会出现环，所以也就是说找到了那条待删边
            if (union.isSame(s, t)) System.out.println(s + " " + t);
            else union.join(s, t);
        }
    }
}