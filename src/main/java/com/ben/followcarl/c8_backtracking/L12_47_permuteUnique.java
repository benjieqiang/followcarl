package com.ben.followcarl.c8_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-31  00:07
 * @Description:
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 有重复数字, 排列问题, 去重
 * 1. 排列问题,肯定不能有for循环从index开始, 即backtracking函数只需要传一个参数nums
 * 2. 排列问题, 如果没有重复元素, 去重逻辑是使用used数组, 每次当前i位置的used[i]是否是true, 为true, 则说明用过,需要跳过;
 * 3. 有重复数字,去重, 首先需要排序数组,
 *    然后 判断树层去重逻辑: i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false
 *
 * @Version: 1.0
 */
public class L12_47_permuteUnique {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean used[];

    private void backtracking(int[] nums) {
        if (path.size() == nums.length) { //子序列的长度等于原来数组长度，加入结果集并返回。
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // used[i - 1] == true，说明同⼀树枝nums[i - 1]使⽤过
            // 前后元素相同且used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过 ，进行树层去重
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            // 排除已经抽的元素，同一个元素不能使用多次
            if (used[i] == true) continue;
            path.add(nums[i]);
            used[i] = true;
            backtracking(nums);
            used[i] = false;
            path.removeLast();
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums); //相同元素挨在一起，便于树层去重
        used = new boolean[nums.length];
        backtracking(nums);
        return res;
    }
}