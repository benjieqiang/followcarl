package com.ben.followcarl.c3_hashmap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-08-04  18:32
 * @Description: Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * <p>
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 * @Version: 1.0
 */
public class L7_18_fourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);
        // 剪枝
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] > target && target > 0) break; //剪枝
            // nums[k]去重
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            for (int i = k + 1; i < nums.length; i++) {
                // 剪枝
                if (nums[k] + nums[i] > target && target > 0) break;
                if (i > k + 1 && nums[i] == nums[i - 1]) continue; // nums[i]去重
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    //[1000000000,1000000000,1000000000,1000000000] -294967296
                    long sum = (long) nums[k] + nums[i] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        // 收获结果
                        res.add(Arrays.asList(nums[k], nums[i], nums[left], nums[right]));
                        // 去重
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    }
                }
            }
        }

        return res;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
//        if (nums[0] > target) return res; // target有可能是负数，所以不能加这句
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int k = i + 1; k < nums.length; k++) {
                if (k > i + 1 && nums[k] == nums[k - 1]) continue;

                int left = k + 1;
                int right = nums.length - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[k] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[k], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    }
                }
            }
        }

        return res;
    }

    @Test
    public void test_FourSum() {
//        int[] nums = {1, 0, -1, 0, -2, 2};
        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3};
        int target = 0;
        List<List<Integer>> lists = fourSum(nums, target);
        List<List<Integer>> lists2 = fourSum2(nums, target);
        System.out.println(lists);
        System.out.println(lists2);
    }
}
