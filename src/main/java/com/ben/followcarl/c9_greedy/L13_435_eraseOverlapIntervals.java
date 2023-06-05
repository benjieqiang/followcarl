package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-04  16:39
 * @Description: TODO
 * @Version: 1.0
 */
public class L13_435_eraseOverlapIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // 利用a - b会溢出
        int res = 0; // 记录重复区间，默认无重叠
        for (int i = 1; i < intervals.length; i++) {
            // 不重叠情况：当前左边界 大于或等于 上一个元素右边界
            // 统计重叠区间：如果小于，则是重合的；
            if (intervals[i][0] < intervals[i - 1][1]) {
                res++;
                // 为了记录i+1位置的区间和它上面的区间i和i-1的区间是否重叠。所以取了最小右边界；
                // 为啥取最小右边界，因为右边界以前的区间i和i-1的部分是肯定重叠的。所以要比较三个区间的重叠必须取最小的；
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
            }
        }
        return res;
    }

    @Test
    public void testErase() {
        int[][] intervals = {
                {1,2},
                {3,6},
                {7,12},
                {4,8},
                {10,16}
        };
        System.out.println(eraseOverlapIntervals(intervals));
    }
}
