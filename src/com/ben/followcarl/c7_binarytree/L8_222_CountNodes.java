package com.ben.followcarl.c7_binarytree;

public class L8_222_CountNodes {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }

    // 利用普通二叉树，后序遍历，分别求出一个结点左子树的孩子数，再求出右孩子的数目；求和加上当前结点数（1）；就是该层结点的数目；
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftCount = dfs(root.left);
        int rightCount = dfs(root.right);
        return 1 + leftCount + rightCount;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;
        int res = new L8_222_CountNodes().countNodes(node1);
        System.out.println(res);
    }
}
