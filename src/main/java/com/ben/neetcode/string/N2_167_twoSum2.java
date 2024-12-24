package com.ben.neetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-24  11:25
 * @Description: Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order
 *
 * @Version: 1.0
 */
public class N2_167_twoSum2 {
    // 与two sum不同的是，two sum返回下标，
    public int[] twoSum(int[] numbers, int target) {
        // key: number, value: index + 1
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            int tmp = target - numbers[i];
            if (map.containsKey(tmp)) {
                return new int[] {map.get(tmp), i + 1};
            }
            map.put(numbers[i], i + 1);
        }
        return new int[0];
    }

    // O n, O 1， 递增顺序，two points.
    public int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) { // left不能等于right，单个数字本身不能被重复使用，因此只在两个不同数字组合时判断。
            int cur = numbers[left] + numbers[right];
            if (cur < target) {
                left++;
            } else if (cur > target) {
                right--;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[0];
    }
}
