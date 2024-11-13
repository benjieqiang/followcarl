package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-10  17:57
 * @Description: 二叉树的最大深度
 * @Version: 1.0
 */
public class L6_104_maxDepth {

    // dfs。 采用后序遍历，因为二叉树的最大深度就是根节点的高度，高度用后序遍历求；
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth2(root.left); // 左
        int right = maxDepth2(root.right); // 右
        return 1 + Math.max(left, right); // 中 // +1是为了算上当前根节点；
    }
    /*
    假设树：root = 3， left = 9， right = 20;
    1. 计算以left为根节点得到的最大深度是1；
    2. 计算以right为根节点得到的最大深度是1；
    3. 加上以3为跟节点最大深度: 1+ max(left, right);
     */

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

        int res = maxDepth2(node1);
        System.out.println(res);
    }

}
