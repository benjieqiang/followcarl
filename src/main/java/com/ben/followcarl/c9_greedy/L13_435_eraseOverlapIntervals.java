package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-04  16:39
 * @Description:
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * @Version: 1.0
 */
public class L13_435_eraseOverlapIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // 利用a - b会溢出
        int res = 0; // 记录重复区间，默认无重叠
        for (int i = 1; i < intervals.length; i++) {
            // 不重叠区间：当前左边界 大于或等于 上一个元素右边界
            // 重叠区间：如果小于，则是重合的；
            if (intervals[i][0] < intervals[i - 1][1]) {
                res++;
                // 为啥还要再去取一次有边界的最小值?
                // 为了下次遍历时, 判断当前区间的左边界和上一个区间的有边界是否重合, 因为我们取了最小值,所以, 就能判断出来此时i区间和i-1区间以及i-2区间是否有重合和所以取了最小右边界；
                // 因为最小右边界以前的区间i-1区间和i-2的区间是肯定重叠的。现在要比较的是最小右边届右边的区间和第i个区间的左边界是否重合;
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
            }
        }
        return res;
    }

    /**
     * @param intervals:
     * @return int
     * @description
     * @author benjieqiang
     * @date 2023/8/29 9:47 PM
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        // 左边界排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int res = 0; // res记录重叠区间数；
        for (int i = 1; i < intervals.length; i++) {
            // 重叠则更新最小右边界;
            if (intervals[i][0] < intervals[i - 1][1]) {
                res++;
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
