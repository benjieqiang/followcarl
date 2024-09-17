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
        // BFS + 链表；不引入queue的做法。
        public Node connect2(Node root) {
            if (root == null) return root;
            Node cur = root;  // 当前处理的层的最左节点
            // 遍历每一层。使用dummy始终指向下一层的第一个节点，prev用来连接下一层的每一个节点
            while (cur != null) {
                Node dummy = new Node(0);  // 用于构建下一层的节点链表
                Node prev = dummy;  // prev 用于连接下一层的节点
                Node temp = cur;  // 当前层的遍历指针

                // 遍历当前层的每个节点， temp此时指向当前层；
                while (temp != null) {
                    // 有左孩子，让prev下一个指针指向左孩子，prev右移；
                    if (temp.left != null) {
                        prev.next = temp.left;
                        prev = prev.next;
                    }
                    // 有右孩子，让prev下一个指针指向右孩子，prev右移
                    if (temp.right != null) {
                        prev.next = temp.right;
                        prev = prev.next;
                    }
                    // 移动到当前层的下一个节点
                    temp = temp.next;
                }

                // 移动到下一层的最左节点，dummy.next依靠temp的移动来指向了下一层最左节点；
                // 在普通二叉树中，cur = cur.left 不能保证我们移动到下一层的最左节点，
                // 这是因为普通二叉树并不像完美二叉树那样每个节点都有左右子节点。
                // 假设某一层的某些节点没有左孩子或者右孩子，那么 cur.left 可能为空，导致无法正确遍历到下一层的最左节点。
                cur = dummy.next;
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
