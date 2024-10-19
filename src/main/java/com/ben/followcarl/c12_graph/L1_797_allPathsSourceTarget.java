package com.ben.followcarl.c12_graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-31  17:44
 * @Description: dfs: 不到南墙不回头,再回溯.
 * bfs: 一圈一圈的搜
 * 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
 * <p>
 * graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
 * graph[0] = {1,2}表示可以从0结点访问节点1和2. 从graph[0]列表中依次找从节点0到 graph[0][0], graph[0][1]的边；
 * graph[1] = {3}表示可以从1节点访问节点3;
 * graph[3] = {} 表示3号节点是终点, 不能访问任何节点.
 * 输入：graph = [[1,2],[3],[3],[]]
 * 输出：[[0,1,3],[0,2,3]]
 * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
 * <p>
 * 思路: 深搜三部曲
 * 1. 确定递归函数和参数: dfs, 返回值为空,
 * 参数: 收集符合结果的集合res; 0节点到终点的路径: path; graph: 图; node: 当前的节点;
 * 2. 终止条件: 当找到一条路径即 我们到达最后一个元素: 3 == graph.length - 1;
 * 题目要求是从节点0开始到n-1结束;
 * 3. 单层递归逻辑:
 * for [0, graph[node].length) for循环的是每个node的长度.
 * 加入当前节点到结果集中, 递归for循环传入该节点
 * @Version: 1.0
 */
public class L1_797_allPathsSourceTarget {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        path.add(0); // 注意点：题目要求从节点0开始，所以是先加入0，如果要求从1开始，则加入1；
        dfs(graph, 0); // 从节点0开始找
        return res;
    }

    private void dfs(int[][] graph, int begin) {
        // 到达最后一个元素，收获结果
        if (begin == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历当层graph[begin]的所有可达节点
        for (int i = 0; i < graph[begin].length; i++) {
            path.add(graph[begin][i]);
            dfs(graph, graph[begin][i]);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void testAllPath() {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        // 从节点0开始找到末尾n-1节点，则path先加入0，dfs从0节点开始遍历，表示从0下标一直找，结束条件是begin == length - 1;
        System.out.println(allPathsSourceTarget(graph));
    }

    @Test
    public void testFrom1tonPath() {
        int[][] graph = {{}, {3, 2}, {4}, {5}, {5}};
        // 从节点1开始找到末尾n节点，则path先加入1，dfs从1节点开始遍历，表示从1下标一直找，结束条件是begin == length;
        System.out.println(allPathsSourceTarget(graph));

    }
}
