package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-10  18:41
 * @Description: 二叉树的最小深度
 * @Version: 1.0
 */
public class L7_111_minDepth {
    /**
     * @param root:
     * @return int
     * @description dfs思路：最小深度是指，根节点到最近的叶子节点。如果左子树为空或右子树为空，此时，最近的叶子节点在他们相反的子树处出现；
     * @author benjieqiang
     * @date 2024/2/18 1:10 PM
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right != null) {
            // 只有右子树。 如果当前节点的左子树为空，说明最近的叶子节点只能在右子树中
            return 1 + minDepth(root.right);
        }
        if (root.left != null && root.right == null) {
            // 只有左子树
            return 1 + minDepth(root.left);
        }
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    /**
     * 迭代法，层序遍历，只要左右孩子为空，则就找到首个叶子节点，直接返回当前的depth；
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int depth = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.remove();
                // 找到首个叶子结点，直接返回depth，因为从上往下遍历，所以该值就是最小值
                if (node.left == null && node.right == null) return depth;
                if (node.left != null) deque.add(node.left);
                if (node.right != null) deque.add(node.right);
            }
        }
        return depth;
    }

    class Solution {
        // dfs解法： 最小深度是从根节点到最近**叶子节点**的最短路径上的节点数量
        public int minDepth(TreeNode root) {
            if (root == null) return 0;
            int leftDepth = minDepth(root.left); // 左子树深度
            int rightDepth = minDepth(root.right); // 右子树深度
            if (root.left == null) {
                return rightDepth + 1;
            }
            if (root.right == null) {
                return leftDepth + 1;
            }
            // 如果左右子树都不为空，递归计算左右子树的最小深度，取最小值
            return Math.min(leftDepth, rightDepth) + 1;
        }
    }

    @Test
    public void testMinDepth() {
        //  root = [3,9,20,null,null,15,7]
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        int res = minDepth(node1);
        System.out.println(res);
    }
}
