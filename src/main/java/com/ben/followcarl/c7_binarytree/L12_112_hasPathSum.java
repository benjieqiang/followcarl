package com.ben.followcarl.c7_binarytree;

/**
 * @description: 1.叶子结点的判断：if (root.left == null && root.right == null)
 * 2. debug模式来模拟运算，事半功倍；
 * @author: benjieqiang
 * @date: 2023/5/6 9:31 PM
 * @version: 1.0
 */
public class L12_112_hasPathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        // 叶子结点，targetSum为root.val说明找到了，
        if (root.left == null && root.right == null) return targetSum == root.val;

        // 单层递归逻辑，左节点不为空，则看它的左孩子下面是否有满足条件；
        if (root.left != null) {
            // 为啥要定义临时变量isFind， 因为不想让迭代返回false终止当前遍历流程，而是只有满足最后的叶子结点的值为targetSum才会返回；
            boolean isFind = hasPathSum(root.left, targetSum - root.val);
            if (isFind) return true;
        }
        if (root.right != null) {
           boolean isFind = hasPathSum(root.right, targetSum - root.val);
           if (isFind) return true;
        }
        return false;
    }

    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6= new TreeNode(4);
        TreeNode node7= new TreeNode(7);
        TreeNode node8= new TreeNode(2);
        TreeNode node9= new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node6.right = node9;

        int targetSum = 22;
        boolean res = new L12_112_hasPathSum().hasPathSum(node1, targetSum);
        System.out.println(res);
    }
}
