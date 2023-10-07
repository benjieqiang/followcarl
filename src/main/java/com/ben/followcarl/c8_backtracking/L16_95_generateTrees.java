package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-22  17:32
 * @Description: 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树。可以按 任意顺序 返回答案。
 * @Version: 1.0
 */
public class L16_95_generateTrees {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<TreeNode>();

        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int left, int right) {
        List<TreeNode> res = new LinkedList<>();
        if (left > right) {
            res.add(null);
            return res;
        }

        for (int i = left; i <= right; i++) {
            List<TreeNode> leftTrees = generateTrees(left, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, right);
            for (TreeNode node1 : leftTrees) {
                for (TreeNode node2 : rightTrees) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = node1;
                    cur.right = node2;
                    res.add(cur);
                }
            }
        }
        return res;
    }

    @Test
    public void testTree() {
        int n = 3;
        List<TreeNode> treeNodes = generateTrees(n);
        System.out.println(treeNodes);
    }
}
