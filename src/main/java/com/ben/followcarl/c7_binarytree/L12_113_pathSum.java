package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-07  21:09
 * @Description: 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * @Version: 1.0
 */
public class L12_113_pathSum {
    // res用来存最终的结果，innerList是保存中间结果的；必须是LinkedList，因为可以删除末尾元素；
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> inner = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        traversal(root, targetSum);
        return res;
    }

    private void traversal(TreeNode root, int count) {
        inner.add(root.val);
        if (root.left == null && root.right == null && count == root.val) {
            // 收集结果
            res.add(new LinkedList<>(inner));
        }
        // 空节点不遍历
        if (root.left != null) {
            traversal(root.left, count - root.val);
            inner.removeLast();
        }

        if (root.right != null) {
            traversal(root.right, count - root.val);
            inner.removeLast();
        }
    }

    @Test
    public void testPathSum() {

        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node6.right = node9;

        int targetSum = 22;
        List<List<Integer>> res = pathSum(node1, targetSum);
        System.out.println(res);

    }
}
