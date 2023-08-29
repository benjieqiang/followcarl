package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-06  00:25
 * @Description:
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 思路:
 * 1. 首先都是一个二维数组求区间的问题; 都需要先根据左边界或右边界进行排序;
 * 2. 本题当前左边界和上一个区间右边界相等也是重合的
 *    使用一个数组来记录第一个区间的左右区间,
 *      如果不重合就指向下一个区间，加入结果集;
 *      如果重合就取最大的右边界,
 * 3. 重合情况: intervals[i][0] <= intervals[i - 1][1]
 *              左区间已经排好序了,所以取最小的intervals[i - 1][0];
 *              找到最大的右区间 Math.max(intervals[i - 1][1], intervals[i][1])
 *    不重合情况: intervals[i][0] > intervals[i - 1][1] 把当前区间记录到一个list中;
 *
 * 时间复杂度 ： O(NlogN) 排序需要O(NlogN)
 * 空间复杂度 ： O(logN)  java 的内置排序是快速排序 需要 O(logN)空间
 *
 * @Version: 1.0
 */
public class L15_56_merge {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        // 先把数组按照第一个元素大小升序排列；
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        // 记录起始位置的左右边界，先加入第一个区间的左右边界；
        int[] cur = intervals[0]; // cur[0]是左边界, cur[1]是右边界;
        for (int i = 1; i < intervals.length; i++) {
            // 发现重叠: 当前左区间 <= 上一个区间右边界;
            if (intervals[i][0] <= cur[1]) {
                // 更新重叠区间的右边界是最大值;
                cur[1] = Math.max(intervals[i][1], cur[1]);
            } else {
                // 不重叠, 当前区间直接进入结果集, cur指向下一个区间;
                res.add(cur);
                cur = intervals[i];
            }
        }
        res.add(cur);
        return res.toArray(new int[res.size()][2]);
    }

    @Test
    public void testMerge() {
        int[][] res = {{1,3},{2,6},{8,10},{15,18}};
//        int[][] res = {{1,3}};
        int[][] merge = merge(res);
        for (int[] n : merge) {
            System.out.println(Arrays.toString(n));
        }
    }
}
