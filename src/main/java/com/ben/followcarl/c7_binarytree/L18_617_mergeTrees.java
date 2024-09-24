package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-08  21:39
 * @Description: TODO
 * @Version: 1.0
 */
public class L18_617_mergeTrees {
    // dfs
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

    // bfs
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        Deque<TreeNode> q1 = new LinkedList<>();
        Deque<TreeNode> q2 = new LinkedList<>();
        q1.add(root1);
        q2.add(root2);
        while (!q1.isEmpty()) {
            TreeNode node1 = q1.remove();
            TreeNode node2 = q2.remove();
            // 两个节点此时都不为空, 直接更新树1的当前节点值;
            node1.val += node2.val;

            // 树1和树2左节点都不为空
            if (node1.left != null && node2.left != null) {
                q1.add(node1.left);
                q2.add(node2.left);
            }

            // 树1和树2右节点都不为空
            if (node1.right != null && node2.right != null) {
                q1.add(node1.right);
                q2.add(node2.right);
            }

            // 修改node1的左节点值和右节点值；
            // 树1左节点为空, 树2左节点不为空, 直接拷过去
            if (node1.left == null && node2.left != null) {
                node1.left = node2.left;
            }

            // 树1右节点为空, 树2右节点不为空, 直接拷过去
            if (node1.right == null && node2.right != null) {
                node1.right = node2.right;
            }
        }
        // 返回树1的根节点；
        return root1;
    }

    /**
     * @param root1:
     * @param root2:
     * @return TreeNode
     * @description 不修改原来的树的递归做法；前序遍历；
     * @author benjieqiang
     * @date 2024/2/20 9:56 AM
     */
    public TreeNode mergeTrees3(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        // 都不为空时，节点值叠加，分别处理左右子树；
        TreeNode node = new TreeNode(root1.val + root2.val);
        node.left = mergeTrees3(root1.left, root2.left);
        node.right = mergeTrees3(root1.right, root2.right);
        return node;
    }


    @Test
    public void testMergeTrees() {

    }
}
