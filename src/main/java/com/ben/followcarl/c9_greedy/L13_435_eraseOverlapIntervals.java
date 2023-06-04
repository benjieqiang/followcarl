package com.ben.followcarl.c9_greedy;

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
            // 统计重复区间：当前元素的左边界和上一个元素的右边界进行比较，如果小于，则是不重合的；等于和大于表示不重合。
            if (intervals[i][0] < intervals[i - 1][1]) {
                res++;
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
            }
        }
        return res;
    }
}
