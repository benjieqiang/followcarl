package com.ben.followcarl.c3_hashmap;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-08-04  15:06
 * @Description: Given two integer arrays nums1 and nums2, return an array of their
 * intersection
 * . Each element in the result must be unique and you may return the result in any order.
 *
 * @Version: 1.0
 */
public class L2_349_intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        Set<Integer> resSet = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            if (map.containsKey(num)) resSet.add(num);
        }
        int[] res = new int[resSet.size()];
        int i = 0;
        for (int num : resSet) {
            res[i++] = num;
        }
        return res;
    }

    @Test
    public void test_Intersection() {
//        int[] nums1 = {1, 2, 2, 1};
//        int[] nums2 = {2, 2};

        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};

        int[] intersection = intersection(nums1, nums2);

        // Print the intersection array
        for (int num : intersection) {
            System.out.println(num);
        }
    }
}
