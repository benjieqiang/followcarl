package com.ben.neetcode.math;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-11-30  16:22
 * @Description:
 * [ 1  2  3 ]
 * [ 4  5  6 ]
 * [ 7  8  9 ]
 * After rotating 90 degrees clockwise, the new positions will be:
 * i,j -> j, n - i - 1
 * Element matrix[0][0] (1) moves to rotated[0][2].
 * Element matrix[0][1] (2) moves to rotated[1][2].
 * Element matrix[0][2] (3) moves to rotated[2][2].
 *
 * Element matrix[1][0] (4) moves to rotated[0][1].
 * Element matrix[1][1] (5) moves to rotated[1][1].
 * Element matrix[1][2] (6) moves to rotated[2][1].
 *
 * Element matrix[2][0] (7) moves to rotated[0][0].
 * Element matrix[2][1] (8) moves to rotated[1][0].
 * Element matrix[2][2] (9) moves to rotated[2][0].
 * @Version: 1.0
 */
public class N1_Rotate {
    // brute force, o(n2), o(n2)
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - i - 1] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rotated[i][j];
            }
        }
    }
    // Rotate By Four Cells


    /*
     * [ 1  2  3 ]
     * [ 4  5  6 ]
     * [ 7  8  9 ]
     * reverse
     *
     * [ 7  8  9 ]
     * [ 4  5  6 ]
     * [ 1  2  3 ]
     * i = 1, j 0->1. 8, 4 互换
     * i = 2, j 0->2, 9,1 互换, 6,2互换，
     *
     */
    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];

        // Reverse the matrix vertically
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = tmp;
            }
        }
        // Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    @Test
    public void testRotate() {
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        rotate3(matrix);
    }
}
