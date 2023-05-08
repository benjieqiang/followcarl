package com.ben.followcarl.c7_binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-07  21:09
 * @Description: TODO
 * @Version: 1.0
 */
public class L15_113_pathSum {
    // res用来存最终的结果，innerList是保存中间结果的；必须是LinkedList，因为可以删除末尾元素；
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> innerList = new LinkedList<>();
    public List<List<Integer>> pathSum (TreeNode root,int targetSum) {
        traverse(root, targetSum);
        return res;
    }
    private void traverse(TreeNode root,  int count) {
        if (root == null) return;
        innerList.add(root.val);
        count -= root.val;
        if (root.left == null && root.right == null && count == 0) {
            res.add(new LinkedList<>(innerList));
        }
        traverse(root.left, count);
        traverse(root.right, count);
        innerList.removeLast(); // 回溯
    }

    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6= new TreeNode(4);
        TreeNode node7= new TreeNode(7);
        TreeNode node8= new TreeNode(2);
        TreeNode node9= new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node6.right = node9;

        int targetSum = 22;
        List<List<Integer>> res = new L15_113_pathSum().pathSum(node1, targetSum);
        System.out.println(res);

    }
}
