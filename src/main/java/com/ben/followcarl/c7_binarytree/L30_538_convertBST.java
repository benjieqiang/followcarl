package com.ben.followcarl.c7_binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-14  20:32
 * @Description: 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * 题意:
 * [4,1,6] BST二叉树 =》 累加树是[11,10,6]
 *  4
 * / \
 * 1  6
 * 从最右的节点计算累加树, 节点6变成6, 节点4前面有6, 和为10, 节点1前面有4,6, 和是11;
 * 如果是有序数组[2, 5, 13]，求从后到前的累加数组，也就是[20, 18, 13]
 * 利用pre节点，记录前一个节点值。反中序遍历顺序，右中左，中间累加root节点值和pre节点值，更新pre节点为当前root节点值；
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


    public TreeNode convertBST2(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null ||!stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.right; // right
            } else {
                // mid
                cur = stack.pop();
                cur.val += pre;
                pre = cur.val;
                // left
                cur = cur.left;
            }
        }
        return root;
    }

    class Solution {
        public TreeNode convertBST(TreeNode root) {
            dfs(root, 0);
            return root;
        }

        private int dfs(TreeNode root, int pre) {
            if (root == null) return pre;
            // Traverse the right subtree first, passing the updated pre value
            pre = dfs(root.right, pre);
            // Update root value and pre
            root.val += pre;
            pre = root.val;
            // Traverse the left subtree with the new pre value
            return dfs(root.left, pre);
        }
    }
}
