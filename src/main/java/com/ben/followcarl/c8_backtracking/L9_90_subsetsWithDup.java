package com.ben.followcarl.c8_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-30  20:20
 * @Description: 树枝不去重，树层去重，利用used数组来记录取过的元素。
 * @Version: 1.0
 */
public class L9_90_subsetsWithDup {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    boolean[] used;

    void backtracking(int[] nums, int startIndex) {
        res.add(new ArrayList<>(path));
        for (int i = startIndex; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) { //同一层去重
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backtracking(nums, i + 1);
            path.removeLast();
            used[i] = false;
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); //先排序
        used = new boolean[nums.length]; //定义一个used数组，完成去重。树层去重，树枝不去重。
        backtracking(nums, 0);
        return res;
    }
}