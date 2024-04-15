package com.ben.followcarl.sortalgrithum;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-03-15  15:31
 * @Description: TODO
 * @Version: 1.0
 */
public class PascalTriangle {

    public static void main(String[] args) {
        int numRows = 10;
        printPascalTriangle(numRows);
    }

    public static void printPascalTriangle(int numRows) {
        int[][] triangle = new int[numRows][numRows];

        // 填充三角形
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    triangle[i][j] = 1; // 每行的第一个和最后一个元素为1
                } else {
                    triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j]; // 其他元素为上一行相邻两个数字之和
                }
                System.out.print(triangle[i][j] + " ");
            }
            System.out.println();
        }

        // 打印三角形
//        for (int i = 0; i < numRows; i++) {
//            // 打印空格来对齐
//            for (int k = 0; k < numRows - i; k++) {
//                System.out.print(" ");
//            }
//            // 打印数字
//            for (int j = 0; j <= i; j++) {
//                System.out.print(triangle[i][j] + " ");
//            }
//            System.out.println(); // 换行
//        }
    }
}
