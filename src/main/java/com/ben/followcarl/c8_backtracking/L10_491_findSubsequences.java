package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-30  22:48
 * @Description:
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 *
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,6,7,7]
 * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * 示例 2：
 *
 * 输入：nums = [4,4,3,2,1]
 * 输出：[[4,4]]
 *
 * 思路: 首先不能排序, 排序后都是递增子序列了， 题目求的是排序数组
 * 1. 树层取数必须得递增: 首先path不能为空, 其次path的最后一个元素如果大于当前元素,则不能取;
 * 2. 树层得去重, 不能重复取; 每层利用HashSet来去重,如果有元素已经加入到set中,再次add就是false,取反,说明当前元素已存在,跳过;
 * 3. 树枝去重使用i+1来保证下一次递归起始位置；
 *
 *
 * 时间复杂度：这里枚举所有子序列的时间代价是O(2^n)
 * 每次检测序列是否合法和获取哈希值的时间代价都是 O(n)故渐近时间复杂度为 O(2^n * n)
 * 空间复杂度：O(2^n)
 * 最坏情况下整个序列都是递增的，每个长度大于等于 2的子序列都要加入答案，这里哈希表中要加入2^n个元素，
 * 空间代价为O(2^n)用一个临时的数组来存当前答案，空间代价为 O(n)。
 *
 * @Version: 1.0
 */
public class L10_491_findSubsequences {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    private void backtracking(int[] nums, int startIndex) {
        // 递归结束条件可以不写，startIndex > nums.length return;
        // 子序列path何时加入res，当path的长度大于等于2时，往res里加；
        if (path.size() >= 2) res.add(new ArrayList<>(path)); // 不能return是因为要取遍整个树枝满足递增的元素序列
        HashSet<Integer> uset = new HashSet<>(); //uset 记录本层元素是否重复使用，新的一层uset都会重新定义（清空
        for (int i = startIndex; i < nums.length; i++) {
            // 树枝取数必须得递增, 如果当前元素的值小于path的最后一个元素，不能取.
            // path判空是为了防止null异常，第一次进来path是空的。
            // 树层不能重复取数：uset.add，如果有同一层的元素已经取过了，是加入不了的。返回false取反就是真;
            //  path有元素，那么必须是递增的且path最后一个元素和当前值比较，如果比他大，不满足递增或者path满足，直接把这个数加入到hset中用来同层去重。
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1)|| !uset.add(nums[i])) continue;
            // 同层去重逻辑，先判断uset中有没有当前num值，有的话跳过该值，没有下一行加入。
//            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1)|| uset.contains(nums[i])) continue;
//            uset.add(nums[i]);
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
//        int[] nums = {4,7,6,7};
        int[] nums = {1,2,3,4,5,6,7,8,9,10,1,1,1,1,1};
        // hashset for deduplicaiton
        List<List<Integer>> subsequences = findSubsequences(nums);
        System.out.println(subsequences);
    }
}