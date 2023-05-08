package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-08  10:37
 * @Description: 从中序与后序遍历序列构造二叉树
 * @Version: 1.0
 */
public class L16_106_BuildTree {
    /**
     * @param inorder: 中序数组
     * @param postorder: 后序数组
     * @return TreeNode
     * @description
     * 中序数组[9,3,15,20,7] 和 后序数组[9,15,7,20,3]
     * 1. 后序数组的最后一个元素就是二叉树的根节点3；
     * 2. 在中序数组中找到3这个元素作为切割点，把中序数组切成左中序[9]和右中序[15,20,7]
     * 3. 拿着左中序数组[9]的大小去切割后序数组，切成左后序[9];
     * 4. 拿着右中序数组[15,20,7]的大小去切割后序数组，切成右后序[15,7,20]
     * 5. 递归处理左中序和左后序；
     * 6. 递归处理右中序和右后序；
     * @author benjieqiang
     * @date 2023/5/8 10:38 AM
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return new TreeNode(1);
    }

    @Test
    public void testBuildTree() {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};

        TreeNode node = buildTree(inorder, postorder);
        System.out.println(node.val);

    }


}

