package com.ben.followcarl.c8_backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-05-17  23:58
 * @Description: 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1: 输入: k = 3, n = 7 输出: [[1,2,4]]
 * <p>
 * 示例 2: 输入: k = 3, n = 9 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * <p>
 * #
 * @Version: 1.0
 */
public class L2_216_combinationSum3 {
    // 在1-9中找到和为n的k个数；
    List<List<Integer>> res1 = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    int sum = 0;

    /**
     * @param k:          1-9中选k个数
     * @param n:          和
     * @param startIndex: 决定了下一次递归从哪里开始，不能重复
     * @return void
     * @description TODO
     * @author benjieqiang
     * @date 2023/5/19 6:32 PM
     */

    void backtracking1(int k, int n, int startIndex) {
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
            backtracking1(k, n, i + 1);
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
            backtracking2(k, n, i + 1);
            path.removeLast();
            sum -= i;
        }
    }

    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> inner = new LinkedList<>();

    void backtracking(int k, int n, int index) {
        if (inner.size() > k || n < 0) return;
        if (inner.size() == k && n == 0) {
            res.add(new ArrayList<>(inner));
            return;
        }
        for (int i = index; i <= 9; i++) {
            inner.add(i);
            backtracking(k, n - i, i + 1);
            inner.removeLast();
        }
    }

    /**
     * @param k:
     * @param n:
     * @return List<List < Integer>>
     * @description 新写法:
     * 树的深度就是k, 最终子集集合的大小
     * 树的宽度就是9,
     * 递归三部曲: 1. 返回值和入参: n, k, index;
     * 2. 结束条件: 收集的子集size = k, 并且出现 n == 0;
     * 3. 单层递归逻辑: 从index出发,递归的加入到集合inner中,符合条件就走2;
     * 这里用了一个n - i的操作, 这样可以省一个变量sum, 不然这层递归结束, sum还需要-回去;
     * @author benjieqiang
     * @date 2023/8/30 7:49 PM
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(k, n, 1);
        return res;
    }


    @Test
    public void testSumCombine() {
        int k = 3, n = 9;
        System.out.println(combinationSum3(k, n));
    }

}
