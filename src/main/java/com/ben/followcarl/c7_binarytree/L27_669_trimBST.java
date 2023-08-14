package com.ben.followcarl.c7_binarytree;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-14  19:54
 * @Description: 669. 修剪二叉搜索树
 * @Version: 1.0
 */
public class L27_669_trimBST {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val < low) {
            // 当前节点的值小于最小边界,那么它的左孩子肯定也不满足边界, 但是右孩子需要递归判断
            TreeNode right = trimBST(root.right, low, high);
            return right;
        }
        if (root.val > high) {
            // 当前节点的值大于最大边界,那它的右孩子也不满足边界, 左孩子可能满足,需要判断
            TreeNode left = trimBST(root.left, low, high);
            return left;
        }

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }

}
