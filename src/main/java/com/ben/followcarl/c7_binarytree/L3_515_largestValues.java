package com.ben.followcarl.c7_binarytree;
import java.util.*;
/**
 * @Author: benjieqiang
 * @CreateTime: 2023-11-10  20:12
 * @Description: 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *
 * @Version: 1.0
 */
public class L3_515_largestValues {

    class Solution {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            if (root == null) return res;

            Deque<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                int maxValue = Integer.MIN_VALUE; // 注意点，因为二叉树里面节点值有负数，所以给了一个Integer最小值；
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.remove();
                    maxValue = Math.max(maxValue, node.val);
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
                res.add(maxValue);
            }
            return res;
        }
    }
}
