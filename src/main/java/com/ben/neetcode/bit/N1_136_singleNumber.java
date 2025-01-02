package com.ben.neetcode.bit;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-27  11:02
 * @Description: 一组数组中出现一次的数
 * 异或性质：
 *    - `a ^ a = 0`，`a ^ 0 = a`
 *    - 交换性：`a ^ b = b ^ a`
 *    - 自反性：`a ^ b ^ b = a`
 * @Version: 1.0
 */
public class N1_136_singleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }
}
