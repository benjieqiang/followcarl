package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-07  21:09
 * @Description: 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 *
 * 迭代法的注意点：
 *
 * @Version: 1.0
 */
public class L12_113_pathSum {
    // res用来存最终的结果，innerList是保存中间结果的；必须是LinkedList，因为可以删除末尾元素；
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> inner = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        traversal(root, targetSum);
        return res;
    }

    private void traversal(TreeNode root, int count) {
        inner.add(root.val);
        if (root.left == null && root.right == null && count == root.val) {
            // 收集结果，add一个集合，就是把这个集合中的元素复制到res中；
            res.add(new LinkedList<>(inner));
        }
        // 空节点不遍历
        if (root.left != null) {
            traversal(root.left, count - root.val);
            inner.removeLast();
        }

        if (root.right != null) {
            traversal(root.right, count - root.val);
            inner.removeLast();
        }
    }

    /**
     * @return null
     * @description 迭代:整体上还112一样，只不过这里需要引入一个存放整体队列的栈，用来存放符合条件的list集合；
     * inner是从栈中弹出的临时list，加入当前节点值，使用add(new LinkedList<inner>)之后加入stackList之后，再移除加入的节点值，
     * 保证了根节点，如果左右孩子都在，inner先存左节点，加入stackList之后，再移除左节点，之后再加入右节点，加入stackList之后，再移除右孩子。
     * @author benjieqiang
     * @date 2024/2/19 4:27 PM
     */
    class Solution {
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> res = new LinkedList<>(); // 结果集
            if (root == null) return res;

            Deque<Object> stack = new LinkedList<>(); // 存放当前节点和节点值
            Deque<List<Integer>> stackList = new LinkedList<>(); // 存放符合条件的list集合；
            // 加入root节点，root节点值，stackList加入第一个集合；
            stack.push(root);
            stack.push(root.val);
            List<Integer> list = new LinkedList<>();
            list.add(root.val);
            stackList.push(list);

            while (!stack.isEmpty()) {
                int val = (int)stack.pop();
                TreeNode node = (TreeNode)stack.pop();
                List<Integer> tmpList = stackList.pop();
                // 叶子节点，且targetSum是val值，收获结果；
                if (node.left == null && node.right == null && targetSum == val) {
                    res.add(tmpList);
                }
                // 先加入右子树
                if (node.right != null) {
                    stack.push(node.right);
                    stack.push(node.right.val + val);
                    tmpList.add(node.right.val);
                    stackList.push(new LinkedList<>(tmpList));
                    tmpList.remove(tmpList.size() - 1);
                }

                // 加入左子树
                if (node.left != null) {
                    stack.push(node.left);
                    stack.push(node.left.val + val);
                    tmpList.add(node.left.val);
                    stackList.push(new LinkedList<>(tmpList));
                    tmpList.remove(tmpList.size() - 1);
                }

            }
            return res;
        }
    }

    public List<List<Integer>> pathSum3(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Deque<Object> stack = new LinkedList<>();
        stack.push(root);
        stack.push(root.val);
        stack.push(new LinkedList<>(Arrays.asList(root.val)));
        while (!stack.isEmpty()) {
            List<Integer> path = (LinkedList)stack.pop();
            int sum = (int)stack.pop();
            TreeNode node = (TreeNode)stack.pop();
            if (targetSum == sum && node.left == null && node.right == null) {
                res.add(path);
            }
            if (node.left != null) {
                stack.push(node.left);
                stack.push(sum + node.left.val);
                path.add(node.left.val);
                stack.push(new LinkedList<>(path));
                path.remove(path.size() - 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                stack.push(sum + node.right.val);
                path.add(node.right.val);
                stack.push(new LinkedList<>(path));
                path.remove(path.size() - 1);
            }
        }

        return res;
    }
    @Test
    public void testPathSum() {

        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node6.right = node9;

        int targetSum = 22;
        List<List<Integer>> res = pathSum3(node1, targetSum);
        System.out.println(res);

    }
}
