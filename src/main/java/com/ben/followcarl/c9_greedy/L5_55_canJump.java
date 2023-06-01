package com.ben.followcarl.c9_greedy;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-01  10:34
 * @Description: TODO
 * @Version: 1.0
 */
public class L5_55_canJump {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true; //一个数肯定能跳到；
        // 看跳的范围，每次更新一个数，计算最大的跳跃步数，
        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            // 每次看当前元素最多能跳到哪里？
            cover = cover > nums[i] +i ? cover : nums[i] + i; // nums[i] + i i代表当前位置，nums[i]表示第i的位置的最大步数
            if (cover >= nums.length - 1) return true;
        }
        return false;
    }
}