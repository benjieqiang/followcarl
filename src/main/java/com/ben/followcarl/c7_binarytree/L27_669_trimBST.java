package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

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
            // 当前节点的值小于最小边界,那么它的左孩子肯定也不满足边界, 则递归右孩子，返回符合条件的右孩子到上层节点，挂到他的左孩子上，这样完成了
            // 不符合条件的节点的删除；
            TreeNode right = trimBST(root.right, low, high);
            return right;
        }
        if (root.val > high) {
            // 当前节点的值大于最大边界,那它的右孩子也不满足边界, 左孩子可能满足,需要判断. 返回左孩子到上一层节点赋值给上层节点的右孩子。
            TreeNode left = trimBST(root.left, low, high);
            return left;
        }

        //若 root.val 符合要求，则 root 可被保留，递归处理其左右节点并重新赋值即可。
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }

    public TreeNode trimBST2(TreeNode root, int low, int high) {
        // 1. 找到root在[low,high]的第一个节点
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        // 没有满足条件的节点。
        if (root == null) return null;
        // 从root出发，只有有左孩子，左孩子不在low范围内，则让node指向下一个左孩子的右节点。也就是删除了node.left节点
        for (TreeNode node = root; node.left != null; ) {
            if (node.left.val < low) {
                node.left = node.left.right;
            } else {
                node = node.left;
            }
        }
        // 同理，删除在high外的右节点。
        for (TreeNode node = root; node.right != null; ) {
            if (node.right.val > high) {
                node.right = node.right.left;
            } else {
                node = node.right;
            }
        }
        return root;
    }

    public TreeNode trimBST4(TreeNode root, int low, int high) {
        if (root == null) return null;
        // if root.val < low, left subtree < low, handle right subtree.
        if (root.val < low) return trimBST4(root.right, low, high);
        // if root.val > high, right subtree > high. not in [low, high], handle left subtree;
        if (root.val > high) return trimBST4(root.left, low, high);
        // root val in [low, high], recursive root.left and root.right;
        root.left = trimBST4(root.left, low, high);
        root.right = trimBST4(root.right, low, high);
        return root;
    }
    @Test
    public void test_TrimBST() {
        //root = [3,0,4,null,2,null,null,1]
        TreeNode root = new TreeNode(3);
        TreeNode root1 = new TreeNode(0);
        TreeNode root2 = new TreeNode(4);
        TreeNode root3 = new TreeNode(2);
        TreeNode root4 = new TreeNode(1);
        root.left = root1;
        root.right = root2;
        root1.right = root3;
        root3.left = root4;

        System.out.println(trimBST2(root, 1, 3));
    }
}
