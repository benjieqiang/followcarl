package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class L4_226_InvertTree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        // dfs 递归：前后序都可以，
        new L4_226_InvertTree().invertTree(node1);
//        new L4_226_InvertTree().invertTree2(node1);
    }

    public TreeNode invertTree(TreeNode root) {
        // 1. 递归函数的入参和返回值为TreeNode；
        // 2. 递归结束的条件，为空则返回当前结点；
        if (root == null) return root;
        // 3. 单层递归的逻辑。
        swap(root);
        System.out.println(root.val);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    private void swap(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    // bfs 迭代
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            swap(node);
            // 这里，左右孩子顺序没有区别，无论是谁，都是以它为根节点交换它的左右孩子。
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return root;
    }

    // bfs
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.remove();
                swap(node); // 交换当前节点的左右孩子；
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return root;
    }

    @Test
    public void testInvertTree() {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        TreeNode treeNode = invertTree2(node1);

        System.out.println(treeNode);
    }
}