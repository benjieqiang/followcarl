package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-19  18:06
 * @Description: TODO
 * @Version: 1.0
 */
public class L4_39_combinationSum {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    int sum = 0;

    private void backtracking(int[] candidates, int target, int startIndex) {
        if (sum > target) return;
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        // for循环体现了树的宽度；
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

    @Test
    public void testCombinationSum() {
        int[] candidates = {2,3,6,7};
        int target = 7;
        Arrays.sort(candidates);
        List<List<Integer>> res = combinationSum(candidates, target);
        System.out.println(res);
    }
}
