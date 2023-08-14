package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

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

    /**
     * @param root:
     * @return List<String>
     * @description 迭代法：一个栈来同时把当前节点和路径加入。遍历整个栈，直到左右孩子为空，则把当前路径加入到结果集；
     * 前序遍历
     * @author benjieqiang
     * @date 2023/8/10 10:48 PM
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Object> stack = new LinkedList<>();
        // 结点和路径同时入栈
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {
            String path = (String)stack.pop(); //弹出"1"
            TreeNode node = (TreeNode)stack.pop(); // 弹出当前节点

            // 收集结果的，中
            if (node.left == null && node.right == null) res.add(path);
            if (node.left != null) { //左
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
            if (node.right != null) { //右
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
        }

        return res;
    }

    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();// 存最终的结果
        if (root == null) return res;
        traversal(root, "", res);
        return res;
    }

    private void traversal(TreeNode root, String str, List<String> res) {
        if (root == null) return;
        // 遇到叶子结点就停下来，收集结果
        str += root.val;
        if (root.left == null && root.right == null) {
            res.add(str);
        }
        // 递归和回溯是同时进行，这里入参执行完，自己就回溯了
        traversal(root.left, str + "->", res);
        traversal(root.right, str+"->", res);
    }

    @Test
    public void testBinaryTreePaths() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node2.right = node4;

        System.out.println(binaryTreePaths2(node1));

    }
}