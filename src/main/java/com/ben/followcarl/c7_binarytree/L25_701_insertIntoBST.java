package com.ben.followcarl.c7_binarytree;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-14  17:41
 * @Description: 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 *
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 *
 * 题意: 给一个bst, 插入一个值，这个值与二叉树的各个节点值不同, 得到一个新bst,返回新bst的根节点.
 *
 * 其实是插入到叶子节点;
 * @Version: 1.0
 */
public class L25_701_insertIntoBST {

    // 递归法
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            // 找到了要插入的位置
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    // 迭代法
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode cur = root;
        TreeNode pre = root; // 记录上一个节点
        while (cur != null) { // 用来不断寻找要插入节点的位置
            pre = cur;
            if (cur.val > val) cur = cur.left;
            else cur = cur.right;
        }
        TreeNode node = new TreeNode(val);
        if (pre.val > val) pre.left = node;
        else pre.right = node;

        return root;
    }


}
