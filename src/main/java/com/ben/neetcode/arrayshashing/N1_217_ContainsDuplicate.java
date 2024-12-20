package com.ben.neetcode.arrayshashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-17  19:45
 * @Description: Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.
 * @Version: 1.0
 */
public class N1_217_ContainsDuplicate {
    //  时间复杂度 : O(nlogn)。即排序的时间复杂度。扫描的时间复杂度 O(n) 可忽略。

    //  空间复杂度 : O(1)。 没有用到额外空间。如果深究 Arrays.sort(nums) 使用了栈空间，那就是 O(logn)。
    public boolean hasDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }

    // O（N）
    public boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) return true;
            set.add(i);
        }
        return false;
    }
}
