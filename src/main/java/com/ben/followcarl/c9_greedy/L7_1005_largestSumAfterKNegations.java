package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-02  21:52
 * @Description: 贪心思路：1. 局部最优: 绝对值大的负数变成正数，当前值最大达到局部最优-》整体和最大；
 *                       2. 第一步完成之后，k大于0，那么需要判断k是否奇偶，偶数不需要反转，
 *                          k为奇数反转一次最小的元素即可完成整体和最大；
 * @Version: 1.0
 */
public class L7_1005_largestSumAfterKNegations {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 1. 先排序，按照绝对值从大到小的顺序；先转成流
        nums = IntStream.of(nums)
                .boxed() //每个元素装箱
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();

        int res = 0;
        // 2. 从前往后反转，把负数变成正数;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] *= -1;
                k--;
            }
        }
        // 3. 如果次数k还大于0，如果k是偶数，反转k次最小值还是正数，所以不需要反转，如果k是奇数，则需要把最小的正整数变成负的
        if (k % 2 == 1) nums[nums.length - 1] *= -1;
        // 4. 求整体和
        for (int a : nums) res += a;
        return res;
    }

    /**
     * @param nums:
     * @param k:
     * @return int
     * @description
     * 时间复杂度: O(nlogn)
     * 空间复杂度: O(1)
     *
     * 排序后遍历把所有负数转为正数，如果k剩余且为奇数，先排序再翻转最小值;
     * 求数组和;
     * @author benjieqiang
     * @date 2023/8/28 3:02 PM
     */
    public int largestSumAfterKNegations2(int[] nums, int k) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] *= -1;
                k--;
            }
        }

        if (k % 2 == 1) {
            Arrays.sort(nums);
            nums[0] *= -1;
        }

        int res = 0;
        for (int num : nums) res += num;
        return res;
    }

    @Test
    public void testRes() {
        int[] nums = {2,-3,-1,5,-4};
        int k = 2;
        System.out.println(largestSumAfterKNegations(nums, k));
    }

    @Test
    public void testSortByAbs() {
        int[] nums = {-3, 1, -2, 5, -4};

        // 错误的，必须是Integer类型才能排序；
//        Arrays.sort(nums, (a, b) -> Integer.compare(Math.abs(a), Math.abs(b)));


        // 使用冒泡排序按绝对值排序
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (Math.abs(nums[j]) > Math.abs(nums[j + 1])) {
                    // 交换元素
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        // 打印排序后的数组
        System.out.println(Arrays.toString(nums));
    }
}
