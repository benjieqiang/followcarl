package com.ben.followcarl.c10_dp;


import org.junit.Test;

import java.util.HashMap;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-16  20:58
 * @Description: 小偷开始偷二叉树了，相邻节点不能偷。树形dp基础题，后续遍历
 * 1. int[] robTree(TreeNode root)
 * dp数组（dp table）以及下标的含义：
 * 下标为0记录不偷该节点所得到的的最大金钱，
 * 下标为1记录偷该节点所得到的的最大金钱。
 * 2. 在遍历的过程中，如果遇到空节点的话，很明显，无论偷还是不偷都是0，所以就返回
 * if (root == NULL) return {0,0};
 * 3. int[] left = robTree(root.left);
 * int [] right = robTree(root.right);
 * 4. 单层递归
 * int val1 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); //不偷当前节点
 * int val2 = root.val + left[0] + right[0];
 * return {val1, val2};
 *
 * 这道题引出来一个二叉树的dp问题:
 * 1. 定义dp[] = 是一个长度为2的数组, dp[0]表示不偷i得到的最大值; dp[1]表示偷i得到的最大值;
 * 2. 遍历顺序: 后序遍历, 左右中;
 *  递归逻辑:
 *  if 当前节点为空,则返回{0,0}数组,表示空节点偷不偷都是{0,0}的金钱;
 *
 *  递归左孩子, 递归右孩子
 *  中序: 当前节点root偷不偷得到最大值
 *  偷root节点: root.val + left[0] + right[0]
 *  不偷root节点,左右孩子随便偷一个 max(left[1], left[0]) + max(right[0], right[1])
 *
 *  返回一个dp数组 new int[]{val, val2}; 左中右,累加返回
 * @Version: 1.0
 */
public class L22_337_rob {
    /**
     * @param root:
     * @return int
     * @description 树形dp解法
     * @author benjieqiang
     * @date 2023/8/21 1:32 PM
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int[] res = robTree(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robTree(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};

        int[] left = robTree(root.left); // 左
        int[] right = robTree(root.right); // 右

        // 中
        int val1 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); //不偷当前节点, 偷左右孩子, 左右孩子偷不偷取最大;
        int val2 = root.val + left[0] + right[0];// 偷当前节点, 左右孩子不能偷;
        return new int[]{val1, val2};
    }

    /**
     * @param root:
     * @return int
     * @description 递归的去偷;
     * @author benjieqiang
     * @date 2023/8/21 1:33 PM
     */
    public int rob2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;

        // 偷root
        int val1 = root.val;
        if (root.left != null) val1 += rob2(root.left.left) + rob2(root.left.right); //不偷root.left;
        if (root.right != null) val1 += rob2(root.right.left) + rob2(root.right.right); //不偷root.right;
        // 不偷root
        int val2 = rob2(root.left) + rob2(root.right);

        return Math.max(val1, val2);
    }

    /**
     * @param root:
     * @return int
     * @description 优化: 使用一个map来把当前节点的最大值记录下来, 下次如果遇到该节点就不用再递归查找了, 直接取map里面的节点对应的值就是所求;
     * @author benjieqiang
     * @date 2023/8/21 1:39 PM
     */
    HashMap<TreeNode,Integer> map = new HashMap<>();
    public int rob3(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        if (map.containsKey(root)) return map.get(root);
        // 偷root
        int val1 = root.val;
        if (root.left != null) val1 += rob3(root.left.left) + rob3(root.left.right); //不偷root.left;
        if (root.right != null) val1 += rob3(root.right.left) + rob3(root.right.right); //不偷root.right;
        // 不偷root
        int val2 = rob3(root.left) + rob3(root.right);
        int res = Math.max(val1, val2);
        map.put(root, res);
        return res;
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
        System.out.println(rob2(node1));
    }
}
