package com.ben.neetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-08-04  18:31
 * @Description:
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 * 1. sort; if nums[0] > 0 return null;
 * 2. iterate nums from i to length
 * 3. pruning i : i > 0 && nums[i] == nums[i - 1]
 * 4. Find Triplets:
 *      left begins i + 1 , right begins length - 1;
 * 5.   as long as left < right, get sum of i, left, right value;
 * 6.   if sum < 0, right - 1, sum > 0, left + 1;
 * 7. Add Triplet to Results: if sum == 0, get the result. add the triplet [nums[i], nums[left], nums[right]] to the results list.
 * 8. Duplicate Handling: left < right. as left value == left - 1 left++
 *                        as right value == right - 1 , right--；
 * 9. left++, right--;
 *  * @Version: 1.0
 */
public class N3_15_threeSum {

    //  O(n^2) O（1）or O(n) depending on the sorting algorithm.
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        // 首元素大于0，不可能有和为0的情况；
        if (nums[0] > 0) return res;

        // i用来遍历整个数组
        for (int i = 0; i < nums.length; i++) {
            // i去重逻辑: i和前一位进行元素i-1位置的元素比较，如果相等就跳过，比如{-1,-1, 2} i = 0， left = 1， right =2;
            // 如果比较的是nums[i]和nums[i+1]的元素相等，则会跳过结果集；
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) { // 下标不能重复
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    // 比0大right左移
                    right--;
                } else if (sum < 0) {
                    // 比0小left右移
                    left++;
                } else {
                    // 找到了
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 左右的指针去重逻辑
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                }
            }
        }
        return res;
    }
}


