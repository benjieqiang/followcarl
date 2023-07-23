package com.ben.followcarl.c3_hashmap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-23  22:24
 * @Description: 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]（若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d< n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Version: 1.0
 */
public class L7_18_fourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);
        // 剪枝
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] > target && nums[k] > 0 && target > 0) break; //剪枝
            // nums[k]去重
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            for (int i = k + 1; i < nums.length; i++) {
                // 剪枝
                if (nums[k] + nums[i] > target && nums[k] + nums[i] > 0 && target > 0) break;
                if (i > k + 1 && nums[i] == nums[i - 1]) continue; // nums[i]去重
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    //[1000000000,1000000000,1000000000,1000000000] -294967296
                    long sum = (long)nums[k] + nums[i] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        // 收获结果
                        res.add(Arrays.asList(nums[k], nums[i], nums[left], nums[right]));
                        // 去重
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    }
                }
            }
        }

        return res;
    }

    @Test
    public void test4Sum() {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        List<List<Integer>> lists = fourSum(nums, target);
        lists.forEach(list -> {
            System.out.println(list);
        });

    }

    @Test
    public void test4Sum2() {
        int[] nums = {2,2,2,2,2};
        int target = 8;

        List<List<Integer>> lists = fourSum(nums, target);
        lists.forEach(list -> {
            System.out.println(list);
        });

    }


}
