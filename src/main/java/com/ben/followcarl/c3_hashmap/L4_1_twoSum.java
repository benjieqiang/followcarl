package com.ben.followcarl.c3_hashmap;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-19  22:56
 * @Description: 两数之和
 *
 *             // 遍历数组，第一个元素先进map，{3:0}
 *             // 第二个元素2进来,tmp = 4，元素2入map{3:0, 2:1}
 *             // 第三个元素4进来，tmp = 2，找到了，i是当前元素的下标，tmp对应的值就是另一个元素下标；
 * @Version: 1.0
 */
public class L4_1_twoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int tmp = target - nums[i];

            if (hashMap.containsKey(tmp)) {
                res[0] = i;
                res[1] = hashMap.get(tmp);
            }
            hashMap.put(nums[i], i);
        }

        return res;
    }

    @Test
    public void testTwoSum() {
        int[] res = {3,2,4};
        int target = 6;
        int[] ints = twoSum(res, target);
        System.out.println(Arrays.toString(ints));
    }
}
