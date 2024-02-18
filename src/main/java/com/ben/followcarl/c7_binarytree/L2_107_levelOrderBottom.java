package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-04  21:50
 * @Description: 二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[15,7],[9,20],[3]]
 *
 * @Version: 1.0
 */
public class L2_107_levelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>(); // 注意点：定义成LinkedList
        if (root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.addFirst(list); //借助addFirst方法，每次都往头加元素，这样就可以完成从底向上遍历。
        }
        return res;
    }


    // dfs:
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;

        dfs(root, res, 0);

        Collections.reverse(res); // 与层序遍历不同的地方，得到的结果需要反转一下；
        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> res, int depth) {
        if (root == null) return;
        if (res.size() == depth) res.add(new LinkedList<>());

        res.get(depth).add(root.val);
        dfs(root.left, res, depth + 1);
        dfs(root.right, res, depth + 1);
    }


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


        List<List<Integer>> res = levelOrderBottom2(node1);
        System.out.println(res);
    }
}
