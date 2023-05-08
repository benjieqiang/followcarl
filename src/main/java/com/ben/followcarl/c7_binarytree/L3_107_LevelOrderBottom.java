package com.ben.followcarl.c7_binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class L3_107_LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeLast();
                list.add(node.val);
                if (node.left != null) queue.addFirst(node.left);
                if (node.right != null) queue.addFirst(node.right);
            }
            res.addFirst(list);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        L3_107_LevelOrderBottom l3_107_levelOrderBottom = new L3_107_LevelOrderBottom();

        List<List<Integer>> res = l3_107_levelOrderBottom.levelOrderBottom(node1);
        System.out.println(res);
    }
}