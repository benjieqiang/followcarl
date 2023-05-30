package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-30  22:48
 * @Description: TODO
 * @Version: 1.0
 */
public class L10_491_findSubsequences {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    private void backtracking(int[] nums, int startIndex) {
        // 递归结束条件可以不写，startIndex > nums.length return;
        // 子序列path何时加入res，当path的长度大于等于2时，往res里加；
        if (path.size() > 1) res.add(new ArrayList<>(path)); // 不能return是因为要取遍整个树枝满足递增的元素序列
        HashSet<Integer> uset = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            // 树枝取数必须得递增,如果当前元素的值小于path的最后一个元素，不能取.path判空是为了防止null异常，第一次进来path是空的。
            // 树层不能重复取数：uset.add，如果有同一层的元素已经取过了，是加入不了的。
            if (!path.isEmpty() && nums[i] < path.getLast() || !uset.add(nums[i])) continue;
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums, 0);
        return res;
    }

    @Test
    public void testFindSubsequences() {
        int[] nums = {4,7,6,7};
        List<List<Integer>> subsequences = findSubsequences(nums);
        System.out.println(subsequences);
    }
}