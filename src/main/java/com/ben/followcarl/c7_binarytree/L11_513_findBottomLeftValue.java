package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @description: https://leetcode.cn/problems/find-bottom-left-tree-value/
 * @author: benjieqiang
 * @date: 2023/4/9 10:52 PM
 * @version: 1.0
 */
public class L11_513_findBottomLeftValue {


    // 层序遍历,很简单, 层序遍历模板题, 每次把结果值更新成最左边的节点的值；
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (i == 0) res = node.val; //保证每一层第一个结点；
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return res;
    }

    // 递归如何解决?
    // 最大深度的叶子节点,
    // 左中右，前序遍历，在求二叉树的最大深度加上判断，如果深度大于最大深度且没有左右孩子节点，即到达最底层叶子节点，就收集结果。并更新最大深度；
    int maxDepth = Integer.MIN_VALUE; // 记录叶子节点的最大深度
    int res = 0;

    public int findBottomLeftValue2(TreeNode root) {
        if (root == null) return 0;
        traversal(root, 1);
        return res;
    }

    private void traversal(TreeNode root, int depth) {
        if (depth > maxDepth && root.left == null && root.right == null) {
            // 收集结果
            maxDepth = depth;
            res = root.val;
        }
        if (root.left != null) traversal(root.left, depth + 1);
        if (root.right != null) traversal(root.right, depth + 1);
    }

    @Test
    public void testFind() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node5.left = node7;

        System.out.println(findBottomLeftValue2(node1));
    }

    @Test
    public void testFind2() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;


        System.out.println(findBottomLeftValue2(node1));
    }
}
