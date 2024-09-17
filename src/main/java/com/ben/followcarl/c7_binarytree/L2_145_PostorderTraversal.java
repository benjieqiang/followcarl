package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class L2_145_PostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Deque<TreeNode> st = new LinkedList<>();
        st.push(root);
        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            res.push(node.val);
            if (node.left != null) st.push(node.left);
            if (node.right != null) st.push(node.right);
        }
        return res;
    }

    @Test
    public void testPostOrder() {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        List<Integer> res = postorderTraversal(node1);
        System.out.println(res);
    }
}
/*
* 1. 首先放入头节点stack = [5], 从栈中拿出栈顶元素5，放入res，stack = [], res = [5];
* 2. 由于根节点5有左右孩子，依次把左右孩子放入栈中；stack = [4,6], res = [5];
* 3. 进入下一步while循环，拿出栈顶元素结点6，此时由于linkedlist的性质，插入到头元素，stack = [4]， res = [6,5]
* 4. 由于结点6下面没有左右孩子，所以进入下一次while循环，拿出栈顶元素结点4，插入到res的头部，stack = []， res = [4,6,5],
* 5. 由于4结点有左右孩子，所以再把左右孩子放入栈中，stack = [1,2], res = [4,6,5]
* 6. 同理，依次把2和1放入到res中，res = [1,2,4,6,5];
* */