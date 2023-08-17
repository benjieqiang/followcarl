package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-16  18:36
 * @Description: 给你一棵二叉树的前序遍历和中序遍历结果，要求你写出这棵二叉树的后序遍历结果。
 * 输入:
 * 输入包含多组测试数据。每组输入包含两个字符串，分别表示二叉树的前序遍历和中序遍历结果。每个字符串由不重复的大写字母组成。
 * DBACEGF ABCDEFG
 * BCAD CBAD
 * 输出:
 * 对于每组输入，输出对应的二叉树的后续遍历结果。
 * ACBFGED
 * CDAB
 * @Version: 1.0
 */

import java.util.HashMap;
import java.util.Scanner;

public class L21_PostOrderTree {
    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        Character val;

        TreeNode(Character val) {
            this.val = val;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] str = sc.nextLine().split(" ");
            String preOrder = str[0];
            String inOrder = str[1];

            TreeNode res = buildTree(preOrder.toCharArray(), inOrder.toCharArray());
            printTree(res);
            System.out.println();
        }
    }

    private static void printTree(TreeNode root) {
        if (root == null) return;
        printTree(root.left);
        printTree(root.right);
        System.out.print(root.val);
    }

    private static TreeNode buildTree(char[] preorder, char[] inorder) {
        // 利用map来存中序数组的结果, 快速找到切割点
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        TreeNode res = buildTreeTraversal(preorder, inorder, 0, 0, inorder.length - 1, map);

        return res;
    }

    private static TreeNode buildTreeTraversal(char[] preorder, char[] inorder,
                                               int preStart, int inStart, int inEnd,
                                               HashMap<Character, Integer> map) {
        if (preStart > preorder.length - 1 || inStart > inEnd) return null;

        // 根节点
        Character rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // 得到切割点
        int rootIndex = map.get(rootVal);

        // 切割左右子树: 左闭右闭: preStart + rootIndex - inStart + 1 计算方式: 前序的起点: preStart+1, 加上中序数组切割的长度, rootIndex - 1 - inStart + 1;
        root.left = buildTreeTraversal(preorder, inorder, preStart + 1, inStart, rootIndex - 1, map);
        root.right = buildTreeTraversal(preorder, inorder, preStart + rootIndex - inStart + 1, rootIndex + 1, inEnd, map);
        return root;
    }


}
