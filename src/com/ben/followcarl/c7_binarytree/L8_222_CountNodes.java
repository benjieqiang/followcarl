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

    // 完全二叉树的性质，
    public int countNodes2(TreeNode root) {
        if(root == null) return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 0, rightDepth = 0;
        while (left != null) {
            left = left.left;
            leftDepth++;
        }
        while (right != null) {
            right = right.right;
            rightDepth++;
        }
        if (leftDepth == rightDepth) {
            return (2 << leftDepth) - 1;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;
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
