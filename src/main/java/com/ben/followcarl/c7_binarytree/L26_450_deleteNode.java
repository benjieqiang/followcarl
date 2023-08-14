package com.ben.followcarl.c7_binarytree;

import apple.laf.JRSUIUtils;
import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-14  18:52
 * @Description: 450. 删除二叉搜索树中的节点
 * 五种情况进行讨论: 递归函数的值表示删完节点后的指向节点;
 * 1. 未找到,返回null
 * 2. 找到待删节点,有四种情况
 *      1. 左右孩子为空,直接删,返回null
 *      2. 左右孩子有一个为空, 返回不为空的孩子;(2种情况)
 *      3. 左右孩子都不为空, 先找右孩子的最左节点, 再把待删节点的左孩子挂到右孩子最左节点的左边;
 * 3. 递归去看左右孩子是否需要删除, key和左右孩子的值比较,
 * @Version: 1.0
 */
public class L26_450_deleteNode {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root; // 未找到要删除的节点，此时root为空
        // 找到，即root是待删节点
        if (root.val == key) {
            //1.左右孩子为空, 叶子节点,直接返回null
            if (root.left == null && root.right == null) {
                return null;
            }
            //2.左右孩子有一个为空, 谁为空, 不为空的补位,
            if (root.left == null && root.right != null) {
                return root.right;
            } else if (root.left != null && root.right == null) {
                return root.left;
            }
            // 3.左右孩子都不为空，此时root节点就是要待删除的节点
            // 1. 此时去它的右子树的最左节点
            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            //2.把root待删节点的左子节点挂到cur的左子节点上
            cur.left = root.left;
            return root.right; // 记录此时root的右孩子节点，需要返回；
        }
        if (root.val > key) root.left = deleteNode(root.left, key);
        if (root.val < key) root.right = deleteNode(root.right, key);
        return root;
    }

    @Test
    public void testDelNode() {
//        [5,3,6,2,4,null,7]
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        System.out.println(deleteNode(node1, 0));

    }

    @Test
    public void testDelNode2() {
//        [5,3,6,2,4,null,7]
        TreeNode node1 = new TreeNode(0);


        System.out.println(deleteNode(node1, 0));

    }
}
