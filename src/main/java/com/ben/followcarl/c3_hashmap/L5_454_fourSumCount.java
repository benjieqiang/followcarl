package com.ben.followcarl.c3_hashmap;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-19  23:09
 * @Description: 454. 四数相加 II
 * <p>
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * <p>
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * <p>
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * 输出：2
 * 解释：
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/4sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Version: 1.0
 */
public class L5_454_fourSumCount {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //key：nums1中的任一元素和nums2中任一元素之和，value：两数之和的次数，该和肯定由不止一种格式构成；
        //比如：nums1 = [-1,-1] nums2=[-1,1]
        // map中最终{0=2,-2=2};和为0由两种构成形式
        HashMap<Integer, Integer> hmap = new HashMap<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                hmap.put(num1 + num2, hmap.getOrDefault(num1 + num2, 0) + 1);
            }
        }
        int count = 0;
        for (int num3 : nums3) {
            for (int num4 : nums4) {
                count += hmap.getOrDefault(0 - num3 - num4, 0);
            }
        }
        return count;
    }

    @Test
    public void test4SumCount() {
        int[] nums1 = {1,2}, nums2 = {-2,-1}, nums3 = {-1,2}, nums4 = {0,2};
        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
    }
}
