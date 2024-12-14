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
    // rotate each number, move matrix[i][j] to rotated[j][n - i - 1], rotated[j][n - i - 1] = matrix[i][j];
    // eg (0,0) -> (0,1 = 2 - 0 - 1), (0,1) -> (1,1 = 2 - 0 - 1) n = 2
    // copy rotated -> matrix
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
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        int left = 0;
        int right = n - 1;
        // Process each layer
        while (left < right) {
            for (int i = 0; i < right - left; i++) {
                int top = left;
                int bottom = right;
                //save the topleft
                int tmp = matrix[top][left + i];
                // move bottom left into top left
                matrix[top][left + i] = matrix[bottom - i][left];
                // move bottom right into bottom left
                matrix[bottom - i][left] = matrix[bottom][right - i];
                // move top right into bottom right
                matrix[bottom][right - i] = matrix[top + i][right];
                // move top left into top right
                matrix[top + i][right] = tmp;
            }
            left++;
            right--;
        }
    }

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
                System.out.println("martix["+i+"]["+j +"]");
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
    /*
        1. i = 0, j = 0, j == i, continue;
        2. i = 1, j = 0 < i = 1; matrix[1][0] <-> matrix[0][1];
        3. i = 2; j = 0 < i = 2; matrix[2][0] <-> matrix[0][2]; j++, j = 1 < 2; matrix[2][1] -> matrix[1][2]
    */

    @Test
    public void testRotate() {
//        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate2(matrix);
    }
}
