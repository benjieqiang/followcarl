package com.ben.followcarl.c7_binarytree;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-14  19:54
 * @Description: 669. 修剪二叉搜索树
 * @Version: 1.0
 */
public class L27_669_trimBST {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val < low) {
            // 当前节点的值小于最小边界,那么它的左孩子肯定也不满足边界, 则递归右孩子
            TreeNode right = trimBST(root.right, low, high);
            return right;
        }
        if (root.val > high) {
            // 当前节点的值大于最大边界,那它的右孩子也不满足边界, 左孩子可能满足,需要判断
            TreeNode left = trimBST(root.left, low, high);
            return left;
        }

        //若 root.val 符合要求，则 root 可被保留，递归处理其左右节点并重新赋值即可。
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }
    /*
    起始先从给定的 root 进行出发，找到第一个满足值符合 [low,high]范围的节点，该节点为最后要返回的根节点 ans。

    随后考虑如何修剪 ans 的左右节点：当根节点符合 [low,high] 要求时，修剪左右节点过程中仅需考虑一边的边界值即可。
    即对于 ans.left 只需考虑将值小于 low 的节点去掉（因为二叉搜索树的特性，ans 满足不大于 high 要求，则其左节点必然满足）；
    同理 ans.right 只需要考虑将大于 high 的节点去掉即可。
    */

    public TreeNode trimBST2(TreeNode root, int low, int high) {
        if(root == null) return null;
        // find the fisrt root in [low, high]
        while(root != null && (root.val < low || root.val > high)){
            if(root.val < low) // 当前值比low小，看右子树；
                root = root.right;
            else // 当前值比low大，看左子树；
                root = root.left;
        }

        // 找到一个符合[low, high]的节点；
        TreeNode curr = root;

        // 此时root已经在[L, R] 范围内，处理左孩子元素小于L的情况，遇到比low小的节点，则把它的右孩子挂到当前节点的左孩子处；
        while(curr != null){
            while(curr.left != null && curr.left.val < low){
                curr.left = curr.left.right;
            }
            curr = curr.left;
        }
        //go back to root;
        curr = root;

        // 此时root已经在[L, R] 范围内，处理右孩子大于R的情况，遇到比high大的节点，则把当前右孩子的左孩子挂到当前节点的右孩子
        while(curr != null){
            while(curr.right != null && curr.right.val > high){
                curr.right = curr.right.left;
            }
            curr = curr.right;
        }
        return root;
    }

}
