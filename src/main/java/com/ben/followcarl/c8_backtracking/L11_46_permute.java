package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-30  23:42
 * @Description: TODO
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