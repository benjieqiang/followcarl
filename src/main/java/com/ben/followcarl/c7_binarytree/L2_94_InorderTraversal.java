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
        TreeNode cur = root; // 当前节点初始化为根节点

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) { // 如果当前节点不为空，将当前节点以及其左子树的所有左孩子节点压入栈中
                stack.push(cur); // 当前节点入栈
                cur = cur.left; // 移动到左子树
            } else { // 如果当前节点为空，则表示左子树已经遍历完毕，需要处理栈顶元素
                cur = stack.pop(); // 弹出栈顶元素
                list.add(cur.val); // 将栈顶元素的值加入结果集
                cur = cur.right; // 移动到右子树
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
 * 1. 往栈中把左孩子全压入，stack = [5，4，1]，栈顶是1，由于无左孩子，所以走else逻辑，
 * 2. 先拿出栈顶元素1， 把1放入res中，stack = [5, 4], res = [1];
 * 3. 看一下结点1的右边孩子有没有，发现为空，继续进入while循环，走else分支，弹出栈顶元素4，  stack = [5], res = [1, 4]
 * 4. 看一下结点4有没有右孩子，发现有，则把右孩子2放入栈中，stack = [5,2], res = [1,4]
 * 5. 2出栈，入res，stack = [5], res = [1,4,2]
 * 6. 2无右孩子，所以继续出栈，5出栈，stack = []. res = [1,4,2,5]
 * 7. 由于5有右孩子6，所以6入栈 stack = [6], res = [1,4,2,5]
 * 8. 6出栈，stack = [], res = [1,4,2,5,6], 6下面无右孩子，因为栈空了且当前结点是6的右孩子为空，所以跳出循环。返回res
 * */