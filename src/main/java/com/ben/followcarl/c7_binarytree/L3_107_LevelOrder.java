package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class L3_107_LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(list);
        }
        return res;
    }

    // dfs
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        dfs(root, res, 0); //0:depth
        return res;
    }

    private void dfs(TreeNode node, List<List<Integer>> res, int depth) {
        if (node == null) return;
        if (res.size() == depth) res.add(new LinkedList<>());

        res.get(depth).add(node.val);
        dfs(node.left, res, depth + 1);
        dfs(node.right, res, depth + 1);
    }
    /*
    前序遍历：
        从根节点开始，遍历左子树，再遍历右子树。
        在遍历过程中，利用 depth 参数记录当前节点所在的深度，然后将节点值添加到对应深度的列表中。
        如果当前深度对应的列表不存在，则创建一个新的列表。最终返回层序遍历结果列表 res。
     */

    @Test
    public void testLevelOrderTraversal() {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;


        List<List<Integer>> res = levelOrder2(node1);
        System.out.println(res);
    }
}