package com.ben.followcarl.hot100;

import java.util.HashMap;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-11  17:31
 * @Description:
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 *
 *
 *
 * 示例 1 ：
 *
 * 输入：nums = [2,2,1]
 * 输出：1
 * 示例 2 ：
 *
 * 输入：nums = [4,1,2,1,2]
 * 输出：4
 * 示例 3 ：
 *
 * 输入：nums = [1]
 * 输出：1
 * 交换律：a ^ b ^ c <=> a ^ c ^ b
 *
 * 任何数于0异或为任何数 0 ^ n => n
 *
 * 相同的数异或为0: n ^ n => 0
 *
 * var a = [2,3,2,4,4]
 *
 * 2 ^ 3 ^ 2 ^ 4 ^ 4等价于 2 ^ 2 ^ 4 ^ 4 ^ 3 => 0 ^ 0 ^3 => 3
 * 思路: 遍历整个数组, 进行异或运算,最后所求就是
 * @Version: 1.0
 */
public class L136_singleNumber {
    // hash表 O(n)
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) return key;
        }
        return -1;
    }
    // 异或运算:
    public int singleNumber2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }
}
