package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-08  21:39
 * @Description: TODO
 * @Version: 1.0
 */
public class L18_617_mergeTrees {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 递归终止条件
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        // 单层递归逻辑，把两个数之和放到第一个树的节点上；中左右；
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
    @Test
    public void testMergeTrees() {

    }
}
