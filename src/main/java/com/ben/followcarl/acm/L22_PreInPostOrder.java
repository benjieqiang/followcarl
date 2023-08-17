package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-16  20:53
 * @Description: 给出一个n个节点的二叉树，请求出二叉树的前序遍历，中序遍历和后序遍历。
 * 输入:
 * 第一位一个整数n(0<n<=26)，表示二叉树有n个节点, 以下n行
 * 每行第一个为一个大写字母表示节点，后面为两整数，
 * 第一个表示左儿子序号
 * 第二个表示右儿子序号，如果该序号为0表示没有。
 * (例如下面示例中，F序号为1，C序号为2，E序号为3，A序号为4，D序号为5，G序号为6，B序号为7)
 * 7
 * F 2 3
 * C 4 5
 * E 0 6
 * A 0 0
 * D 7 0
 * G 0 0
 * B 0 0
 * 输出:
 * 共三行，第一行为二叉树的前序遍历，第二行为中序遍历，第三行为后序遍历
 * <p>
 * FCADBEG
 * ACBDFEG
 * ABDCGEF
 * @Version: 1.0
 */


import java.util.Scanner;

public class L22_PreInPostOrder {
    static class TreeNode {
        char val;
        int left;
        int right;

        public TreeNode(char val, int left, int right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static TreeNode[] nodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        nodes = new TreeNode[n + 1];
        for (int i = 0; i < n; i++) {
            char val = sc.next().charAt(0); // 提取本行字符串的第一个字符;
            int left = sc.nextInt();
            int right = sc.nextInt();
            nodes[i + 1] = new TreeNode(val, left, right);
        }
        preorder(1);
        System.out.println();
        inorder(1);
        System.out.println();
        postorder(1);
        System.out.println();
        sc.close();
    }

    private static void postorder(int root) {
        if (root == 0)
            return;
        postorder(nodes[root].left);
        postorder(nodes[root].right);
        System.out.print(nodes[root].val);
    }

    private static void inorder(int root) {
        if (root == 0)
            return;
        inorder(nodes[root].left);
        System.out.print(nodes[root].val);
        inorder(nodes[root].right);
    }

    private static void preorder(int root) {
        if (root == 0)
            return;
        System.out.print(nodes[root].val);
        preorder(nodes[root].left);
        preorder(nodes[root].right);
    }

}
