package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-08  15:03
 * @Description: TODO
 * @Version: 1.0
 */
public class L17_654_constructMaximumBinaryTree {

    private TreeNode construct(int[] nums, int left, int right) {
        if (left >= right) return null; //区间无元素了
        // 每次都在对应的数组区间nums[left, right]，寻找最大值的下标
        int index = left;
        for (int i = left + 1; i < right; i++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        // 根节点
        TreeNode root = new TreeNode(nums[index]);
        // 递归左右区间,都是左闭右开
        root.left = construct(nums, left, index);
        root.right = construct(nums, index + 1, right);

        return root;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }

    @Test
    public void testConstructMaximumBinaryTree() {
        int[] nums = {3,2,1,6,0,5};
        TreeNode node = constructMaximumBinaryTree(nums);
        System.out.println(node);
    }

}
