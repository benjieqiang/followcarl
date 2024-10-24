package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-21  09:57
 * @Description: 有一种有向树, 该树只有一个根节点，所有其他节点都是该根节点的后继。
 * 该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点。有向树拥有 n 个节点和 n - 1 条边。如图：
 * <p>
 * <p>
 * 现在有一个有向图，有向图是在有向树中的两个没有直接链接的节点中间添加一条有向边。如图：
 * <p>
 * 输入一个有向图，该图由一个有着 n 个节点(节点编号 从 1 到 n)，n 条边，请返回一条可以删除的边，使得删除该条边之后该有向图可以被当作一颗有向树。
 * <p>
 * 输入描述
 * 第一行输入一个整数 N，表示有向图中节点和边的个数。
 * <p>
 * 后续 N 行，每行输入两个整数 s 和 t，代表这是 s 节点连接并指向 t 节点的单向边
 * <p>
 * 输出描述
 * 输出一条可以删除的边，若有多条边可以删除，请输出标准输入中最后出现的一条边。
 * 输入示例
 * 3
 * 1 2
 * 1 3
 * 2 3
 * 输出示例
 * 2 3
 * 思路：
 * 题目理解：给有向图删一条边，让有向图-》有向树；若有多条边可以删除，请输出标准输入中最后出现的一条边。
 * 有向图 = 一颗有向树 + 一条有向边
 * 有向树的根节点入度为0，其他节点入度是1；因为该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点
 * 1. 找到入度为2的节点，删除最后一条边；比如节点3 的入度为2，删 1 -> 3 或者 2 -> 3 。选择删顺序靠后的2->3便可。
 * 2. 找到入度为2的节点，删除特定边才能构成有向图。比如节点3 的入度为 2，但在删除边的时候，只能删构成环的这条边（节点1 -> 节点3），如果删这条边（节点4 -> 节点3），那么删后本图也不是有向树了（因为找不到根节点）。
 * 3. 无入度为2的点，说明图中有环了（注意是有向环）。删除有环图的一边；
 *
 * 1. 使用List<int[]>edges 存储边 s->t ，注意从1开始
 * 2. inDegree[t]++, 计算从1开始到n节点的入度；
 * 3. 遍历所有的边int[] s->t，判断入t节点的度，如果=2，记录该节点的两条边到list，倒序插入；
 * 4. 判断有向图删除哪条边让他成为有向树？
 *  4.1 只要list size > 0,说明存在度2的节点，拿出最后出现的那条边。
 *  4.2 判断删除边之后是否是有向树，如何判断？使用并查集，遍历所有的边，遇到待删边跳过，如果不存在
 * @Version: 1.0
 */

import java.util.*;

public class L12_RedundantDirectedConnection {

    // 将所有边的两端节点分别加入并查集，遇到待删边则跳过（模拟删除），如果遇到即将加入并查集的边的两端节点 本来就在并查集了，说明构成了环。
    // 如果顺利将所有边的两端节点（除了要删除的边）加入了并查集，则说明 删除该条边 有向图成为有向树
    private static boolean isTreeAfterRemoveEdge(List<int[]> edges, int[] removeEdge) {
        UnionFind union = new UnionFind(edges.size() + 1);
        for (int[] edge : edges) {
            // 这个判断用来跳过输入中的 removeEdge，即模拟删除这条边，忽略这条边的处理。
            if (edge[0] == removeEdge[0] && edge[1] == removeEdge[1]) continue;
            // isSame 方法来判断边的两个端点是否属于同一个集合。
            // 如果不属于同一集合，表示这条边不会形成环，因此可以继续合并这两个节点。
            if (!union.isSame(edge[0], edge[1])) union.join(edge[0], edge[1]);
            // 这意味着有环的存在（因为已经连通的两个节点之间又有一条边），这时返回 false，表示删除某条边后，剩余的图不是一棵树。
            else return false;
        }
        // 如果遍历了所有边都没有形成环，则返回 true，表示删除这条边之后，剩下的图是无环且连通的，因此是树。
        return true;
    }
    // 确定图中一定有了有向环，删除的环的那条边：
    // 将所有边的两端节点分别加入并查集，如果遇到即将加入并查集的边的两端节点 本来就在并查集了，说明构成了环。
    // 3->2, 2->1, 1->3, 3->4。找图中的环，也就是看集合中两个点有没有共同根，有的话找到了环；返回；
    private static void getRemoveEdge(List<int[]> edges) {
        UnionFind union = new UnionFind(edges.size() + 1);
        for (int[] edge : edges) {
            if (!union.isSame(edge[0], edge[1])) {
                union.join(edge[0], edge[1]);
            } else {
                // 如果两个节点已经在同一个集合，说明这条边会形成环
                System.out.println(edge[0] + " " + edge[1]);
                return;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // edges
        List<int[]> edges = new ArrayList<>();
        // degree
        int[] inDegree = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            edges.add(new int[]{s, t});
            inDegree[t]++; // s->t, 记录每个节点t的入度，根节点是入度是0
        }
        // 记录入度为2的边，倒序插入。删除时删除首元素，就能按照选择删顺序靠后
        LinkedList<int[]> twoDegreesEdges = new LinkedList<>();
        // reverse edges get degree = 2 edges list
        for (int[] edge : edges) {
            // 如果发现有节点（edge[1]）的入度是2，则说明存在环，倒序插入
            if (inDegree[edge[1]] == 2) twoDegreesEdges.addFirst(edge);
        }

        // size > 0, degree = 2 exist;
        if (twoDegreesEdges.size() > 0) {
            // is Tree,
            int[] tmp;
            // 情况1；判断删除最后一条边是否是环，无环，直接删除入度为 2 的最后一条边，删除最后出现的那条边，也就是在twoDegreesEdges中的首个元素。
            if (isTreeAfterRemoveEdge(edges, twoDegreesEdges.get(0))) {
                tmp = twoDegreesEdges.get(0);
            } else {
                // 是环：删环的那一边，
                tmp = twoDegreesEdges.get(1);
            }
            System.out.println(tmp[0] + " " + tmp[1]);
            return;
        }
        // 无度为2的节点，则说明是一个有向环，删除构成环的边；找构成节点是不是属于同一个集合，属于则找到那条边了。
        getRemoveEdge(edges);
    }

}
