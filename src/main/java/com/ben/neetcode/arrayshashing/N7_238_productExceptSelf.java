package com.ben.neetcode.arrayshashing;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-18  10:38
 * @Description:
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 * @Version: 1.0
 */
public class N7_238_productExceptSelf {
    // 1. nums中含2个0；
    // 2. nums中含1个0，当前数，则乘机为prod，否则是0；
    // 3. nums中不含0，prod/当前数；
    // O(n), O(1)
    public int[] productExceptSelf(int[] nums) {
        int prod = 1, zeroCount = 0;
        for (int num : nums) {
            if (num != 0) prod *= num;
            else zeroCount++;
        }

        // 1. [0,0], 有两个0以上，结果肯定是0，举例：[0,0,1,1] => res= [0,0,0,0]
        if (zeroCount > 1) return new int[nums.length];
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 2. zeroCount = 1/ > 0, nums中有一个0，看是不是当前数，是的话，乘机就是prod，否则，就是其他数，乘机自然为0；
            if (zeroCount > 0) {
                res[i] = (nums[i] == 0) ? prod : 0;
            } else {
                // 3. nums中不含0的情况；
                res[i] = prod / nums[i];
            }
        }
        return res;
    }

    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;                // 获取数组长度
        int[] res = new int[n];             // 用于存储结果的数组

        res[0] = 1;                         // 初始化 res[0] 为 1，表示“左侧没有元素”
        for (int i = 1; i < n; i++) {       // 第一遍循环计算前缀积
            res[i] = res[i - 1] * nums[i - 1];
        }

        int postfix = 1;                    // 初始化后缀积为 1
        for (int i = n - 1; i >= 0; i--) {  // 第二遍循环从右往左计算后缀积
            res[i] *= postfix;              // 当前结果乘以后缀积
            postfix *= nums[i];             // 更新后缀积
        }

        return res;                         // 返回结果数组
    }

    @Test
    public void test_productExceptSelf() {
        int[] nums = {-1,0,1,2,3};
        System.out.println(productExceptSelf(nums));
    }
}
