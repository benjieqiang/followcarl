package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-01  11:08
 * @Description: todo: 8.30
 * 跳到终点: 最少跳几步. 每个元素代表能跳几步;
 *
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 *
 * @Version: 1.0
 */
public class L6_45_jump {
    // 当前可移动距离尽可能多走，如果还没到终点，步数再加一。不关心跳的细节，只看覆盖范围(nums[i] + i)能否到达终点。
    public int jump(int[] nums) {
        if (nums.length == 1) return 0; // 长度为1，不用跳，已经到了最后一个元素了, 原地踏步
        int res = 0;
        int curCover = 0; // 当前元素的覆盖范围，起始位置就是0，因为从index=0开始。覆盖该元素不需要走。
        int nextCover = 0; // 下一步覆盖范围;
        for (int i = 0; i < nums.length; i++) {
            nextCover = Math.max(nums[i] + i, nextCover);// 收集下一步最大的覆盖范围;
            if (i == curCover) { // 当i移动到当前的覆盖范围，发现还没到终点，则需要再走一步。
                res++; // 再走一步
                curCover = nextCover; //当前的覆盖范围更新为此时i的覆盖范围；
                if (curCover >= nums.length - 1) break;
            }
        }
        return res;
    }

    @Test
    public void testJump(){
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
    }
}
