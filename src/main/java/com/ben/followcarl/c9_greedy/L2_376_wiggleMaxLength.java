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
     * @description
     * 贪心算法：局部最优: 删除单调坡度的结点,得到两个峰值 =》 全局最优: 最多的峰值
     * 三种情况：利用当前元素的前一个坡度 和 当前元素的后一个坡度进行比较;
     *  prediff = nums[i] - nums[i - 1]
     *  curdiff = nums[i + 1] - nums[i]
     *
     * 0. 首先有峰值的情况出现在一正一负的场景:  prediff < 0 && curdiff > 0 或 prediff > 0 && curDiff < 0
     *
     * 1. 上下坡有平坡：[1,2,2,2,2,1] 等效于[1,2,1],只记录最右边的2,也就是i=4时记录峰值，其他的三个2删除；
     *      需要prediff == 0 && curDiff > 0 凸的, 出现平坡只记录最右边的坡度,
     *      也有这种可能 preDiff == 0 && curDiff < 0 凹下去的
     *
     *      结合0和1 =》 prediff <= 0 && curdiff >0 || prediff >= 0 && curdiff < 0
     * 2. 首位两端：[2,5]，只有两个元素，峰值为2, 可以看作有三个元素, preDiff = 0表示当前2位置之前还有一个平的元素2
     *    prediff = 0 && curDiff > 0 或 prediff = 0 && curDiff < 0
     *    默认最右边就是一个峰值，即把res = 1,
     * 3. 单调坡度有平坡：[1,2,2,2,3,4]。res应该等于2，中间没有摆动, 一正一负才算摆动, 所以prediff的更新要在坡度发生变化时再进行更新preDiff
     *  1的位置 prediff = 0 && curdiff >0
     *  第三个2的位置: prediff = 0 && curdiff >0 这样就会多统计一个峰值, 所以prediff只有在发生变化时就更新为最新的curdiff. 即 >0;
     * @author benjieqiang
     * @date 2023/5/31 11:50 AM
     */
    public int wiggleMaxLength(int[] nums) {
        int length = nums.length;
        if (length <= 1) return length; // 1个元素就是1个;
        if (length == 2 && nums[0] != nums[1]) return length; // 两个不同元素, 摆动序列长度就是2
        int res = 1; // 默认序列最右边是有一个峰值; 这样两个元素时,结果就是2;
        int preDiff = 0; // 前一对差值
        int curDiff = 0; // 后一对差值
        for (int i = 0; i < length - 1; i++) { // 注意只能遍历到nums.length - 2, 因为后面有i+1
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
