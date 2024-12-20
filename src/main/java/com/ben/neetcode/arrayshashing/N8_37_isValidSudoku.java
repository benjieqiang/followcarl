package com.ben.neetcode.arrayshashing;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-18  11:40
 * @Description: You are given a a 9 x 9 Sudoku board board. A Sudoku board is valid if the following rules are followed:
 *
 * Each row must contain the digits 1-9 without duplicates.
 * Each column must contain the digits 1-9 without duplicates.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.
 * Return true if the Sudoku board is valid, otherwise return false
 *
 * Note: A board does not need to be full or be solvable to be valid.
 * @Version: 1.0
 */
public class N8_37_isValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9]; // 9 by 9, rows[i][index]表示第i行这个index数出现的次数；
        int[][] cols = new int[9][9];
        int[][][] square = new int[3][3][9]; // 3*3个square，看0-8元素出现的次数
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch == '.') continue;
                int index = ch - '0' - 1; // 0-8的范围。
                rows[i][index]++;
                cols[j][index]++;
                square[i/3][j/3][index]++;
                if (rows[i][index] > 1 || cols[j][index] > 1 ||
                        square[i/3][j/3][index] > 1)
                    return false;
            }
        }
        return true;
    }
}
