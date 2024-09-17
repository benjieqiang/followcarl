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

        // BFS + O1的解法
        public Node connect(Node root) {
            if (root == null) return null;

            // 从根节点开始
            Node cur = root;
            // 遍历每一层
            // 我们只需要处理到倒数第二层，因为下一层是最后的叶子节点层，而最后一层已经没有子节点可以设置 next 指针。
            // 当 cur.left == null 时，表示我们已经到达叶子节点层，或者树为空，这时不需要进一步操作。
            // 如果我们使用 while (cur != null)，循环可能会在处理完最后一层之后再次执行，这会导致空指针异常（NullPointerException），因为叶子节点没有 left 或 right 子节点
            // 因为在满二叉树（Perfect Binary Tree）中，如果有左子节点，必然有右子节点，而在叶子节点层（最后一层），所有的节点都没有左右子节点，所以没有必要处理叶子节点的下一层
            while (cur.left != null) {
                // 遍历当前层的节点
                Node temp = cur;
                while (temp != null) {
                    // 连接当前节点的左右孩子
                    temp.left.next = temp.right;

                    // 连接当前节点的右孩子与下一个节点的左孩子
                    if (temp.next != null) {
                        temp.right.next = temp.next.left;
                    }

                    // 继续遍历当前层
                    temp = temp.next;
                }

                // 移动到下一层（当前层的最左节点）
                cur = cur.left;
            }

            return root;
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
            dfs(root, null); // 初始调用 dfs，next 为 null，因为根节点没有右侧相邻节点。
            return root;
        }
        private void dfs(Node node, Node next) {
            if (node == null) return;  // 递归的基准条件，节点为空时直接返回。
            node.next = next;  // 将当前节点的 next 指针指向传入的 next。
            // 递归连接左子节点，左子节点的 next 应该是右子节点
            dfs(node.left, node.right);
            // 递归连接右子节点，右子节点的 next 应该是当前节点的 next 节点的左子节点
            dfs(node.right, node.next == null ? null : node.next.left);
        }
    }

}
