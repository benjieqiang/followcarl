package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-09  22:03
 * @Description: 530. 二叉搜索树的最小绝对差
 * 中序遍历是有序数组
 * @Version: 1.0
 */
public class L21_530_getMinimumDifference {
    List<Integer> resList = new ArrayList<>();

    void traversal(TreeNode root) {
        if (root == null) return;
        traversal(root.left);
        resList.add(root.val);
        traversal(root.right);
    }

    /**
     * @param root:
     * @return int
     * @description 中序遍历得到有序数组，然后遍历该数组，找到最小间距的子树。
     * @author benjieqiang
     * @date 2023/5/9 10:04 PM
     */
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        traversal(root);
        int val = Integer.MAX_VALUE;
        for (int i = 1; i < resList.size(); i++) {
            if ((resList.get(i) - resList.get(i - 1)) < val)
                val = resList.get(i) - resList.get(i - 1);
        }
        return val;
    }

    @Test
    public void testGetMinimumDifference() {

    }


    class Solution {
        int res = Integer.MAX_VALUE;
        TreeNode pre = null;

        /**
         * @param root:
         * @return void
         * @description 递归的时候, 利用pre来记录当前节点的上一个节点
         * cur 指向当前节点；
         * <p>
         * 左中右的递归；
         * @author benjieqiang
         * @date 2023/8/14 3:32 PM
         */
        public int getMinimumDifference(TreeNode root) {
            if (root == null) return 0;
            traversal(root);
            return res;
        }

        private void traversal(TreeNode cur) {
            if (cur == null) return;
            traversal(cur.left);
            if (pre != null) {
                res = Math.min(res, cur.val - pre.val);
            }
            pre = cur;
            traversal(cur.right);
        }
    }

    // 中序遍历模板；
    int minValue = Integer.MAX_VALUE;
    TreeNode pre = null;

    public int getMinimumDifference4(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) { // 从根节点开始一路向左，斜边入栈，直至为null
                stack.push(cur);
                cur = cur.left;//步骤一，遍历左子树
            } else { // 当遇到叶子节点之后，弹出来第一个元素，加入结果集，然后遍历右孩子
                cur = stack.pop();
                if (pre != null) {
                    minValue = Math.min(cur.val - pre.val, minValue);
                }
                pre = cur;
                cur = cur.right;//步骤三，遍历右子树
            }
        }
        return minValue;
    }

    @Test
    public void testGetMinimumDifference2() {
        //10^ 5和Integer.MAX_VALUE哪个大
        double max = Math.pow(2, 32);
        int maxInt = Integer.MAX_VALUE;
        System.out.println(max);
        System.out.println(maxInt);
        System.out.println(max > maxInt);
    }


    /**
     * @param root:
     * @return int
     * @description 笨方法: 中序遍历,得到有序数组,然后求最小间距;
     * @author benjieqiang
     * @date 2023/8/14 3:29 PM
     */
    public int getMinimumDifference3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traversal(root, list);
        int res = list.get(1) - list.get(0);
        for (int i = 2; i < list.size(); i++) {
            int diff = list.get(i) - list.get(i - 1);
            if (diff < res) res = diff;
        }
        return res;
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        traversal(root.left, list);
        list.add(root.val);
        traversal(root.right, list);
    }

}
