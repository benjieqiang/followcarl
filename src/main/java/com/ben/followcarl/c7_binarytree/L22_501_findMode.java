package com.ben.followcarl.c7_binarytree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-14  15:35
 * @Description: 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * @Version: 1.0
 */
public class L22_501_findMode {

    /**
     * @param root:
     * @return int
     * @description 先利用前序遍历得到一个map, 统计频率最高的为众树,
     * 找map中次数一样的key组成数组;
     * @author benjieqiang
     * @date 2023/8/14 4:04 PM
     */
    // 2. 统计map中最多的次数
    int maxValue = 0;

    public int[] findMode(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>(); //key是节点值,val是出现次数;
        List<Integer> res = new ArrayList<>();
        if (root == null) return null;
        // 1. 利用中序遍历得到map,
        traversal(root, map);


        // 3. 只要map中的值一样就是众树,记录其key
        for (Integer key : map.keySet()) {
            if (map.get(key) == maxValue) {
                res.add(key);
            }
        }
        // 4. stream api把list转成int
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    private void traversal(TreeNode root, HashMap<Integer, Integer> map) {
        if (root == null) return;
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        maxValue = Math.max(maxValue, map.get(root.val)); // 更新最大值是key出现最多次对应的value值；
        traversal(root.left, map);
        traversal(root.right, map);
    }

    //利用bst中序遍历是有序数组来解题

    List<Integer> resList = new ArrayList<>();

    /**
     * @param root:
     * @return int
     * @description
     * @author benjieqiang
     * @date 2023/8/14 4:11 PM
     */
    public int[] findMode2(TreeNode root) {
        if (root == null) return null;
        dfs(root);
        // 不知道如何使用API的话
        int[] res = new int[resList.size()];
        //        return resList.stream().mapToInt(Integer::intValue).toArray();

        //把集合list转化为数组
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    /**
     * @param root:
     * @return void
     * @description bst中序遍历，有序数组；
     * @author benjieqiang
     * @date 2023/8/14 4:09 PM
     */
    int count = 0; // 单个元素出现的最大频次;
    int maxCount = 0; //所有元素出现的最大频次;
    TreeNode pre = null; //记录前一个节点

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        // 处理节点
        if (pre == null) { // 处理树中第一个节点。
            count = 1;
        } else if (root.val == pre.val) {
            count++;
        } else {
            // 当前cur和前一个节点pre的值不等, 则count归1
            count = 1;
        }
        pre = root;
        // 当单个元素的频次和已经遍历过的元素的最大频次相等,则
        if (count == maxCount) resList.add(root.val);
        if (count > maxCount) { //找到新的最大的元素频次,更新最大频次,清空res结果集;
            maxCount = count;
            resList.clear();
            resList.add(root.val);
        }
        dfs(root.right);
    }

    @Test
    public void testFindMore() {
        TreeNode node1 = new TreeNode(1);
        node1.right = new TreeNode(2);
        node1.right.left = new TreeNode(2);
        int[] mode2 = findMode2(node1);
        System.out.println(Arrays.toString(mode2));
    }

}
