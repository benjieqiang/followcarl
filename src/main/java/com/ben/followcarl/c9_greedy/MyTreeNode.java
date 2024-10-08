package com.ben.followcarl.c9_greedy;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append(val);
        sb.append(", ").append(left);
        sb.append(",").append(right);
        sb.append("}");

        return sb.toString();
    }
}

public class MyTreeNode {

}
