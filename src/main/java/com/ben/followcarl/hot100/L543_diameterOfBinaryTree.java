package com.ben.followcarl.hot100;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-11  17:52
 * @Description: 二叉树的直径
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 *
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 *
 * 两节点之间路径的 长度 由它们之间边数表示。
 *
 *
 * 输入：root = [1,2,3,4,5]
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 * 二叉树的直径：二叉树中从一个结点到另一个节点最长的路径，叫做二叉树的直径
 * 思路: 求直径?
 * 直径是两个节点之前的最长路径:两节点之间的边数;
 * 现在要求4节点和5节点的路径: 先求2->4的路径加上2->5的路径 = 2;
 *
 * @Version: 1.0
 */
public class L543_diameterOfBinaryTree {
    class Solution {
        int res;
        public int diameterOfBinaryTree(TreeNode root) {
            res = 1;
            dfs(root);
            return res - 1;
        }
        public int dfs(TreeNode node) {
            if (node == null) return 0;
            int left = dfs(node.left);
            int right = dfs(node.right);
            res = Math.max(res, left + right + 1);

            return Math.max(left, right) + 1;
        }
    }
}
