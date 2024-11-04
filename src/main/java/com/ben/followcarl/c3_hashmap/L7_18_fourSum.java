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
 * 时间复杂度: O(n^3)
 * 空间复杂度: O(1)
 *
 * @Version: 1.0
 */
public class L7_18_fourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);
        // 剪枝
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] > target && target > 0) break; //剪枝
            if (k > 0 && nums[k] == nums[k - 1]) continue; // nums[k]去重
            for (int i = k + 1; i < nums.length; i++) {
                if (nums[k] + nums[i] > target && target > 0) break;// 剪枝
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
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] > target && target > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] > target && target > 0) break;
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1;
                int right = length - 1;
                while (left < right) {
                    long sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
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
//        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3}; //0
        int[] nums = {2,2,2,2,2};
        int target = 8;
        List<List<Integer>> lists = fourSum(nums, target);
        List<List<Integer>> lists2 = fourSum2(nums, target);
        System.out.println(lists);
        System.out.println(lists2);
    }
}
