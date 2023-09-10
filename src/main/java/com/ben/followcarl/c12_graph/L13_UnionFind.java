package com.ben.followcarl.c12_graph;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-08  11:45
 * @Description: 并查集模板
 *
 * 这里对路径压缩版并查集来做分析。
 *
 * 空间复杂度： O(n) ，申请一个father数组。
 *
 * 关于时间复杂度，如果想精确表达出来需要繁琐的数学证明，就不在本篇讲解范围内了，大家感兴趣可以自己去深入去研究。
 *
 * 这里做一个简单的分析思路。
 *
 * 路径压缩后的并查集时间复杂度在O(logn)与O(1)之间，且随着查询或者合并操作的增加，时间复杂度会越来越趋于O(1)。
 *
 * 了解到这个程度对于求职面试来说就够了。
 * 在第一次查询的时候，相当于是n叉树上从叶子节点到根节点的查询过程，时间复杂度是logn，但路径压缩后，后面的查询操作都是O(1)，
 * 而 join 函数 和 isSame函数 里涉及的查询操作也是一样的过程。
 * @Version: 1.0
 */
public class L13_UnionFind {

    private int n; // 根据题目中节点数量而定，一般比节点数量大一点就好
    private int[] father;

    // 并查集初始化
    public void init(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    // 并查集里寻根的过程: 未路径压缩, 递归不断找当前结点,直到找到根结点; 根节点的特征: 自己指向自己;
    int find2(int u) {
        if (u == father[u]) return u; // 自己指向自己，直接返回
        else return find(father[u]); // 如果根不是自己，就根据数组下标一层一层向下找
    }

    // 并查集寻根: 采用路径压缩的方法; 如果发现当前结点不是根结点,就把它往上挪, 减少树的层数;
    // 挪的方法是: 修改当前结点的指向, 一层一层往上, 最后形成一个根结点,下面全是子节点的树;查找是O(1)
    public int find(int u) {
        return u == father[u] ? u : (father[u] = find(father[u])); // 路径压缩
    }

    // 判断u和v是否找到同一个根
    public boolean isSame(int u, int v) {
        return find(u) == find(v);
    }

    // 加u v这条边介入到并查集;
    public void join(int u, int v) {
        u = find(u); // 寻找u的根
        v = find(v); // 寻找v的根
        if (u == v) return; // 如果发现根相同，则说明在一个集合，不用两个节点相连直接返回
        father[v] = u; // 关联
    }

    @Test
    public void testFind2() {
        init(1005);
        // 在这里可以进行并查集的操作
        join(1, 2); // 得到2的father是1;
        join(2, 3); // 得到1的father是3;

        // 看1和3的父节点是否一致, 是的;
        System.out.println(isSame(1, 3)); // true
    }

    @Test
    public void testFind() {
        init(1005);
        join(1, 8);
        join(3, 8);
        join(1, 7);
        join(8, 5);
        join(6, 2);
        join(2, 9);

        System.out.println(isSame(8, 7)); // true
        System.out.println(isSame(7, 2)); // false
    }
}
