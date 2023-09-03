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
        // 同行是否重复
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }
        // 同列是否重复
        for (int j = 0; j < 9; j++) {
            if (board[j][col] == val) {
                return false;
            }
        }
        // 9宫格里是否重复
        int startRow = (row / 3) * 3;
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

/*
*
ChatGPT
这段代码的逻辑用于确定当前位置 (row, col) 所属的 3x3 子数独块的左上角位置坐标 (startRow, startCol)。这是因为在数独游戏中，整个9x9的数独棋盘被分成9个3x3的子数独块，每个子数独块中的数字不能重复。

让我来解释一下这段代码的逻辑：

row 和 col 表示当前位置的行和列坐标。
(row / 3) 用于确定当前行所在的子数独块的行索引。因为每个子数独块有3行，所以 (row / 3) 会将棋盘的行分成若干组，每组对应一个子数独块。
(col / 3) 用于确定当前列所在的子数独块的列索引。同样，每个子数独块有3列，所以 (col / 3) 会将棋盘的列分成若干组，每组对应一个子数独块。
(row / 3) * 3 和 (col / 3) * 3 是为了将行和列索引映射回子数独块的左上角位置。因为子数独块的行和列索引是从0到2，所以乘以3可以将它们映射回棋盘上的坐标。
举个例子，假设 (row, col) 是 (4, 7)，即棋盘上的一个位置。计算 (row / 3) * 3 会得到 1 * 3，即 startRow 为 3。计算 (col / 3) * 3 会得到 2 * 3，即 startCol 为 6。这意味着 (4, 7) 所在的子数独块的左上角位置坐标是 (3, 6)，这是一个3x3子数独块的左上角位置。

这个逻辑用于帮助我们在检查子数独块内是否存在重复的数字时，可以从子数独块的左上角位置开始遍历该子数独块。这是因为在一个子数独块内，每个数字只能出现一次。
*
* */