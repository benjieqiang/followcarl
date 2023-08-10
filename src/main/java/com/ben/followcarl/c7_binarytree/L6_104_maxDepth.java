package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-10  17:57
 * @Description: 二叉树的最大深度
 * @Version: 1.0
 */
public class L6_104_maxDepth {

    // dfs。 采用后序遍历，因为二叉树的最大深度就是根节点的高度，
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left); // 左
        int right = maxDepth(root.right); // 右
        return 1 + Math.max(left, right); // 中
    }

    // bfs
    public int maxDepth(TreeNode root) {
        int res = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++; //每到一层深度加1
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return res;
    }

    @Test
    public void testMaxDepth() {

    }

}
