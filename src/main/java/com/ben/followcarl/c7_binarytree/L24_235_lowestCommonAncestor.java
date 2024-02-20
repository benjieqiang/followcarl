package com.ben.followcarl.c7_binarytree;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-14  17:27
 * @Description: BST的最近公共祖先
 *
 * 当我们从上向下去递归遍历，第一次遇到 cur节点是数值在[p, q]区间中，那么cur就是 p和q的最近公共祖先。
 * 为啥第一次遇到就是呢？因为如果不是的话，cur要么向左子树走，那么会失去q孩子，cur向右走，失去p孩子；
 * 思路:
 * 1. 当前节点比p和q节点的值还要大的话,那么p和q一定是在当前节点的左子树里面,否则就在右子树
 * 2. 当前节点在p和q之间,一定是最近的公共祖先;
 * 原因:
 * 无论往当前节点的左孩子还是右孩子遍历,都会失去p或q作为孩子
 * @Version: 1.0
 */
public class L24_235_lowestCommonAncestor {
    // dfs
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    // bfs:
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }
}
