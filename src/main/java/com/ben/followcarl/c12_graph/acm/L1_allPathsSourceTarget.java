package com.ben.followcarl.c12_graph.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-18  11:50
 * @Description:
 * 给定一个有 n 个节点的有向无环图，节点编号从 1 到 n。
 * 请编写一个函数，找出并返回所有从节点 1 到节点 n 的路径。每条路径应以节点编号的列表形式表示。
 *
 * 输入描述
 * 第一行包含两个整数 N，M，表示图中拥有 N 个节点，M 条边
 *
 * 后续 M 行，每行包含两个整数 s 和 t，表示图中的 s 节点与 t 节点中有一条路径
 *
 * 输出描述
 * 输出所有的可达路径，路径中所有节点之间空格隔开，每条路径独占一行，存在多条路径，路径输出的顺序可任意。如果不存在任何一条路径，则输出 -1。
 *
 * 注意输出的序列中，最后一个节点后面没有空格！ 例如正确的答案是 `1 3 5`,而不是 `1 3 5 `， 5后面没有空格！
 *
 * 输入示例
 * 5 5
 * 1 3
 * 3 5
 * 1 2
 * 2 4
 * 4 5
 *
 * 找有向图的所有路径；
 * 1. 两种方法接收数组，邻接矩阵/邻接表，两种方法的dfs也不一样；
 *  邻接表： [[], [3, 2], [4], [5], [5]]。
 *      int n = sc.nextInt();
 *      int m = sc.nextInt();
 *
 *      List<List<Integer>> graph = new ArrayList<>(n + 1);
 *      // graph size = n + 1;
 *      // 加入n+1行，表示1-n个节点；graph首行是空的。
 *      for (int i = 0; i <= n; i++) {
 *          graph.add(new LinkedList<>());
 *      }
 *
 *      // 根据m条边，加入节点关系from s->t;
 *      while (m-- > 0) {
 *          int s = sc.nextInt();
 *          int t = sc.nextInt();
 *
 *          graph.get(s).add(t); // s->t
 *      }
 *
 *  2. dfs从节点1开始到节点n结束，所以遍历二维数组时先给path加入节点1，表示从1开始
 *   1. 当x == n 收获结果，是copy一份path到res;
 *   2. 遍历graph的每一行graph.get(x)，从0遍历到每一行末尾；
 *      1. 先入path，2. 再递归下个节点，即找graph.get(x).get(i)节点的下一个路径；
 *      3. 回溯：删除path末尾节点；
 *
 * 邻接矩阵：
 *       Scanner scanner = new Scanner(System.in);
 *         int n = scanner.nextInt();
 *         int m = scanner.nextInt();
 *
 *         // 节点从1到n，所以申请 n+1 这么大的数组
 *         int[][] graph = new int[n + 1][n + 1];
 *
 *         for (int i = 0; i < m; i++) {
 *             int s = scanner.nextInt();
 *             int t = scanner.nextInt();
 *             // 使用邻接矩阵表示有向图，1 表示 s 与 t 是相连的
 *             graph[s][t] = 1;
 *         }
 *     2. dfs从节点1到节点n结束
 *     1. x == n 收获结果
 *     2. 从哪开始到哪结束》for循环找从x节点出发依次到1,...n节点的关系
 *     如果graph[x][i] = 0，表示从开始节点x到节点i没有连接，跳过
 *     否则，把i加入path，继续从i节点开始递归
 *     回溯：path移除末尾；
 *
 *
 * 3. 输出时：如果结果集没有返回-1，结果集每条路径打印出来，末尾没空格；
 *         for (List list : res) {
 *             for (int i = 0; i < list.size() - 1; i++) {
 *                 System.out.print(list.get(i) + " ");
 *             }
 *             System.out.println(list.get(list.size() - 1));
 *         }
 * @Version: 1.0
 */

import java.util.*;

class L1_allPathsSourceTarget {
    static List<List<Integer>> res = new LinkedList<>();
    static List<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>(n + 1);
        // graph size = n + 1;
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }

        // only init from s->t;
        while (m-- > 0) {
            int s = sc.nextInt();
            int t = sc.nextInt();

            graph.get(s).add(t); // s->t
        }

        // System.out.println(graph);
        path.add(1); // begins at 1;
        dfs(graph, 1, n);

        // not find
        if (res.isEmpty()) System.out.println(-1);

        // out
        for (List list : res) {
            for (int i = 0; i < list.size() - 1; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println(list.get(list.size() - 1));
        }
    }

    private static void dfs(List<List<Integer>> graph, int x, int n) {
        if (x == n) {
            res.add(new LinkedList<>(path));
            return;
        }

        // x既是起始节点，又是每一行的下标，这样能找到每一行对应的下一个节点
        for (int i = 0; i < graph.get(x).size(); i++) {
            path.add(graph.get(x).get(i));
            dfs(graph, graph.get(x).get(i), n);
            path.remove(path.size() - 1);
        }
    }
}


class Main_AdjacencyMatrix {
    static List<List<Integer>> res = new LinkedList<>();
    static List<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 节点从1到n，所以申请 n+1 这么大的数组
        int[][] graph = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int s = scanner.nextInt();
            int t = scanner.nextInt();
            // 使用邻接矩阵表示有向图，1 表示 s 与 t 是相连的
            graph[s][t] = 1;
        }

        // System.out.println(graph);
        path.add(1); // begins at 1;
        dfs(graph, 1, n);

        // not find
        if (res.isEmpty()) System.out.println(-1);

        // out
        for (List list : res) {
            for (int i = 0 ; i < list.size() - 1; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println(list.get(list.size() - 1));
        }
    }

    // 找从节点x到n节点的路径；
    private static void dfs(int[][] graph, int x, int n) {
        if (x == n) {
            res.add(new LinkedList<>(path));
            return;
        }

        for (int i = 1; i <= n; i++) {
            // 0表示从x出发到i节点不相连
            if (graph[x][i] == 0) continue;
            path.add(i);
            dfs(graph, i, n);
            path.remove(path.size() - 1);
        }
    }

}

