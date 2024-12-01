package com.ben.hackerrank;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-11-30  11:41
 * @Description: Given a square matrix, calculate the absolute difference between the sums of its diagonals.
 *
 * @Version: 1.0
 */
public class L5_diagonalDifference {
    public static int diagonalDifference(List<List<Integer>> arr) {
        int res = 0;
        for (int i = 0; i < arr.size(); i++) {
            res += arr.get(i).get(i) - arr.get(i).get(arr.size() - 1 - i);
        }
        return Math.abs(res);
    }
}
