package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-08  15:03
 * @Description: 构造二叉树使用
 * 前序遍历 : 中左右
 * 每次通过下标来找左子树和右子树;
 *
 * 分割的是同一个数组,105和106是分割两个数组
 * 思路基本一致，先找到最大值下标，然后分割第一个数组；
 * 递归的终止条件：left>=right;
 * @Version: 1.0
 */
public class L17_654_constructMaximumBinaryTree {

    private TreeNode construct(int[] nums, int left, int right) {
        if (left >= right) return null; //数组里面至少有一个元素,
        // 数组区间nums[left, right]，寻找最大值的下标
        int index = left;
        for (int i = left + 1; i < right; i++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        // 切割点: 最大值就是根节点
        TreeNode root = new TreeNode(nums[index]);
        // 切割: 递归左右区间,都是左闭右开
        root.left = construct(nums, left, index);
        root.right = construct(nums, index + 1, right);

        return root;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }


    /**
     * @param nums:
     * @return TreeNode
     * @description 1. 首先整体是从[0, nums.length)开始
     * 2. 单层递归时:左区间从[left, index)
     * 右区间[index+1, right)
     * @author benjieqiang
     * @date 2023/8/14 11:17 AM
     */
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        if (nums == null) return null;
        return construct2(nums, 0, nums.length);
    }
    private TreeNode construct2(int[] nums, int left, int right) {
        if (left >= right) return null;
        // 最大元素的下标
        int index = left;
        for (int i = left + 1; i < right; i++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        // 根节点
        TreeNode root = new TreeNode(nums[index]);

        root.left = construct(nums, 0, index);
        root.right = construct(nums, index + 1, nums.length);

        return root;
    }

    public TreeNode constructMaximumBinaryTree3(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int maxVal = nums[0];
        int index = 0;
        // get maxValue & index;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(maxVal);
        // split nums;
        int[] leftNums = Arrays.copyOfRange(nums, 0, index);
        int[] rightNums = Arrays.copyOfRange(nums, index + 1, nums.length);
        // build root.left, root.right;
        root.left = constructMaximumBinaryTree(leftNums);
        root.right = constructMaximumBinaryTree(rightNums);
        // return root;
        return root;
    }
    @Test
    public void testConstructMaximumBinaryTree() {
        int[] nums = {3, 2, 1, 6, 0, 5};
        TreeNode node = constructMaximumBinaryTree2(nums);
        System.out.println(node);
    }

}
