package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-16  21:29
 * @Description: 现给定一棵二叉树的先序遍历序列和中序遍历序列，要求你计算该二叉树的高度。
 * 输入:
 * 输入包含多组测试数据，每组输入首先给出正整数N（<=50），为树中结点总数。
 * 下面2行先后给出先序和中序遍历序列，均是长度为N的不包含重复英文字母（区别大小写）的字符串。
 * 9
 * ABDFGHIEC
 * FDHGIBEAC
 * 7
 * Abcdefg
 * gfedcbA
 * 输出:
 * 5
 * 7
 * @Version: 1.0
 */

import java.util.Scanner;
import java.util.HashMap;

public class L23_BinaryTreeHeight {
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
            int n = sc.nextInt();
            String preorder = sc.next();
            String inorder = sc.next();
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < inorder.length(); i++) {
                map.put(inorder.charAt(i), i);
            }
            TreeNode root = buildTree(preorder, inorder, 0, preorder.length() - 1, 0, inorder.length() - 1, map);
            int res = getHeight(root);
            System.out.println(res);

        }
    }

    private static TreeNode buildTree(String preorder, String inorder,
                                      int startPre, int endPre, int startIn, int endIn,
                                      HashMap<Character, Integer> map) {
        if (startPre > endPre || startIn > endIn) return null;
        // 构造根节点
        char rootVal = preorder.charAt(startPre);
        TreeNode root = new TreeNode(rootVal);

        // 找rootIndex
        int rootIndex = map.get(rootVal);
        int subLength = rootIndex - startIn;
        // 切割
        root.left = buildTree(preorder, inorder, startPre + 1, startPre + subLength, startIn, rootIndex - 1, map);
        root.right = buildTree(preorder, inorder, startPre + 1 + subLength, endPre, rootIndex + 1, endIn, map);

        return root;
    }

    private static int getHeight(TreeNode root) {
        if (root == null) return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1;
    }

}
