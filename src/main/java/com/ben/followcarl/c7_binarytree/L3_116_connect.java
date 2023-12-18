package com.ben.followcarl.c7_binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-11-12  21:12
 * @Description: 把每一层的节点next指针指向右边节点，直至为空。
 * @Version: 1.0
 * 为了先让右边的连上null指针，所以先把右边节点加入队列；再加入左边节点；
 */
public class L3_116_connect {
    class Solution {
        // Definition for a Node.
        class Node {
            public int val;
            public Node left;
            public Node right;
            public Node next;

            public Node() {}

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, Node _left, Node _right, Node _next) {
                val = _val;
                left = _left;
                right = _right;
                next = _next;
            }
        };
        public Node connect(Node root) {
            if (root == null) return null;

            Deque<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                Node preNode = null;
                for (int i = 0; i < size; i++) {
                    Node node = queue.remove();
                    node.next = preNode;
                    if (node.right != null) queue.add(node.right); // 右
                    if (node.left != null) queue.add(node.left); // 左
                    preNode = node;
                }
            }
            return root;
        }
    }
}
