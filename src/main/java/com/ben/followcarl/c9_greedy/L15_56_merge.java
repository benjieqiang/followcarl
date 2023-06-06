package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-06  00:25
 * @Description: TODO
 * @Version: 1.0
 */
public class L15_56_merge {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        // 先把数组按照第一个元素大小升序排列；
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        // 记录起始位置的左右边界，先加入第一个区间的左右边界；
        int[] cur = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            // 当前元素的左边界小于等于上一个元素的右边界（小于是重叠，等于属于相邻）
            if (intervals[i][0] <= cur[1]) {
                // 取当前元素的右边界intervals[i][1]和上一个元素的右边界cur[1]最大值；
                cur[1] = Math.max(intervals[i][1], cur[1]);
            } else {
                // 不重叠
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
