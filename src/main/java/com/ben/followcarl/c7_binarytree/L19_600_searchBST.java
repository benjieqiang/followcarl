package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-08  21:49
 * @Description: BST的搜索；
 * 二叉搜索树是一个有序树：
 * 若它的左子树不空，则左子树上所有孩子的值均小于中节点
 * 若它的右子树不空，则右子树上所有孩子的值均大于中节点
 * 同时：左、右子树也分别为二叉搜索树
 * @Version: 1.0
 */
public class L19_600_searchBST {

    class Solution {
        /**
         * @param root:
         * @param val:
         * @return TreeNode
         * @description 后序遍历，
         * @author benjieqiang
         * @date 2024/2/20 10:00 AM
         */
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) return null;
            if (val > root.val) return searchBST(root.right, val);
            if (val < root.val) return searchBST(root.left, val);
            return root;
        }
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return root;
        if (root.val == val) return root;
        if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    //迭代法
    /*
        对于一般二叉树，递归过程中还有回溯的过程，例如走一个左方向的分支走到头了，那么要调头，在走右分支。
        而对于二叉搜索树，不需要回溯的过程，因为节点的有序性就帮我们确定了搜索的方向。
    * */
    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null) return root;
        while (root != null) {
            if (root.val > val) root = root.left;
            else if (root.val < val) root = root.right;
            else return root;
        }
        return root;
    }




    @Test
    public void testSearchBST() {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        searchBST(node1, 2);

    }

}
