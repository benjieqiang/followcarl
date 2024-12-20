package com.ben.neetcode.arrayshashing;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-19  22:56
 * @Description: 两数之和
 *  什么时候使用哈希法，当我们需要查询一个元素是否出现过，或者一个元素是否在集合里的时候，就要第一时间想到哈希法。
 * map：元素为key，下标为value;
 * 遍历数组，如果发现target - nums[i]该元素在map中出现，说明找到了和为target的下标：i和map.get(target-nums[i]);
 * 无论找没找到，都得更新map，key是nums[i], value是index;
 * 赋值数组；
 * 返回数组；
 *             // 遍历数组，第一个元素先进map，{3:0}
 *             // 第二个元素2进来,tmp = 4，元素2入map{3:0, 2:1}
 *             // 第三个元素4进来，tmp = 2，找到了，i是当前元素的下标，tmp对应的值就是另一个元素下标；
 * @Version: 1.0
 */
public class N3_1_twoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];

            if (map.containsKey(diff)) {
                // map.get(tmp) != null
                res[0] = i;
                res[1] = map.get(diff);
            }
            map.put(nums[i], i);
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
