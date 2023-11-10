package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-04  21:50
 * @Description: 二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[15,7],[9,20],[3]]
 *
 * @Version: 1.0
 */
public class L2_107_levelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                list.add(node.val);
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
            }
            res.push(list); //借助addFirst方法，每次都往头加元素，这样就可以完成从底向上遍历。
        }
        return res;
    }


    // dfs:
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;

        dfs(root, res, 0);

        Collections.reverse(res);
        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> res, int depth) {
        if (root == null) return;
        if (res.size() == depth) res.add(new LinkedList<>());

        res.get(depth).add(root.val);
        dfs(root.left, res, depth + 1);
        dfs(root.right, res, depth + 1);
    }
    /*
递归到新节点要把该节点放入 level 对应列表的末尾。

当遍历到一个新的深度 level，而最终结果 res 中还没有创建 level 对应的列表时，应该在 res 中新建一个列表用来保存该 level 的所有节点。

     */

    @Test
    public void testLevelOrderTraversal() {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;


        List<List<Integer>> res = levelOrderBottom2(node1);
        System.out.println(res);
    }
}
