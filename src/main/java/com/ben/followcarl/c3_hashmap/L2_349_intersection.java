package com.ben.followcarl.c3_hashmap;

import org.junit.Test;

import java.util.*;

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
        HashSet<Integer> resSet = new HashSet<>();
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
    public int[] intersection3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            if (set1.contains(num)) set2.add(num);
        }
        int[] res = new int[set2.size()];
        int i = 0;
        for (int num : set2) {
            res[i++] = num;
        }
        return res;
    }
    // two int[1001]
    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return null;
        List<Integer> resList = new ArrayList<>();
        int[] map1 = new int[1001];
        int[] map2 = new int[1001];
        for (int num : nums1) map1[num]++;
        for (int num : nums2) map2[num]++;
        for (int i = 0; i < 1001; i++) {
            // map[i] 数字出现的count，从1遍历到1000，看每个数字在两个map中出现的次数不为0，则说明有重叠，加入；
            if (map1[i] != 0 && map2[i] != 0) resList.add(i);
        }
        // resList.stream().mapToInt(Integer::intValue).toArray();
        int[] res = new int[resList.size()];
        int i = 0;
        for (int num : resList) {
            res[i++] = num;
        }
        return res;
    }

    // two points;
    public int[] intersection5(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return null;
        Set<Integer> resSet = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                resSet.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] res = new int[resSet.size()];
        int k = 0;
        for (int num : resSet) {
            res[k++] = num;
        }
        return res;
    }
    @Test
    public void test_Intersection() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

//        int[] nums1 = {4,9,5};
//        int[] nums2 = {9,4,9,8,4};

        int[] intersection = intersection(nums1, nums2);


        System.out.println(Arrays.toString(intersection));
    }
}
