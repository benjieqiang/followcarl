package com.ben.followcarl.c7_binarytree;

/**
 * @description: 110。平衡二叉树 https://leetcode.cn/problems/balanced-binary-tree/
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
        // 如果某个节点的左右子树的高度差已经大于1，直接返回-1；否则返回当前节点的高度： 1 + 左右孩子中的最大值；
        return Math.abs(leftHeight - rightHeight) > 1 ? -1 : (1 + Math.max(leftHeight, rightHeight));
    }
}
