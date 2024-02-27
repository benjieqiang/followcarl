package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-19  18:06
 * @Description:
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为： [ [7], [2,2,3] ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为： [ [2,2,2,2], [2,3,3], [3,5] ]
 *
 * @Version: 1.0
 */
public class L4_39_combinationSum {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    int sum = 0;

    /**
     * @param candidates:
     * @param target:
     * @param startIndex: 用来保证回溯到下一层的时候，从第几个元素开始，77题是每次到下一层，不能重复取，所以是传入i+1，
     *                  这里能重复取，所以下一层继续从i开始取，
     * @return void
     * @description TODO
     * @author benjieqiang
     * @date 2024/2/22 2:07 PM
     */
    private void backtracking(int[] candidates, int target, int startIndex) {
        if (sum > target) return;
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        // for循环体现了树的宽度；树的宽度是所给集合的长度；
        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            backtracking(candidates, target, i); // 从i开始，保证了下一次继续可以拿到第i个元素；
            path.removeLast();
            sum -= candidates[i];
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtracking(candidates, target, 0);
        return res;
    }
    // 剪枝优化，先排序目标数组candidates，当sum > target时，说明此时该元素后面的值不需要再遍历了
    private void backtracking2(int[] candidates, int target, int startIndex) {
        if (sum > target) return;
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        // for循环体现了树的宽度；
        for (int i = startIndex; i < candidates.length && sum <= target; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            backtracking(candidates, target, i); // 从i开始，保证了下一次继续可以拿到第i个元素；
            path.removeLast();
            sum -= candidates[i];
        }
    }

    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            backtracking(candidates, target, 0);

            return res;
        }
        /**
         * @param candidates:
         * @param target:
         * @param index:
         * @return void
         * @description
         * 首先是一个集合里面取数, 所以backtracking()index位置传入的是i或i+1
         * 到底是i还是i+1呢? 题目要求是需要重复取数的, 所以递归的位置传入的是i
         * 最重要是明白index的含义? 这里index用来控制下一次for循环从哪里开始? 每次传入的是i;因为每个元素可以使用多次;
         * 时间复杂度: O(n * 2^n)，
         * 时间复杂度取决于搜索树所有叶子节点的深度之和，即所有可行解的长度之和。
         * 注意这只是复杂度的上界，因为剪枝的存在，
         * 真实的时间复杂度远小于此我们知道 O(n*2^n)是一个比较松的上界，
         * 即在这份代码中，n 个位置每次考虑选或者不选，如果符合条件，就加入结果集的时间代价
         *
         * 空间复杂度: O(target)
         *
         * @author benjieqiang
         * @date 2023/8/30 9:58 PM
         */
        private void backtracking(int[] candidates, int target, int index) {
            if (target < 0) return;
            if (target == 0) {
                res.add(new LinkedList<>(path));
                return;
            }

            for (int i = index; i < candidates.length; i++) {
                path.add(candidates[i]);
                backtracking(candidates, target - candidates[i], i);
                path.removeLast();
            }
        }
    }


    @Test
    public void testCombinationSum() {
        int[] candidates = {2,3,6,7};
        int target = 7;
        Arrays.sort(candidates);
        List<List<Integer>> res = combinationSum(candidates, target);
        System.out.println(res);
    }

    @Test
    public void testCombinationSum2() {
        int[] candidates = {2,3,5};
        int target = 8;
        Arrays.sort(candidates);
        List<List<Integer>> res = new Solution().combinationSum(candidates, target);
        System.out.println(res);
    }
}
