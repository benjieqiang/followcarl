package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-14  20:14
 * @Description: 有序数组 转成 高度平衡BST
 *
 * 思路: 选中间节点作为它的根节点, 这样左右子树的节点数目一样;
 *
 * 利用左闭右开;
 * @Version: 1.0
 */
public class L28_108_sortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;
        return construct(nums, 0, nums.length);
    }
    private TreeNode construct(int[] nums, int left, int right) {
        if (left >= right) return null;
        int mid = left + (right - left) / 2;
        // int mid = left + ((right - left) >> 1); 一定要加括号，不然就挂了；
        TreeNode node = new TreeNode(nums[mid]);
        // left, mid
        node.left = construct(nums, left, mid);
        // mid + 1, right
        node.right = construct(nums, mid + 1, right);
        return node;
    }

    @Test
    public void testSortedArrayToBST() {
        int[] nums = {-10,-3,0,5,9};
        System.out.println(sortedArrayToBST(nums));
    }
}
