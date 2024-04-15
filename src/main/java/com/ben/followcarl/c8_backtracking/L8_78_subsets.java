package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-25  21:50
 * @Description: 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * @Version: 1.0
 */
public class L8_78_subsets {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtracking(nums, 0);
        return res;
    }

    /**
     * @param nums: 数组
     * @param startIndex: 决定了本层递归从数组的哪个位置开始，下一层只能从它的下一个位置去取，因为不能重复取startIndex位置的元素
     * @return void
     * @description path = {}是在一开始就加入了;
     *
     * @author benjieqiang
     * @date 2023/5/30 8:04 PM
     */

    private void backtracking(int[] nums, int startIndex) {
        res.add(new ArrayList<>(path)); // 每次取出来的数组成的集合就是所求的一个子集
        if (startIndex >= nums.length) return; // 终止条件可以不写，因为startIndex在下面的for循环中一直增加1，直至nums.length就会结束；
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast(); //弹出去
        }
    }

    @Test
    public void testSubSets() {
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }
}
