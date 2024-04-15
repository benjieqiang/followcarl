package com.ben.followcarl.c8_backtracking;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-30  20:20
 * @Description: 这种去重题目, 得先排序数组, 树枝不去重，树层去重，利用used数组来记录取过的元素。
 * 树层去重的判断: 首先i必须大于0, 并且当前元素与上一个元素值相等,并且上一个元素已经取过了
 *              i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false

 * 时间复杂度：O(n×2^n)，其中 n 是数组 nums的长度。排序的时间复杂度为 O(nlogn)。一共 2^n个状态，
 * 每种状态需要 O(n)的时间来构造子集，一共需要O(n×2^n)时间来构造子集。
 * 由于在渐进意义上 O(nlogn) 小于O(n×2^n)，故总的时间复杂度为 O(n×2n)。
 *
 * 空间复杂度：O(n)。即构造子集使用的临时数组 t 的空间代价。
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

    /**
     * @param nums:
     * @param startIndex:
     * @return void
     * @description 同层去重采用hashSet去重，
     * @author benjieqiang
     * @date 2024/2/27 11:48 AM
     */
    public void backtracking2(int[] nums,int startIndex){
        res.add(new ArrayList<>(path));
        if(startIndex >= nums.length)return;
        HashSet<Integer> hashSet = new HashSet<>();
        for(int i = startIndex; i < nums.length; i++){
            if(hashSet.contains(nums[i])){
                continue;
            }
            hashSet.add(nums[i]);
            path.add(nums[i]);
            backtracking2(nums,i+1);
            path.removeLast();
        }
    }
}