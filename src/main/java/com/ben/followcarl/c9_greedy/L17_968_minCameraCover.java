package com.ben.followcarl.c9_greedy;


/**
 * @Author: benjieqiang
 * @CreateTime: 2024-10-08  14:50
 * @Description:
 * You are given the root of a binary tree.
 * We install cameras on the tree nodes where each camera at a node can monitor its parent,
 * itself, and its immediate children.
 *
 * Return the minimum number of cameras needed to monitor all nodes of the tree.
 * @Version: 1.0
 */
public class L17_968_minCameraCover {
    class Solution {
        int res = 0;
        public int minCameraCover(TreeNode root) {
            if (root == null) return 0;
            // 0: no cover; 1: camera; 2: cover
            if (minCame(root) == 0) res++;
            return res;
        }

        private int minCame(TreeNode node) {
            if (node == null) return 2;
            int left = minCame(node.left);
            int right = minCame(node.right);
            if (left == 2 && right == 2) {
                return 0;
            } else if (left == 0 || right == 0) {
                res++;
                return 1;
            } else {
                return 2;
            }
        }
    }
}
