package com.ben.neetcode.bit;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-27  11:57
 * @Description: TODO
 * @Version: 1.0
 */
public class N5_268_missingnumber {
    // 等差数列求和，先计算0-n的总和，再计算所有num的总和，求得差就是missing
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int cur = 0, sum = n * (n + 1) / 2;
        for (int i : nums) cur += i;
        return sum - cur;
    }
    //找缺失数、找出现一次数都是异或的经典应用。
    //
    //我们可以先求得 [1,n] 的异或和 ans，然后用 ans 对各个 nums[i] 进行异或。
    //
    //这样最终得到的异或和表达式中，只有缺失元素出现次数为 1 次，其余元素均出现两次（x⊕x=0），即最终答案 ans 为缺失元素。
    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i <= n; i++) ans ^= i;
        for (int i : nums) ans ^= i;
        return ans;
    }
}
