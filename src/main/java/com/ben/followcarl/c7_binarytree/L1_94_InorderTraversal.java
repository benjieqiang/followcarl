package com.ben.followcarl.c7_binarytree;


import java.util.ArrayList;
import java.util.List;

public class L1_94_InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    /**
     * @param root:
     * @param list:
     * @return void
     * @description 递归法：
     * 1. 参数和返回值；
     * 2. 终止条件；
     * 3. 单层递归逻辑；
     * @author benjieqiang
     * @date 2023/8/4 7:42 PM
     */
    void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}