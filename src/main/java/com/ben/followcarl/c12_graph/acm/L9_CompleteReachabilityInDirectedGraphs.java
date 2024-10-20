package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-20  10:40
 * @Description: 105. 有向图的完全可达性
 * 题目描述
 * 给定一个有向图，包含 N 个节点，节点编号分别为 1，2，...，N。现从 1 号节点开始，如果可以从 1 号节点的边可以到达任何节点，则输出 1，否则输出 -1。
 * 输入描述
 * 第一行包含两个正整数，表示节点数量 N 和边的数量 K。 后续 K 行，每行两个正整数 s 和 t，表示从 s 节点有一条边单向连接到 t 节点。
 * 输出描述
 * 如果可以从 1 号节点的边可以到达任何节点，则输出 1，否则输出 -1。
 * 输入示例
 * 4 4
 * 1 2
 * 2 1
 * 1 3
 * 2 4
 * 输出示例
 * 1
 * <p>
 * <p>
 * 1. 首先辨别从节点1到所有节点（2,...N节点）的可达性,需要N+1大小的数组；
 * 2. dfs/bfs寻找整个list中从1到N节点，看是否能到，能访问到的话，记录当前节点visited[] = true;所以visited数组也需要n+1的长度；
 * 3. 遍历visited，如果有false说明有节点从1到该节点访问不到，否则就输出1；
 * 1.1 使用邻接表如何存
 * List<List<Integer>> graph = new ArrayList<>(n + 1);
 * 先根据节点从1到N往path中加入1-N个list；
 *  for (遍历边长）
 *      int s = sc.nextInt();
 *      int t = sc.nextInt();
 *      graph.get(s).add(t); // 从s指向t；
 * <p>
 * 1.2 邻接矩阵如何存
 *  // 节点从1到n，所以申请 n+1 这么大的数组
 *  int[][] graph = new int[n + 1][n + 1];
 *  <p>
 *  for (int i = 0; i < m; i++) {
 *      int s = scanner.nextInt();
 *      int t = scanner.nextInt();
 *      // 使用邻接矩阵表示有向图，1 表示 s 与 t 是相连的
 *  graph[s][t] = 1;
 *  }
 * <p>
 * dfs逻辑：
 * // 写法一：处理当前访问的节点
 * void dfs(List<List<Integer>> graph, int key, boolean[] visited) {
 * if (visited[key]) {
 *      return;
 * }
 * visited[key] = true;
 * List<Integer> keys = graph.get(key);
 * for (int key : keys) {
 * // 深度优先搜索遍历
 * dfs(graph, key, visited);
 * }
 * }
 * 1. // 写法二：处理下一个要访问的节点. 首节点1一定要先赋值成true；
 * void dfs(List<List<Integer>> graph, int key, boolean[] visited) {
 *      List<Integer> keys = graph.get(key); // 拿到该节点可以访问的节点列表；
 *      for (int key : keys) {
 *          if (visited[key] == false) { // 下个节点未访问，则访问，继续从下个节点出发，看下个节点；
 *              visited[key] = true;
 *              dfs(graph, key, visited);
 *          }
 *      }
 * }
 * <p>
 * <p>
 * bfs逻辑：
 * 定义队列，存储Integer节点值；
 * 1. 加入首节点1到队列，标记可达；
 * 2. 遍历队列，依次取出该节点可访问的节点列表，遍历，如果没有访问，则进行访问，入队，标记可达；
 * <p>
 * <p>
 * 写完检查：
 * 1. 方法名，修饰符typo；
 * 2. 变量名；
 * 3. 比较时，使用==而不是=；
 * @Version: 1.0
 */

import java.util.*;

public class L9_CompleteReachabilityInDirectedGraphs {


    private static void dfs(List<List<Integer>> graph, boolean[] visited, int start) {
        List<Integer> list = graph.get(start);
        for (int node : list) {
            if (visited[node] == false) {
                visited[node] = true;
                dfs(graph, visited, node);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= k; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            graph.get(s).add(t); // s->t;
        }
        boolean[] visited = new boolean[n + 1]; // from 1 -> n;

        // System.out.println(Arrays.toString(visited));
        visited[1] = true; // 节点1 预先处理
        // from node 1->n
        dfs(graph, visited, 1);

        for (int i = 1; i < n; i++) {
            if (visited[i] == false) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(1);
    }

}
