package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-15  23:04
 * @Description: 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例: 输入: n = 4, k = 2 输出: [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 * <p>
 * 分析回溯问题的时间复杂度，有一个通用公式：路径长度×搜索树的叶子数。对于本题，它等于 O(k⋅C(n,k))。
 * C(n,k)= k!⋅(n−k)!/n!
 * 空间复杂度: O(n)
 * @Version: 1.0
 */
public class L1_77_combine {

    // 不用回溯，就得用暴力for循环，比如n=100，k=50，那么需要for循环50次，找到1-100中所有可能的50个数字的组合。不现实。
    // 回溯
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>(); //为了移除每次加入的最后一个元素，

    void backtracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            res.add(new LinkedList<>(path)); // 往res中加入一个新集合,内容是path;
            return; //返回到上一层；
        }
        // 单层递归逻辑,树的宽度由n来决定,树的高度由k来决定；
        // 剪枝：i <= n - (k - path.size()) + 1
        // 当递归进入下一层时，已经从 startIndex 开始选取了某个数字 i，这个数字已经被加入到当前的组合（path）中了。
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            // 下一层的递归不再需要从 startIndex 开始，因为我们已经选取了一个数字 i，所以要从 i + 1 开始继续选择，
            // 避免重复选择同一个数字，并确保组合中的数字按顺序排列。直到path的长度为k
            backtracking(n, k, i + 1);
            path.remove(path.size() - 1);

        }
        // 剪枝优化： 求至多从哪里开始？
        /* 1. path.size(): 已经选取的元素个数；
         * 2.  k - path.size() : 还需要从集合中选几个元素；
         * 3.  for循环还需要遍历多少次呢？
         *   n - (k - path.size()) + 1
         *  举例: 为啥要加1呢？
         *       如果path.size() = 0, n = 4，k = 3，
         *       想象树顶是集合[1,2,3,4],进来之后startIndex是1，可以选1/2/3/4.
         *      （path.size为0）n - (k - 0) + 1 即 4 - ( 3 - 0) + 1 = 2。
         *       也就是说，第一次选元素，边界上界就是2.选3，4都没用，凑不够k=3个数。
         * */
    }

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || n < k) {
            return null;
        }
        // startIndex 控制的是本层递归中的起始位置，它是上一次递归调用传入的值，指示本层从哪个位置开始遍历。
        backtracking(n, k, 1);
        return res;
    }

    @Test
    public void testCombine() {
        int n = 4;
        int k = 2;
        List<List<Integer>> res = combine(4, 3);
        System.out.println(res); // [[1, 2, 3], [1, 2, 4], [1, 3, 4], [2, 3, 4]]
    }

}
