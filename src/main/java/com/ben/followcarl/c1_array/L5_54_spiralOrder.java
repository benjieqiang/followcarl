package com.ben.followcarl.c1_array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-06  20:49
 * @Description:
 * 1、顺时针打印矩阵，模拟题，需要每次确认好i开始和结束的位置，矩阵变的哪个元素；
 * 2、矩阵的结束条件？m行n列的矩阵，如何结束？
 * @Version: 1.0
 */
public class L5_54_spiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = m - 1;
        List<Integer> res = new ArrayList<>();
        while (true) {
            // i从left到right
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            if (++top > bottom) break;
            // i从top到bottom
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            if (--right < left) break;
            // i从right到left
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }
            if (--bottom < top) break;
            // i从bottom到top
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            if (++left > right) break;
        }
        return res;
    }
}