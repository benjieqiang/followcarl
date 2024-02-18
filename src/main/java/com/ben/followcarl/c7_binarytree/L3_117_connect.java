package com.ben.followcarl.c7_binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-11-12  21:12
 * @Description:
 * @Version: 1.0
 *
 * 与116不同的是，这个是非完全二叉树，使用bfs，代码完全一样，dfs代码有不同；
 *
 * BFS + 链表，实现O(1);
 */
public class L3_117_connect {
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
        // BFS + 链表；
        public Node connect2(Node root) {
            if (root == null) return root;
            Node cur = root; // cur每一层的链表头节点；
            while (cur != null) { // 开始遍历cur所在当前层；
                Node dummy = new Node(0); // dummy节点来方便操作；
                Node pre = dummy; // pre节点从dummy节点出发
                while (cur != null) { // 分别把当前层的左右孩子串起来，不断移动pre到最右边；
                    if (cur.left != null) {
                        pre.next = cur.left;
                        pre = pre.next;
                    }
                    if (cur.right != null) {
                        pre.next = cur.right;
                        pre = pre.next;
                    }
                    cur = cur.next; // 访问当前层的下一个节点；
                }
                cur = dummy.next; // dummy此时指向的是下一层，把dummy.next给cur，就是从下一层的头节点开始继续遍历；
            }
            return root;
        }
        class Solution2 {
            private final List<Node> pre = new ArrayList<>();

            public Node connect(Node root) {
                dfs(root, 0); // 根节点的深度为 0
                return root;
            }

            private void dfs(Node node, int depth) {
                if (node == null) {
                    return;
                }
                if (depth == pre.size()) { // node 是这一层最左边的节点
                    pre.add(node);
                } else { // pre[depth] 是 node 左边的节点
                    pre.get(depth).next = node; // node 左边的节点指向 node
                    pre.set(depth, node);
                }
                dfs(node.left, depth + 1);
                dfs(node.right, depth + 1);
            }
        }
    }
}
