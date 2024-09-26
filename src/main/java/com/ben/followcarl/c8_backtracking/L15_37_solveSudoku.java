package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-03  22:12
 * @Description: 数独问题
 * @Version: 1.0
 */
public class L15_37_solveSudoku {
    public void solveSudoku(char[][] board) {
        backtracking(board);
    }

    private boolean backtracking(char[][] board) {
        // 二维递归: 一个for循环遍历棋盘的行，一个for循环遍历棋盘的列, 一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！
        for (int i = 0; i < 9; i++) { // 遍历行
            for (int j = 0; j < 9; j++) { // 遍历列
                if (board[i][j] != '.') continue;// 只有.的地方是我们要填数字的位置;

                for (char k = '1'; k <= '9'; k++) { // (i, j) 这个位置放k是否合适
                    if (!isValid(i, j, k, board)) continue;
                    board[i][j] = k;
                    if (backtracking(board)) return true; // 如果找到合适一组立刻返回
                    board[i][j] = '.';

                }
                // 9个数都试完了，都不行，那么就返回false
                return false;
                // 因为如果一行一列确定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！
                // 那么会直接返回， 「这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！」
            }
        }
        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }

    /**
     * 判断棋盘是否合法有如下三个维度:
     * 同行是否重复
     * 同列是否重复
     * 9宫格里是否重复
     */
    private boolean isValid(int row, int col, char val, char[][] board) {
        // 同行是否重复，row行i列（0-8）行固定，与val相比
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }
        // 同列是否重复，i（0-8）行col列，列固定，比较val
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == val) {
                return false;
            }
        }
        // 9宫格里是否重复
        // 由于数独是9×9的棋盘，3×3的小方格共有9个。row / 3 会确定当前行属于哪个3×3的子方格，结果是0、1、2三个区域中的一个。通过 * 3，我们计算出该子方格的起始行号。
        int startRow = (row / 3) * 3;
        // col / 3 确定当前列属于哪个3×3子方格，结果也是0、1、2三个区域中的一个。通过 * 3，我们计算出该子方格的起始列号。
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] convertToCharBoard(String[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        char[][] charBoard = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                charBoard[i][j] = board[i][j].charAt(0);
            }
        }

        return charBoard;
    }

    @Test
    public void testSudoku() {

        String[][] board = {
                {"5", "3", ".", ".", "7", ".", ".", ".", "."},
                {"6", ".", ".", "1", "9", "5", ".", ".", "."},
                {".", "9", "8", ".", ".", ".", ".", "6", "."},
                {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
                {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
                {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
                {".", "6", ".", ".", ".", ".", "2", "8", "."},
                {".", ".", ".", "4", "1", "9", ".", ".", "5"},
                {".", ".", ".", ".", "8", ".", ".", "7", "9"}
        };
        char[][] chars = convertToCharBoard(board);
        solveSudoku(chars);
    }
}
