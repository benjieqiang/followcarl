package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-04  15:10
 * @Description: 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 * points[i][0]：第i个气球的左边界值
 * points[i - 1][1]：第i-1个气球的右边界值
 *
 * @Version: 1.0
 */
public class L12_452_findMinArrowShots {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        // 二维数组按照左边界进行升序排序，然后看右边界有无重合的部分
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0])); // 利用a - b会溢出
        int res = 1; //至少需要一个弓箭
        // i从1开始，因为是比较i-1=0位置的气球，从0开始会出现负数；
        for (int i = 1; i < points.length; i++) {
            // 当前气球左边界比前一气球右边界值大，说明需要再用一个弓箭射中当前的气球；不加等号原因是：两个气球挨在一起xstart ≤ x ≤ xend也可以一起射爆，
            if (points[i][0] > points[i - 1][1]) {
                res++;
            } else {
                // <= 表示当前气球的左边界和上一个气球有重叠，需要更新当前气球的右边界，
                // 因为这个右边界再下一次for循环中可以用来判断是否和下一个气球重合，如果有重合，只需要一个弓箭就可以射
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return res;
    }

    @Test
    public void testFindMinArrowShots() {
        int[][] points = {{-2147483646,-2147483645},{2147483646,2147483647}};
//        System.out.println(points[0][0] - points[1][0]);
        System.out.println(findMinArrowShots(points));
    }
}
