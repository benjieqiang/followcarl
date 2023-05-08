package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-08  14:34
 * @Description: 105. 从前序与中序遍历序列构造二叉树
 * @Version: 1.0
 */
public class L16_105_BuildTree {
    private TreeNode travesal(int[] preorder, int[] inorder) {
        if (inorder.length == 0) return null;
        // 前序第一个元素是根节点
        int val = preorder[0];
        TreeNode root = new TreeNode(val);
        // 中序数组切割
        int index = 0;
        for (; index < inorder.length; index++) {
            if (inorder[index] == val) break;
        }
        // 切割前序数组和中序数组
        int[] leftInOrder = Arrays.copyOfRange(inorder, 0, index);
        int[] rightInOrder = Arrays.copyOfRange(inorder, index + 1, inorder.length);
        System.out.println(leftInOrder);
        System.out.println(rightInOrder);
        int[] leftPreOrder = Arrays.copyOfRange(preorder, 1, index + 1);
        int[] rightPreOrder = Arrays.copyOfRange(preorder, index + 1, preorder.length);
        System.out.println(leftPreOrder);
        System.out.println(rightPreOrder);
        // 迭代
        root.left = travesal(leftPreOrder, leftInOrder);
        root.right = travesal(rightPreOrder, rightInOrder);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        return travesal(preorder, inorder);
    }
    @Test
    public void testBuildTree() {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode node = buildTree(preorder, inorder);
        System.out.println(node);
    }
}
