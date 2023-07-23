package com.ben.followcarl.c3_hashmap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-22  00:21
 * @Description:
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 *
 * 你返回所有和为 0 且不重复的三元组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * @Version: 1.0
 */
public class L6_15_threeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        if (nums[0] > 0) return res;

        // i用来遍历整个数组
        for (int i = 0; i < nums.length; i++) {
            // i去重逻辑: i和前一位进行元素i-1位置的元素比较，如果相等就跳过，比如{-1,-1, 2} i = 0， left = 1， right =2;
            // 如果比较的是nums[i]和nums[i+1]的元素相等，则会跳过结果集；
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) { // 下标不能重复
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    // 比0大就往左移
                    right--;
                } else if (sum < 0) {
                    // 比0小left往右移
                    left++;
                } else {
                    // 找到了
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 左右的指针去重逻辑 {-1, -1, -1, -1, 2, 2, 2,2},此时i=0，left=1，right=7，当找到一组{-1,-1,2}时
                    // left右边-1和right的左右2都不能再取了，所以去重逻辑是left和left+1，right和right-1
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    while(left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                }
            }
        }
        return res;
    }

    @Test
    public void test3Sum() {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> res = threeSum(nums);
        res.forEach(list -> {
            System.out.println(list);
        });
    }

}
