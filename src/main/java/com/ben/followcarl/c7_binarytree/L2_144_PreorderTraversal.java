package com.ben.followcarl.c7_binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class L2_144_PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> res  = new LinkedList<>();
        if (root == null) return res;
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.removeFirst();
            res.add(node.val);
            // 入栈，右边结点先入栈后出，左结点后入先出，这样可以实现中左右的遍历顺序。
            if (node.right != null) stack.addFirst(node.right);
            if (node.left != null) stack.addFirst(node.left);
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        L2_144_PreorderTraversal l2_144_preorderTraversal = new L2_144_PreorderTraversal();

        List<Integer> res = l2_144_preorderTraversal.preorderTraversal(node1);
        System.out.println(res);
    }
}
/*
1. stack放入5，不为空，进入while循环，弹出5之后加入结果集res中，此时stack = [], res = [5];
2. 由于5结点下面有左右孩子，所以先放入右孩子6，再放入左孩子4，此时stack = [6,4], res = [5];
3. stack不为空，所以，拿出栈顶元素4，放入结果集，此时stack = [6], res = [5,4];
4. 由于左节点4，有左右孩子1和2，所以分别放入栈中，此时stack = [6,2,1], res = [5,4];
5. stack不为空，拿出栈顶元素1，放入结果集，此时stack = [6,2], res = [5,4,1];
6. 由于1结点无左右孩子，所以直接进入while循环，依次拿出栈顶结点2和6，res = [5,4,1,2,6];
* */