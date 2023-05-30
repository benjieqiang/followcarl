package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-30  20:26
 * @Description: TODO
 * @Version: 1.0
 */
public class L5_40_combinationSum2 {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean used[];
    int sum = 0;

    private void backtracking(int[] nums, int target, int startIndex) {
        if (sum > target) return;
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            // used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) continue;
            sum += nums[i];
            path.add(nums[i]);
            used[i] = true;
            backtracking(nums, target, i + 1);
            used[i] = false;
            path.removeLast();
            sum -= nums[i];
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 先排序，之后就能去重。
        used = new boolean[candidates.length];
        backtracking(candidates, target, 0);
        return res;
    }

    @Test
    public void testCombinationSum2() {
        int[] candidates = {10,1,2,7,6,1,5};
        System.out.println(combinationSum2(candidates, 8));
    }
}
