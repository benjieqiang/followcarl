package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-09-03  21:13
 * @Description: 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位
 * <p>
 * 思路:
 * 之前的回溯都是一维的集合,现在N皇后问题是一个二维的集合,
 * 如果暴力解法 n =4, 则需要for循环4次,来判断是否满足条件;
 * <p>
 * 回溯:
 * 树形结构: 树的宽度就是n; 树的深度也是n,也就是说,当我们遍历到n的时候,如果符合条件就收获结果;
 * <p>
 * 回溯三部曲:
 * 1. 返回值和入参: 不需要返回值, 入参: int n; //这一层for循环得n次  row: 表示第几行;
 * 2. 终止条件: row == n; 往棋盘里面加入结果集;当然在单层回溯的时候就需要判断是否符合条件;
 * 3. 单层回溯逻辑:
 * for循环从0开始到n-1结束;
 * 判断此时是否符合条件;
 * 不符合跳过;
 * 符合, 棋盘放置皇后 =》 递归下一层行数从row+1开始,
 * 结束撤销上一步操作, 棋盘放置.
 *
 *
 * 时间复杂度：
 *
 * 在solveNQueens方法中，使用了回溯算法来尝试放置每一个皇后，最坏情况下需要遍历所有可能的解，因此时间复杂度为O(N!)，其中N是棋盘的大小。
 * 在isValid方法中，对每个放置的位置都要检查是否与之前的皇后位置冲突，这需要O(N)的时间。
 * 综合考虑，总体时间复杂度为O(N! * N)，因为回溯算法的复杂度是指数级的。
 * 空间复杂度：
 *
 * 使用了一个二维字符数组chessboard来表示棋盘，其大小为NxN，因此空间复杂度为O(N^2)。
 * 使用了一个res列表来存储所有解，最坏情况下可能有N!个解，每个解都是一个N*N的棋盘，因此额外空间复杂度为O(N!*N^2)。
 * 综合考虑，总体空间复杂度为O(N!*N^2)。
 * @Version: 1.0
 */
public class L14_51_solveNQueens {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (char[] ch : chessboard) {
            Arrays.fill(ch, '.');
        }
        backtracking(chessboard, n, 0);
        return res;
    }

    private void backtracking(char[][] chessboard, int n, int row) {
        if (row == n) { // 表示成功放置了所有的皇后，然后将当前棋盘状态加入到结果中
            List list = new ArrayList<>();
            for (char[] ch : chessboard) {
                list.add(new String(ch)); // 每一行转成字符串后，放到list中；
            }
            res.add(list);
        }

        for (int i = 0; i < n; i++) {
            if (!isValid(chessboard, row, i, n)) continue;
            chessboard[row][i] = 'Q';
            backtracking(chessboard, n, row + 1);
            chessboard[row][i] = '.';
        }
    }

    /**
     * @param board:
     * @param row:
     * @param col:
     * @param n:
     * @return boolean
     * @description 皇后不能同列, 不能同行, 不能同斜线(45和135).
     * 没有同行的原因：因为在单层搜索的过程中，每一层递归，只会选for循环（也就是同一行）里的一个元素，所以不用去重了。
     * @author benjieqiang
     * @date 2023/9/3 9:37 PM
     */
    private boolean isValid(char[][] board, int row, int col, int n) {
        // 检查列 该列从0开始遍历到row
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // 检查45度对角线, 左上方遍历;从（row - 1, col - 1）到（0，0）
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // 检查135度对角线, 右上方遍历，从(row - 1, col + 1)到(0，n-1)
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }
    @Test
    public void testNQueen(){
        int n = 4;
        System.out.println(solveNQueens(n));
    }
}
