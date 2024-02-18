package com.ben.followcarl.c7_binarytree;
import java.util.*;
/**
 * @Author: benjieqiang
 * @CreateTime: 2023-11-10  19:59
 * @Description:
 * 层序遍历，每层计算出sum，sum需要定义成double类型；
 * @Version: 1.0
 */
public class L3_637_averageOfLevels {
    class Solution {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> res = new LinkedList<>();
            if (root == null) return res;

            Deque<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                double sum = 0; // 注意double变量类型；
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.remove();
                    sum += node.val;
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
                res.add(sum / size);
            }
            return res;
        }
    }
}
