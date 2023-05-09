package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-08  22:10
 * @Description: BST的中序遍历是有序的，所以每次找到中节点看它是否
 * @Version: 1.0
 */
public class L20_98_isValidBST {
    //维护一个最小值val，如果发现中节点的值比他还大，则更新当前这个val；
    //下次再来一个节点，如果比他小，那说明不是升序的，直接返回false;
    double val = -Double.MAX_VALUE;
    public boolean isValidBST(TreeNode root) {
        //BST的中序遍历是有序的，所以左中右
        if (root == null) return true;
        boolean left = isValidBST(root.left);
        // 验证元素是否从小到大；
        System.out.println("当前元素val：" + val + ", 当前节点元素root.val:" + root.val);
        if (val < root.val) val = root.val;
        else return false;
        boolean right = isValidBST(root.right);
        return left && right;
    }
    // root = 5
    // root.left = 1
        // 1.left = null 返回true; 到上一级 ，如果比val大，则更新val为1；
        // 1.right = null, 返回true；right = true；整体返回true
    // 到这一级，val = 1 < root.val = 5,  更新val=5；
    // 此时继续往下走，看根节点5的右边是否是bst；
    // 先找到4的左节点3，发现3比5小，返回false;
    @Test
    public void testIsValidBST() {
        //[5,1,4,null,null,3,6]
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        boolean res = isValidBST(node1);
        System.out.println(res);
    }

    @Test
    public void testIsValidBST2() {
        //[5,1,4,null,null,3,6]
        TreeNode node1 = new TreeNode(5);

    }
}
