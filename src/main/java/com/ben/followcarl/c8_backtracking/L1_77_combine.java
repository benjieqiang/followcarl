package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-15  23:04
 * @Description: TODO
 * @Version: 1.0
 */
public class L1_77_combine {

    // 不用回溯，就得用暴力for循环，比如n=100，k=50，那么需要for循环50次，找到1-100中所有可能的50个数字的组合。不现实。
    // 回溯
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>(); //为了移除每次加入的最后一个元素，
    void backtracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            res.add(new LinkedList<>(path));
            return;
        }
        // 单层递归逻辑,树的宽度由n来决定,树的高度由k来决定；
        // 剪枝：n - (k - path.size()) + 1
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backtracking(n, k, i + 1); // 已经取出来一个数字i之后，那么接着从i+1的位置继续取数，直到path的长度为k
            path.removeLast();
        }
        // 剪枝优化： 求至多从哪里开始？
        /* 1. path.size(): 已经选取的元素个数；
        * 2.  k - path.size() : 还需要选几个元素；
        * 3.  for循环还需要遍历多少次呢？
        *   n - (k - path.size()) + 1
        *  比如，path.size() = 0, n = 4，k = 3， 目前没有从集合中选任何元素（path.size为0）
        * ，n - (k - 0) + 1 即 4 - ( 3 - 0) + 1 = 2。 ，可以是组合[2, 3, 4]。
        * */
    }
    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return res;
    }

    @Test
    public void testCombine() {
        int n = 4;
        int k = 2;
        List<List<Integer>> res = combine(4, 2);
        System.out.println(res);
    }

}
