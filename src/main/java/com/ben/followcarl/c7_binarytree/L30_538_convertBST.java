package com.ben.followcarl.c7_binarytree;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-14  20:32
 * @Description: 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * 题意:
 * [4,1,6] 二叉树 =》 累加树是[11,10,6]
 * 从最右的节点计算累加树, 节点6变成6, 节点4前面有6, 和为10, 节点1前面有4,6, 和是11;
 * 转成:
 * 有序数组[2, 5, 13]，求从后到前的累加数组，也就是[20, 18, 13]
 * @Version: 1.0
 */
public class L30_538_convertBST {
    int pre = 0;
    public TreeNode convertBST(TreeNode root) {
        traversal(root);
        return root;
    }

    // 反中序遍历, 即右中左, 这样每次root加上的都是比他大的值;
    private void traversal(TreeNode root) {
        if (root == null) return;
        traversal(root.right);
        root.val += pre; // 累加上一个节点值；
        pre = root.val; // 更新pre
        traversal(root.left);
    }
}
