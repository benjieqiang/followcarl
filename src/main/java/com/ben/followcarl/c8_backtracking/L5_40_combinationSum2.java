package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-30  20:26
 * @Description: 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明： 所有数字（包括目标数）都是正整数。解集不能包含重复的组合。
 * <p>
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
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
        Arrays.sort(candidates); // 树层去重, 先排序
        used = new boolean[candidates.length];
        backtracking(candidates, target, 0);
        return res;
    }

    /**
     * @author benjieqiang
     * @description 首先写出一份不去重的代码, 发现树枝去重已经使用index去控制了，即index + 1；
     * 树层去重如何去控制呢?
     * 1. 树层去重先排序;
     * 2. 使用used数组控制同一层不再访问;
     * 前面我们提到：要去重的是“同一树层上的使用过”，如何判断同一树层上元素（相同的元素）是否使用过了呢。
     * <p>
     * 如果candidates[i] == candidates[i - 1] 并且 used[i - 1] == false，就说明：前一个树枝，使用了candidates[i - 1]，
     * 也就是说同一树层使用过candidates[i - 1]。
     * 比如1，1，2
     * 1. 先取1，之后candidates[0] = 1, used[0] = 1，紧接着回溯到第二层，此时i从0+1的位置开始（树枝去重用index控制）。。。
     * 之后会把used[0]变成false
     * 2. 第二个取1的时候，因为上一个used[0]=false 且 当前元素和上一个元素相同，则说明是重复元素，跳过；
     * @date 2023/8/31 11:47 AM
     */
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        private void backtracking(int[] candidates, int target, int index) {
            if (target < 0) return;
            if (target == 0) {
                res.add(new LinkedList<>(path));
                return;
            }
            for (int i = index; i < candidates.length; i++) {
                path.add(candidates[i]);
                backtracking(candidates, target - candidates[i], i + 1);
                path.removeLast();
            }
        }

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            backtracking(candidates, target, 0);
            return res;
        }
    }

    class Solution2 {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used;

        private void backtracking(int[] candidates, int target, int index) {
            if (target < 0) return;
            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = index; i < candidates.length; i++) {
                if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == false) continue;
                path.add(candidates[i]);
                used[i] = true;
                backtracking(candidates, target - candidates[i], i + 1);
                path.removeLast();
                used[i] = false;
            }
        }

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates); // 树层去重, 先排序
            used = new boolean[candidates.length];
            backtracking(candidates, target, 0);

            return res;
        }
    }

    /**
     * @param candidates:
     * @param target:
     * @param sum:
     * @param startIndex:
     * @return void
     * @description 利用HashSet来同层去重；
     * @author benjieqiang
     * @date 2024/2/27 11:50 AM
     */
    public void backtracking(int[] candidates, int target, int sum, int startIndex) {
        if (sum > target) return;
        if (sum == target) {
            res.add(new ArrayList<>(path));
        }
        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = startIndex; i < candidates.length; i++) {
            if (hashSet.contains(candidates[i])) continue;
            hashSet.add(candidates[i]);
            path.add(candidates[i]);
            backtracking(candidates, target, sum + candidates[i], i + 1);
            path.removeLast();
        }
    }

    @Test
    public void testCombinationSum2() {
        int[] candidates = {4, 3, 2, 3, 5, 2, 1};
        System.out.println(combinationSum2(candidates, 5));
    }
}
