package com.ben.neetcode.math;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-01  15:05
 * @Description: 73. Set Matrix Zeroes
 * https://leetcode.cn/problems/set-matrix-zeroes/description/
 * @Version: 1.0
 */
public class N3_SetZeros {

    // brute force
    class Solution {
        public void setZeroes(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] marked = new int[m][n];

            for (int i = 0; i < m; i++) {
                System.arraycopy(matrix[i], 0, marked[i], 0, n);
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        for (int k = 0; k < m; k++) marked[k][j] = 0;
                        for (int l = 0; l < n; l++) marked[i][l] = 0;
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                System.arraycopy(marked[i], 0, matrix[i], 0, n);
            }
        }
    }

    // o(m * n) O(m + n)
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] rowZeros = new boolean[m];
        boolean[] colZeros = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowZeros[i] = true;
                    colZeros[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowZeros[i] || colZeros[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // O(m * n) O(1)
    /*
    我们可以用矩阵的第一行和第一列代替方法一中的两个标记数组，以达到 O(1) 的额外空间。
    但这样会导致原数组的第一行和第一列被修改，无法记录它们是否原本包含 0。因此我们需要额外使用两个标记变量分别记录第一行和第一列是否原本包含 0。
    1. 预处理出两个标记变量，如果首行或首列有0，则记录true；
    2. 第二行和第二列开始找有0的元素，遇到则更新首行，首列为0；
    3. 遍历数组，使用首行和首列存储的0，则更新相应该行/该列为0；
    3. 两个标记变量更新首行和首列即可。

     */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean rowZero = false;
        boolean colZero = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                rowZero = true;
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                colZero = true;
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowZero) {
            for (int i = 0; i < m; i++) matrix[i][0] = 0;
        }
        if (colZero) {
            for (int j = 0; j < n; j++) matrix[0][j] = 0;
        }
    }
}
