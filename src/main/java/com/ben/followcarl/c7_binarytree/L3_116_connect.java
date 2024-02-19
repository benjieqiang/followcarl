package com.ben.followcarl.c7_binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-11-12  21:12
 * @Description: 把每一层的节点next指针指向右边节点，直至为空。
 * @Version: 1.0
 *
 * 方法2: 层序遍历的顺序，遍历每一层时，只要i小于size-1，则让他的next指针指向queue的getFirst()节点；这样就穿起来了；
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
        }

        public Node connect2(Node root) {
            if (root == null) return root;
            Deque<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node node = queue.remove();
                    if (i < size - 1) {
                        node.next = queue.getFirst();
                    }
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            }

            return root;
        }

        /**
         * @param root:
         * @return Node
         * @description dfs解法，传入当前节点，next节点；
         * @author benjieqiang
         * @date 2024/2/19 10:35 AM
         */
        public Node connect3(Node root) {
            if (root == null) return root;
            dfs(root, null);
            return root;
        }
        private void dfs(Node node, Node next) {
            if (node == null) return;
            node.next = next; // 当前节点指向next；
            dfs(node.left, node.right); // 孩子节点，left和right；
            dfs(node.right, node.next == null ? null : node.next.left); // 当前节点的右孩子和不是同一个父节点的左孩子连接
        }
    }
}
