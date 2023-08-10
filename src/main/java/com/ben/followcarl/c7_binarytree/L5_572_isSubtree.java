package com.ben.followcarl.c7_binarytree;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-09  22:22
 * @Description: 判断一个树的子树
 * @Version: 1.0
 */
public class L5_572_isSubtree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null && subRoot != null) return false;

        // 分别判断大树的根左右节点为顶点的子树是否和所给子树一样
        return compare(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right != null) return false;
        if (left != null && right == null) return false;
        if (left == null && right == null) return true;
        if (left.val != right.val) return false;

        return compare(left.left, right.left) && compare(left.right, right.right);
    }
}
