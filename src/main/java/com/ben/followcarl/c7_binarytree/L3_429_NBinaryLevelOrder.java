package com.ben.followcarl.c7_binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-11-10  20:10
 * @Description: N层二叉树遍历；
 * @Version: 1.0
 */
public class L3_429_NBinaryLevelOrder {
    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> res = new LinkedList<>();
            if (root == null) return res;

            Deque<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> inner = new LinkedList<>();
                for (int i = 0; i < size; i++) {
                    Node node = queue.remove();
                    inner.add(node.val);
                    List<Node> childList = node.children;
                    if (childList == null) continue;

                    for (int j = 0; j < childList.size(); j++) {
                        if (childList.get(j) != null) {
                            queue.add(childList.get(j));
                        }
                    }
                }
                res.add(inner);
            }
            return res;
        }
    }

    // Definition for a Node.

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}

