package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-31  11:17
 * @Description: 连续数字差值正负交替就是摆动序列
 * 仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 * @Version: 1.0
 */
public class L2_376_wiggleMaxLength {
    /**
     * @param nums:
     * @return int
     * @description 三种情况：
     * 1. 上下坡有平坡：[1,2,2,2,2,1] 等效于[1,2,1],只记录最右边的2，其他的三个2删除；那就需要prediff = 0 && curDiff < 0 或 < 0
     * 2. 数组首位两端：[2,5]，只有两个元素，可以看作有三个元素preDiff = 0表示当前0位置之前还有一个平的元素，curDiff > 0 或 < 0
     *    默认最右边就是一个峰值，即把res = 1，在for循环的时候，在倒数第二个元素位置停止；
     * 3. 单调坡度有平坡：[1,2,2,2,3,4]。 res应该等于2，所以我们需要在坡度发生变化时再进行更新preDiff
     * @author benjieqiang
     * @date 2023/5/31 11:50 AM
     */
    public int wiggleMaxLength(int[] nums) {
        int length = nums.length;
        if (length <= 1) return length;
        if (length == 2 && nums[0] != nums[1]) return length;
        int res = 1; //默认序列最右边是有一个峰值;
        int preDiff = 0; // 前一对差值
        int curDiff = 0; // 后一对差值
        for (int i = 0; i < length - 1; i++) {
            curDiff = nums[i + 1] - nums[i];
            if ((preDiff >= 0 && curDiff < 0) || (preDiff <= 0 && curDiff > 0)) {
                res++;
                preDiff = curDiff; //坡度摆动变化的时候，更新 prediff
            }
        }
        return res;
    }

    @Test
    public void testWiggleMaxLength() {
//        int nums[] = {1,7,4,9,2,5};
//        int[] nums = {1,2,2,2,3,4}; // 单调坡度有平坡：[1,2,2,2,3,4]
        int[] nums = {2, 2}; //两个相等元素
        System.out.println(wiggleMaxLength(nums));
    }
}
