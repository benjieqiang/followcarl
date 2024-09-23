package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-13  22:03
 * @Description: 给定二叉树的根节点 root ，返回所有左叶子之和。
 * 遍历当前节点，收集的是它的左孩子。
 * 理解左孩子的定义: A节点不为空且A节点的左孩子没有左右孩子,那么A节点的左孩子就是左叶子
 * @Version: 1.0
 */
public class L10_404_sumOfLeftLeaves {

    // bfs:
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.remove();
                if (node != null && node.left != null && node.left.left == null && node.left.right == null) {
                    res += node.left.val;
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return res;
    }
    // dfs,采用递归的方法:
    // 左叶子节点: 他不为空,他没有左右孩子
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;
        int leftValue = sumOfLeftLeaves2(root.left);
        if (root.left != null && root.left.left == null && root.left.right == null) {
            leftValue = root.left.val;
        }
        int rightValue = sumOfLeftLeaves2(root.right);

        return leftValue + rightValue;
    }


    class Solution {
        /**
         * @param root:
         * @return int
         * @description 如果求所有节点，那就是当前节点值 + 左孩子的所有节点值 + 右孩子的所有节点值；
         * 加上条件：只求左孩子：遍历到当前节点，它的左孩子不为空，且左孩子的左右孩子为空，则说明找到了左节点，得到的左孩子值加上当前节点右孩子的所求节点；
         * @author benjieqiang
         * @date 2024/2/19 3:09 PM
         */
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 0;
            if (root.left != null && root.left.left == null && root.left.right == null) {
                return root.left.val + sumOfLeftLeaves(root.right);
            }

            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
    }

    @Test
    public void testSum() {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        System.out.println(sumOfLeftLeaves2(node1));

    }
}
