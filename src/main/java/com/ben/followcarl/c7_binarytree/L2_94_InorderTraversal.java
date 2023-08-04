package com.ben.followcarl.c7_binarytree;


import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Deque;

public class L2_94_InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;//步骤一，遍历左子树
            } else {
                cur = stack.pop();
                list.add(cur.val);////步骤二，取根结点的值
                cur = cur.right;//步骤三，遍历右子树
            }
        }
        return list;
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