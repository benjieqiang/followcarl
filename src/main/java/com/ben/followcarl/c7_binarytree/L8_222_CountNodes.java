package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @return null
 * @description 完全二叉树的节点数目统计：
 *
 * @author benjieqiang
 * @date 2024/2/19 1:43 PM
 */
public class L8_222_CountNodes {
    // 利用普通二叉树，后序遍历，分别求出一个结点左子树的孩子数，再求出右孩子的数目；求和加上当前结点数（1）；就是该层结点的数目；
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);
        return 1 + leftCount + rightCount;
    }

    //对于完全二叉树，其有如下特性：
    //子树是完全二叉树
    //左子树和右子树至少有一棵是满二叉树
    //因此，对于满二叉树，我们知道它的结点数 = 2^(深度)-1，因此只需获取其高度，无需遍历其所有结点
    public int countNodes2(TreeNode root) {
        if(root == null) return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 0, rightDepth = 0;
//        开始根据左深度和右深度是否相同来判断该子树是不是满二叉树
        while (left != null) {
            left = left.left;
            leftDepth++;
        }
        while (right != null) {
            right = right.right;
            rightDepth++;
        }
        if (leftDepth == rightDepth) {
            return (2 << leftDepth) - 1; // 注意一定要带括号：(2<<1) 相当于2^2，返回满足满二叉树的子树节点数量
        }
        // 如果两侧高度不一致，则使用后序遍历的方法，
        return countNodes2(root.left) + countNodes2(root.right) + 1;
    }

    // bfs来遍历求节点个数，bfs的模板，不需要返回结果集，直接res++；
    public int countNodes3(TreeNode root) {
        if(root == null) return 0;
        //层序遍历
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                res++;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return res;
    }
    public static void main(String[] args) {

    }

    @Test
    public void testCountNodes() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;
        int res = countNodes3(node1);
        System.out.println(res);
    }
}
