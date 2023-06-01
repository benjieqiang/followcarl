package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-01  11:08
 * @Description: TODO
 * @Version: 1.0
 */
public class L6_45_jump {
    // 不关心跳的细节，只看覆盖范围能否到达终点。
    public int jump(int[] nums) {
        if (nums.length == 1) return 0; //长度为1，不用跳，原地踏步
        int res = 0;
        int curCover = 0; // 当前元素的覆盖范围，起始位置就是0，因为从index=0开始。覆盖该元素不需要走。
        int nextCover = 0; // 下一步覆盖最远距离下标
        for (int i = 0; i < nums.length; i++) {
            nextCover = Math.max(nums[i] + i, nextCover);// 每次记录跳的最远的下标，每次记录当前元素的最大的覆盖范围；
            if (i == curCover) { // 当i移动到当前的覆盖范围的时候，发现还没到终点，则需要再走一步。
                res++;
                curCover = nextCover; //把该覆盖范围里的能跳最远的下标给curCover
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
