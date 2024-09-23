package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

/**
 * @description: 110。平衡二叉树 https://leetcode.cn/problems/balanced-binary-tree/
 * 要求的是高度还是深度：首先明白二叉树的高度和深度的概念：
 *
 * 二叉树的高度是：某个节点到叶子节点的节点数；所以从上往下数的话高度依次是4，3，2，1。后序遍历，从下往上；
 * 二叉树的深度是：根节点到该节点的节点数；所以从上往下数的话深度依次是1，2，3，4。前序遍历；从上往下；
 *
 * 二叉树的最大深度，最大深度就是从根节点到最远的叶子结点的距离，也就是说二叉树根节点的高度（根节点到叶子节点的距离）就是最大深度。
 * 所以采用的是后序遍历，左右中的顺序。
 *
 * @author: benjieqiang
 * @date: 2023/4/7 11:24 PM
 * @version: 1.0
 */
public class L9_110_IsBalanced {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return getHeight(root) == -1 ? false : true;
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) return -1;
        // 如果某个节点的左右子树的高度差已经大于1，直接返回-1；否则求该节点的最大深度： 1 + 左右孩子中的最大值(以当前节点为根节点的最大值才是树的高度)
        return Math.abs(leftHeight - rightHeight) > 1 ? -1 : (1 + Math.max(leftHeight, rightHeight));
    }

    public boolean isBalanced2(TreeNode root) {
        if (root == null) return true;
        int left = getHeight2(root.left);
        int right = getHeight2(root.right);
        if (Math.abs(left - right) > 1) return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }
    private int getHeight2(TreeNode root) {
        if (root == null) return 0;
        int left = getHeight2(root.left);
        int right = getHeight2(root.right);

        return Math.max(left, right) + 1;
    }
    @Test
    public void testIsBalanced() {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        System.out.println(isBalanced2(node1));

    }

}
