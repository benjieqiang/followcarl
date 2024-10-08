package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-06  00:25
 * @Description: 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2:
 * <p>
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 思路:
 * 1. 二维数组求区间的问题: 先根据左边界或右边界进行排序;
 * 2. 使用List<int[]> res接收不重合的区间；
 * 3. 从0开始遍历整个二维数组，比较res最后一个元素的右区间和当前元素的左区间
 * 3.1 重合（<=），则更新右区间为最大右区间；
 * 3.2 不重合，加入res；
 * @Version: 1.0
 */
public class L15_56_merge {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return intervals;
        // 比如 a[0] - b[0] 的结果可能会超出 int 的范围（大于 2^31 - 1 或小于 -2^31），导致溢出，而 Integer.compare() 则能安全处理这些情况。
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
//        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            if (res.size() > 0 && left <= res.get(res.size() - 1)[1]) {
                res.get(res.size() - 1)[1] = Math.max(right, res.get(res.size() - 1)[1]);
            } else {
                res.add(intervals[i]);
            }
        }
        return res.toArray(new int[res.size()][2]);
    }

    // 安全写法
//        Arrays.sort(intervals, (a, b) -> {
//            if (a[0] < b[0]) {
//                return -1;  // a[0] 小于 b[0]，返回负数，表示 a 在 b 前面
//            } else if (a[0] > b[0]) {
//                return 1;   // a[0] 大于 b[0]，返回正数，表示 a 在 b 后面
//            } else {
//                return 0;   // a[0] 等于 b[0]，返回 0，表示它们相等
//            }
//        });
    @Test
    public void testMerge() {
        int[][] res = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        int[][] res = {{1,3}};
        int[][] merge = merge(res);
        for (int[] n : merge) {
            System.out.println(Arrays.toString(n));
        }
    }
}
