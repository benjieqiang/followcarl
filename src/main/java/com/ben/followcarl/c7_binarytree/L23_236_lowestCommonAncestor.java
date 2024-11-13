package com.ben.followcarl.c7_binarytree;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-14  17:15
 * @Description: 236. 二叉树的最近公共祖先
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * @Version: 1.0
 */
public class L23_236_lowestCommonAncestor {
    /**
     * @param root:
     * @param p:
     * @param q:
     * @return TreeNode
     * @description 后序遍历, 左右中, 向上管理;
     * @author benjieqiang
     * @date 2023/8/14 5:18 PM
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root; //空
        if (root == p || root == q) return root; // 遇到自己就是自己的祖先，p或q向上返回;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            // 左右孩子分别是p和q, root就是最近的公共祖先
            return root;
        } else if (left == null && right != null) {
            return right;
        } else if (left != null && right == null) {
            return left;
        }
        return null;
    }

    // 精简版
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root; //空
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left != null && right != null) return root;// 左右孩子分别是p和q, root就是最近的公共祖先
        else if (left == null && right != null) return right;
        else if (left != null && right == null) return left;
        return null;
    }
}
