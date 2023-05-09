package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-09  22:03
 * @Description: 530. 二叉搜索树的最小绝对差
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
        if(root == null) return 0;
        traversal(root);
        int val = Integer.MAX_VALUE;
        for (int i = 1; i < resList.size(); i++) {
            if ((resList.get(i) - resList.get(i - 1)) < val)
                val = resList.get(i) - resList.get(i - 1);
        }
        return val;
    }
    @Test
    void testGetMinimumDifference() {

    }



    int res = Integer.MAX_VALUE;
    TreeNode pre = null; //
    void traversal2(TreeNode root) {
        if (root == null) return;
        traversal(root.left);
        if (pre != null) {
            res = res > root.val - pre.val ? root.val - pre.val : res;
        }
        pre = root;
        traversal(root.right);
    }
    public int getMinimumDifference2(TreeNode root) {
        if(root == null) return 0;
        traversal(root);
        return res;
    }
    @Test
    void testGetMinimumDifference2() {

    }

}
