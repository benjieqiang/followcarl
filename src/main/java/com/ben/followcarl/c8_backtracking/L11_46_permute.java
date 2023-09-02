package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-30  23:42
 * @Description:
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 首先是全排列问题, 不需要index来控制每一层从哪开始, for循环直接就从0开始, 使用used数组来判断是否包含该元素,
 * 如果used[i] = true, 说明从0开始到i这里,nums[i]这个元素已经在上一次for循环中使用了,不能再用,跳过;
 * @Version: 1.0
 */
public class L11_46_permute {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean used[];

    private void backtracking(int[] nums) {
        if (path.size() == nums.length) { //子序列的长度等于原来数组长度，加入结果集并返回。
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true) continue; //排除当前元素已经取过了的情况。
            path.add(nums[i]);
            used[i] = true;
            backtracking(nums);
            used[i] = false;
            path.removeLast();
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtracking(nums);
        return res;
    }

    @Test
    public void testPermute() {
        int nums[] = {1,2,3};
        System.out.println(permute(nums));
    }
}