package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-08  10:37
 * @Description: 从中序与后序遍历序列构造二叉树
 * 注意: 前序和后序无法确定一颗树
 * 前序[1,2,3]
 * 后序[3,2,1]
 * 树可能是 左子树123
 * 也可能是: 右子树123
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
     * 3. 拿着左中序数组[9]的大小去切割后序数组，切成左后序[9]; 注意：一律采用左闭右开，Arrays.copyOfRange(数组名, left, right)
     * 4. 拿着右中序数组[15,20,7]的大小去切割后序数组，切成右后序[15,7,20]
     * 5. 递归处理左中序和左后序；
     * 6. 递归处理右中序和右后序；
     * @author benjieqiang
     * @date 2023/5/8 10:38 AM
     */

    public TreeNode traversal(int[] inorder, int[] postorder) {
        // 1. 确定递归的结束条件
        if (postorder.length == 0) return null;
        // 2.postorder的最右边元素是根节点
        int val = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(val);
        // 3.找到切割点
        int index = 0;
        for (;index < inorder.length; index++) {
            if (inorder[index] == val) break;
        }
        // 4.切割inorder：左中[0, index)和右中[index+1, inorder.length),index位置是根节点，所以要从index+1位置开始；
        int[] leftInOrder = Arrays.copyOfRange(inorder, 0, index);
        int[] rightInOrder = Arrays.copyOfRange(inorder, index + 1, inorder.length);
        // 5.切割postorder：左后[0, index)和右后[index, postorder.length-1), postorder.length-1把最后一个根节点排除掉；
        int[] leftPostOrder = Arrays.copyOfRange(postorder, 0, index);
        int[] rightPostOrder = Arrays.copyOfRange(postorder, index, postorder.length - 1);
        // 6.递归处理左右结点；
        root.left = traversal(leftInOrder, leftPostOrder);
        root.right = traversal(rightInOrder, rightPostOrder);

        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        return traversal(inorder, postorder);
    }


    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) return null;
        if (inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) return null;
        return dfs(inorder, postorder);
    }
    private TreeNode dfs(int[] inorder, int[] postorder) {
        if (postorder.length == 0) return null;
        int val = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(val);
        int index = 0;
        for (; index < inorder.length; index++) {
            if (val == inorder[index]) break;
        }
        // 分割
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, index + 1);
        int[] rightInorder = Arrays.copyOfRange(inorder, index + 1, inorder.length);
        System.out.println(Arrays.toString(leftInorder));
        System.out.println(Arrays.toString(rightInorder));
        int[] leftPostorder = Arrays.copyOfRange(postorder, 0, index);
        int[] rightPostorder = Arrays.copyOfRange(postorder, index, postorder.length - 1);
        System.out.println(Arrays.toString(leftPostorder));
        System.out.println(Arrays.toString(rightPostorder));
        // 左右孩子
        root.left = dfs(leftInorder, leftPostorder);
        root.right = dfs(rightInorder, rightPostorder);

        return root;
    }

    @Test
    public void testBuildTree() {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};

        TreeNode node = buildTree2(inorder, postorder);
        System.out.println(node);
    }


}

