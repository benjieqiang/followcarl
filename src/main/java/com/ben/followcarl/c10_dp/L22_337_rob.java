package com.ben.followcarl.c10_dp;


import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-16  20:58
 * @Description: 小偷开始偷二叉树了，相邻节点不能偷。树形dp基础题，后续遍历
 * 1. int[] robTree(TreeNode root)
 * dp数组（dp table）以及下标的含义：下标为0记录不偷该节点所得到的的最大金钱，下标为1记录偷该节点所得到的的最大金钱。
 * 2. 在遍历的过程中，如果遇到空节点的话，很明显，无论偷还是不偷都是0，所以就返回
 * if (root == NULL) return {0,0};
 * 3. int[] left = robTree(root.left);
 * int [] right = robTree(root.right);
 * 4. 单层递归
 * int val1 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); //不偷当前节点
 * int val2 = root.val + left[0] + right[0];
 * return {val1, val2};
 * @Version: 1.0
 */
public class L22_337_rob {
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int[] res = robTree(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robTree(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};

        int[] left = robTree(root.left);
        int[] right = robTree(root.right);

        int val1 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); //不偷
        int val2 = root.val + left[0] + right[0];// 偷当前节点
        return new int[]{val1, val2};
    }

    @Test
    public void testRob() {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;

        System.out.println(rob(node1));
    }
}
