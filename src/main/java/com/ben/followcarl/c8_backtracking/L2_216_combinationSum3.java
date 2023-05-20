package com.ben.followcarl.c8_backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-17  23:58
 * @Description: TODO
 * @Version: 1.0
 */
public class L2_216_combinationSum3 {
    // 在1-9中找到和为n的k个数；
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    int sum = 0;

    /**
     * @param k: 1-9中选k个数
     * @param n: 和
     * @param startIndex: 决定了下一次递归从哪里开始，不能重复
     * @return void
     * @description TODO
     * @author benjieqiang
     * @date 2023/5/19 6:32 PM
     */

    void backtracking(int k, int n, int startIndex) {
        if (path.size() == k) {
            if (sum == n) res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            if (sum > n) {
                path.removeLast();
                sum -= i;
                return;
            }
            backtracking(k, n, i + 1);
            path.removeLast();
            sum -= i;
        }
    }

    //无剪枝操作
    void backtracking2(int k, int n, int startIndex) {
        if (path.size() == k) {
            if (sum == n) res.add(new ArrayList<>(path));
            return;
        }
        // 树的宽度是9，题目要求是不重复取数；
        for (int i = startIndex; i <= 9; i++) {
            path.add(i);
            sum += i;
            backtracking(k, n, i + 1);
            path.removeLast();
            sum -= i;
        }
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(k, n, 1);
        return res;
    }
}
