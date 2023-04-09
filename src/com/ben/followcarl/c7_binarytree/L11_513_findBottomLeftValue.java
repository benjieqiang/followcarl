package com.ben.followcarl.c7_binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @description: https://leetcode.cn/problems/find-bottom-left-tree-value/
 * @author: benjieqiang
 * @date: 2023/4/9 10:52 PM
 * @version: 1.0
 */
public class L11_513_findBottomLeftValue {
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if(i == 0) res = node.val; //保证每一层第一个结点；
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return res;
    }
}
