package com.ben.followcarl.c7_binarytree;


import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Deque;

/**
 * @description 遍历顺序和加入数组的数值不一样；
 * @author benjieqiang
 * @date 2023/8/9 9:18 PM
 */
public class L2_94_InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>(); // 用于存放中序遍历结果的列表
        if (root == null) return list; // 如果根节点为空，则返回空列表

        Deque<TreeNode> stack = new LinkedList<>(); // 使用栈来辅助遍历
        TreeNode cur = root; // 当前节点初始化为根节点，不用把root节点先加入到栈中；

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) { // 如果当前节点不为空，将当前节点以及其左子树的所有左孩子节点压入栈中
                stack.push(cur); // 当前节点入栈
                cur = cur.left; // 移动到左子树
            } else { // 如果当前节点为空，弹出栈顶节点，此时这个节点的左子树已经全部处理完
                cur = stack.pop(); // 弹出栈顶元素
                list.add(cur.val); // 加入中节点
                cur = cur.right; // 处理右节点
            }
        }
        return list; // 返回中序遍历结果列表
    }


    @Test
    public void testInorder() {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        List<Integer> res = inorderTraversal(node1);
        System.out.println(res);
    }
}

/*
    5
  4   6
 1 2
 * 1. 往栈中把左孩子全压入，stack = [1,4,5]，top is 1，由于无左孩子，所以走else逻辑，
 * 2. 先拿出栈顶元素1， 把1放入结果集res中，stack = [4,5], res = [1]，移动到当前节点1的右节点；
 * 3. 结点1的右边孩子为空，但是栈不为空，继续进入while循环，走else分支，弹出栈顶元素4，stack = [5], top is 5, res = [1, 4]，cur指向右孩子2；
 * 4. 结点4的右孩子2放入栈中，stack = [2,5], res = [1,4]，cur此时指2的左孩子= null, 但stack不为空，走else逻辑；
 * 5. 2出栈，入res，stack = [5], res = [1,4,2]
 * 6. 2无右孩子，cur = null, 所以继续else逻辑，5出栈，stack = []. cur指向右孩子6, res = [1,4,2,5]
 * 7. cur ！= null, 走if逻辑，stack = [6], cur指向左孩（null），res = [1,4,2,5]
 * 8. 走else逻辑，6出栈，stack = [], res = [1,4,2,5,6], 6下面无右孩子，因为栈空了且当前结点是6的右孩子为空，所以跳出循环。返回res
 * */