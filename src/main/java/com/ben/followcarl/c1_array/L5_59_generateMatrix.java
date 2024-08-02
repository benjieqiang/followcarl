package com.ben.followcarl.c1_array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-06  20:06
 * @Description:
 * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * @Version: 1.0
 */
public class L5_59_generateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int count = 1;
        while (count <= n * n) {
            // (0,0) -> (0,2) i从左到右
            for (int i = left; i <= right; i++) {
                res[top][i] = count++;
            }
            top++;
            // (1,right) -> (2, right) i从上到下；
            for (int i = top; i <= bottom; i++) {
                res[i][right] = count++;
            }
            right--;

            // (低, right) -> (低,0) i从右到左
            for (int i = right; i >= left; i--) {
                res[bottom][i] = count++;
            }
            bottom--;
            // (1,0) -> (1,0) i从下到上，i行left列
            for (int i = bottom; i >= top; i--) {
                res[i][left] = count++;
            }
            left++;
        }

        return res;
    }

    @Test
    public void testGenerateMatrix() {
        int n = 3;
        int[][] ints = generateMatrix(n);
        print(ints);
    }

    public int[][] generateMatrix3(int n) {
        int[][] res = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int count = 1;
        while (count <= n * n) {
            //left,top > right,top
            for (int i = left; i <= right; i++) {
                res[top][i] = count++;
            }
            top++;
            // right,top > right,bottom
            for (int j = top; j <= bottom; j++) {
                res[j][right] = count++;
            }
            right--;
            // right, bottom > left, bottom
            for (int i = right; i >= left; i--) {
                res[bottom][i] = count++;
            }
            bottom--;
            // left,bottom > left, top
            for (int j = bottom; j >= top; j--) {
                res[j][left] = count++;
            }
            left++;
        }
        return res;
    }

    private int[][] generateMatrix2(int n) {
        int[][] res = new int[n][n];

        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;

        int count = 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                res[top][i] = count++;
            }
            if (++top > bottom) break;
            for (int i = top; i <= bottom; i++) {
                res[i][right] = count++;
            }
            if (--right < left) break;
            for (int i = right; i >= left; i--) {
                res[bottom][i] = count++;
            }
            if (--bottom < top) break;
            for (int i = bottom; i >= top; i--) {
                res[i][left] = count++;
            }
            if (++left > right) break;
        }

        return res;
    }

    private void print(int[][] nums) {
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
        System.out.println("****打印完成****");
    }
}
