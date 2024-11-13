package com.ben.followcarl.c7_binarytree;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-14  17:41
 * @Description: 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 *
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 *
 * 题意: 给一个bst, 插入一个值，这个值与二叉树的各个节点值不同, 得到一个新bst,返回新bst的根节点.
 *
 * 其实是插入到叶子节点;
 * @Version: 1.0
 */
public class L25_701_insertIntoBST {

    // 递归法
    /**
     * @param root:
     * @param val:
     * @return TreeNode
     * @description 有返回值的话，可以利用返回值完成新加入的节点与其父节点的赋值操作。
     * 注意的是：当root为空，则返回new的节点；
     * @author benjieqiang
     * @date 2024/2/20 1:49 PM
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            // 下一层找到了要插入的位置
            return new TreeNode(val);
        }
        // 本层使用root.left或root.right接住，这样就插入了新节点
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    // 迭代法
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) return new TreeNode(val); //当root为空，则返回new的节点；
        TreeNode cur = root;
        TreeNode pre = root; // 记录上一个节点
        // 借助cur遍历找到要插入的节点位置，pre
        while (cur != null) { // 用来不断寻找要插入节点的位置
            pre = cur;
            if (cur.val > val) cur = cur.left;
            else cur = cur.right;
        }
        // 插入所给节点，比较pre节点值和新插入节点值，大则挂左边，小则挂右边
        TreeNode node = new TreeNode(val);
        if (pre.val > val) pre.left = node;
        else pre.right = node;

        return root;
    }


}
