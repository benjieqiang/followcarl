package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-01  10:34
 * @Description: 看每次跳跃的最大步数能不能覆盖到终点;
 * 如何求解?
 * 求每次最大跳跃步数: 当前元素加上下标位置; nums[i] + i;
 * @Version: 1.0
 */
public class L5_55_canJump {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true; //一个数肯定能跳到；
        // 看跳的范围，每次更新一个数，计算最大的跳跃步数，
        int cover = 0;
        for (int i = 0; i <= cover; i++) { // i只能在cover的范围内活动;
            cover = Math.max(cover, nums[i] + i); // nums[i] + i: i代表当前位置，nums[i]表示第i的位置的最大步数
            if (cover >= nums.length - 1) return true;
        }
        return false;
    }
    /*
    * {2,1,1,1,4}
    * 1. i = 0, cover = 0 + nums[0] = 0 + 2 = 2; 只能从0跳到2
    * 2. i = 1, cover = 1 + 1 = 2; cover = 2;
    * 3. i = 2, cover = 2 + 1 = 3; cover = 3;
    * 4. i = 3, cover = 3 + 1 = 4; cover >= nums.length - 1=4, 所以能跳到终点;
    * */
    @Test
    public void testCanJump() {
        int[] nums = {2,1,1,1,4};
//        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
    }
}