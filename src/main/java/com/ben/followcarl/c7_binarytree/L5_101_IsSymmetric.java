package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
//1. stack = [3,3]，0的位置是左节点；
//2. 依次弹出右左结点，比较node1和node2的值一样，所以把他们的孩子入栈；
//3. 此时stack = [null,5,4,4], 栈顶是null，栈底是4；
public class L5_101_IsSymmetric {
    /**
     * @param root:
     * @return boolean
     * @description 确定它的遍历顺序；
     * @author benjieqiang
     * @date 2023/8/9 9:51 PM
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return false;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root.left);
        stack.push(root.right);
        while(!stack.isEmpty()) {
            // 弹出右和左节点；
            TreeNode rightNode = stack.pop();
            TreeNode leftNode = stack.pop();
            // 如果左右孩子都为空，跳过这次循环；比如[2,3,3,4,5,5,null]，当栈为[5,5,null,4]时，此时弹出两个5，因为他们值相同，所以
            // 继续往栈礼放他们的左右孩子（实际是空的），如果在这里返回true是错误的，得跳过。
            if (rightNode == null && leftNode == null) continue;
            // 有一个为空则不对称, 或值不等不对称.
            if ((rightNode != null && leftNode == null) || (rightNode == null && leftNode != null) || (rightNode.val != leftNode.val)) {
                return false;
            }
            // 栈每次入栈是：左结点的右孩子+右节点的左孩子，左结点的左孩子+右节点的右孩子+；
            stack.push(leftNode.left);
            stack.push(rightNode.right);
            stack.push(leftNode.right);
            stack.push(rightNode.left);
        }

        return true;
    }

    /**
     * @param root:
     * @return boolean
     * @description 迭代
     * @author benjieqiang
     * @date 2023/8/10 5:41 PM
     */
    public boolean isSymmetric5(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();

            if (left == null && right == null) continue;//跳过
            if (left == null && right != null) return false;
            if (left != null && right == null) return false;
            if (left.val != right.val) return false;

            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }

        return true;
    }
    /**
     * @param root:
     * @return boolean
     * @description 使用后序遍历，左边孩子处理完，右边孩子处理完，向上返回，
     * @author benjieqiang
     * @date 2023/8/9 9:52 PM
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        return compare(root.left, root.right);
    }
    /**
     * @param left:
     * @param right:
     * @return boolean
     * @description 递归函数；
     * @author benjieqiang
     * @date 2023/8/9 9:53 PM
     */
    private boolean compare(TreeNode left, TreeNode right) {
        // 左右孩子有一个为空，则不对称；
        if (left != null && right == null) return false;
        if (left == null && right != null) return false;
        // 左右都为空，对称。递归函数返回；
        if (left == null && right == null) return true;
        // 左右都不为空，值不等，返回false
        if (left.val != right.val) return false;
        // 都不为空，值相等，对称；
        boolean outside = compare(left.left, right.right);
        boolean inside = compare(left.right, right.left);
        return outside && inside;
    }

    public static void main(String[] args) {
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(2);
//        TreeNode node4 = new TreeNode(3);
//        TreeNode node5 = new TreeNode(3);
//        node1.left = node2;
//        node1.right = node3;
//        node2.right = node4;
//        node3.right = node5;

//        [2,3,3,4,5,null,4]

        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6= new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        boolean res = new L5_101_IsSymmetric().isSymmetric2(node1);
        System.out.println(res);
    }

    public boolean isSymmetric3(TreeNode root) {
        if (root == null) return false;
        return compare2(root.left, root.right);
    }
    private boolean compare2(TreeNode left, TreeNode right) {
        if (left != null && right == null) return false;
        if (left == null && right != null) return false;
        if (left == null && right == null) return true;
        if (left.val != right.val) return false;
        boolean b1 = compare(left.left, right.right);
        boolean b2 = compare(left.right, right.left);
        return b1 && b2;
    }
    @Test
    public void testIsSymmetric() {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;

        System.out.println(isSymmetric3(node1));

    }

}
