package com.ben.followcarl.c7_binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-10  18:13
 * @Description: N叉树的最大深度
 * 基本思路还是和二叉树一样，只是在while循环里面，直接把n叉树的下一层整个子树节点加入到队列中；addAll方法；
 *
 * dfs思路：
 * 1. 递归终止条件：当前节点为空则返回0；
 * 2. 单层逻辑：遍历children，每次求最大的depth。
 * 3. 返回1 + depth；因为要算上当前节点；
 * @Version: 1.0
 */
public class L6_559_maxDepth {
    // dfs，遍历子节点得到最大的深度
    public int maxDepth2(Node root) {
        if (root == null) return 0;
        int depth = 0;
        for (int i = 0; i < root.children.size(); i++) {
            depth = Math.max(depth, maxDepth(root.children.get(i)));
        }
        return depth + 1;
    }
    public int maxDepth(Node root) {
        int res = 0;
        if (root == null) return 0;
        if (root.children.size() == 0) return 1;

        Deque<Node> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            res++;
            while (size-- > 0) {
                Node node = deque.remove();
                if (node.children.size() != 0) {
                    deque.addAll(node.children);
                }
            }
        }
        return res;
    }


    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}

