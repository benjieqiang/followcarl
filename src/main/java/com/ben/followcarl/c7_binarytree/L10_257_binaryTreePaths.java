package com.ben.followcarl.c7_binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 257. 二叉树的所有路径
 * @author: benjieqiang
 * @date: 2023/4/7 11:45 PM
 * @version: 1.0
 */
public class L10_257_binaryTreePaths {

    //迭代法，一个栈来同时把当前节点和路径加入。遍历整个栈，直到左右孩子为空，则把当前路径加入到结果集；
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Object> stack = new LinkedList<>();
        // 结点和路径同时入栈
        stack.addFirst(root);
        stack.addFirst(root.val + "");
        while (!stack.isEmpty()) {
            String path = (String)stack.removeFirst();
            TreeNode node = (TreeNode)stack.removeFirst();

            if (node.left == null && node.right == null) res.add(path);
            if (node.left != null) {
                stack.addFirst(node.left);
                stack.addFirst(path + "->" + node.left.val);
            }
            if (node.right != null) {
                stack.addFirst(node.right);
                stack.addFirst(path + "->" + node.right.val);
            }
        }

        return res;
    }
}