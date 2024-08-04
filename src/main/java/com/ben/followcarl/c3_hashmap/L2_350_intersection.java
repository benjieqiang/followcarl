package com.ben.followcarl.c3_hashmap;

import java.util.*;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-08-04  15:17
 * @Description: O(min(n, m))
 * @Version: 1.0
 */
public class L2_350_intersection {
    /**
     * @param nums1:
     * @param nums2:
     * @return int
     * @description O(n + m): Where n is the length of nums1 and m is the length of nums2.
     * @author benjieqiang
     * @date 2024/8/4 3:26 PM
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        // Step 1: Count elements in nums1
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums1) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Find intersection with nums2
        List<Integer> intersectionList = new ArrayList<>();
        for (int num : nums2) {
            if (countMap.containsKey(num) && countMap.get(num) > 0) {
                intersectionList.add(num);
                countMap.put(num, countMap.get(num) - 1); // Decrement count
            }
        }

        // Step 3: Convert list to array
        int[] result = new int[intersectionList.size()];
        for (int i = 0; i < intersectionList.size(); i++) {
            result[i] = intersectionList.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};

        int[] intersection = intersect(nums1, nums2);

        // Print the intersection array
        for (int num : intersection) {
            System.out.print(num + " ");
        }
    }

    /**
     * @param nums1:
     * @param nums2:
     * @return int
     * @description
     * Sorting takes O(n log n) for nums1 and O(m log m) for nums2.
     * The two-pointer iteration takes O(n + m).
     * @author benjieqiang
     * @date 2024/8/4 3:26 PM
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        // Step 1: Sort both arrays
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // Step 2: Use two pointers to find the intersection
        int i = 0, j = 0;
        int k = 0; // Pointer for result array

        // Use an array to store the result, with a size of the smaller array
        int[] temp = new int[Math.min(nums1.length, nums2.length)];

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                // Add the common element to the result
                temp[k++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        // Step 3: Copy the result to an array of the correct size
        return Arrays.copyOfRange(temp, 0, k);
    }
}
