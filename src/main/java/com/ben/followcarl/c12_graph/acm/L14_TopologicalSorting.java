package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-22  12:12
 * @Description: 117. 软件构建
 * 题目描述
 * 某个大型软件项目的构建系统拥有 N 个文件，文件编号从 0 到 N - 1，在这些文件中，某些文件依赖于其他文件的内容，这意味着如果文件 A 依赖于文件 B，则必须在处理文件 A 之前处理文件 B （0 <= A, B <= N - 1）。请编写一个算法，用于确定文件处理的顺序。
 * 输入描述
 * 第一行输入两个正整数 N, M。表示 N 个文件之间拥有 M 条依赖关系。
 * <p>
 * 后续 M 行，每行两个正整数 S 和 T，表示 T 文件依赖于 S 文件。
 * <p>
 * 输出描述
 * 输出共一行，如果能处理成功，则输出文件顺序，用空格隔开。
 * <p>
 * 如果不能成功处理（相互依赖），则输出 -1。
 * <p>
 * 输入示例
 * 5 4
 * 0 1
 * 0 2
 * 1 3
 * 2 4
 * 输出示例
 * 0 1 2 3 4
 * 提示信息
 * <p>
 * 思路：
 * 1. 找到入度为0 的节点，加入结果集
 * 2. 将该节点从图中移除
 * 3. 如果出现环，有相互依赖情况，即结果集节点个数小于图中节点数，则输出-1
 * @Version: 1.0
 */

import java.util.*;

public class L14_TopologicalSorting {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> umap = new ArrayList<>();
        // 0 -> n-1 注意题目：节点从0到n-1，所以只需要n大小的数组存储他们的关系；
        for (int i = 0; i < n; i++) {
            umap.add(new ArrayList<>());
        }
        int[] inDegree = new int[n];

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            umap.get(s).add(t); // t依赖与s，说明s指向t，构建有向图
            inDegree[t]++; // 记录每个t的入度；
        }

        // System.out.println(Arrays.toString(inDegree));
        // System.out.println(umap);

        Deque<Integer> queue = new LinkedList<>();

        // 找到度为0的节点，就是出发点；加入队首；
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) queue.addFirst(i);
        }

        // 结果集
        List<Integer> resList = new ArrayList<>();

        // 队列不为空，则先拿出该节点；
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            resList.add(cur); // 弹出来就加入到结果集；

            // 入度为0的节点的指向list集合，比如0->1, 0->2
            List<Integer> list = umap.get(cur);
            if (list.size() > 0) { // 有指向则遍历
                for(int i = 0; i < list.size(); i++) {
                    int next = list.get(i);
                    inDegree[next]--; // 度减1，模拟移除；
                    if (inDegree[next] == 0) queue.addFirst(next);
                }
            }
        }

        // 如果是有向无环图，则结果集元素数等于节点数；
        if (resList.size() == n) {
            for (int i = 0; i < n; i++) {
                if (i == n - 1) {
                    System.out.println(resList.get(i));
                } else {
                    System.out.print(resList.get(i) + " ");
                }
            }
        } else {
            // 存在循环依赖，返回-1
            System.out.println(-1);
        }
    }
}
