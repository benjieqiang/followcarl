package com.ben.followcarl.c10_dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-12  17:25
 * @Description: todo:背诵
 * 二叉搜索树是一个有序树：
 * 若它的左子树不空，则左子树上所有结点值均小于中节点
 * 若它的右子树不空，则右子树上所有结点值均大于中节点
 * 同时：左、右子树也分别为二叉搜索树
 * 分析：
 * n = 1 时 ，只有一个bst；1
 * n = 2 时，有两个bst，2    1
 *                   /      \
 *                  1        2
 *
 * n = 3时，有三种情况: 以数字1为节点有两个 1     1
 *                                     \     \
 *                                      2     3
 *                                      \    /
 *                                      3  2
 *                             从形状上来看，1为节点的二叉树数量 = 左子树有0个元素的bst数量(1)* 右子树有2个元素的bst数量(2)
 * 以数字2为节点的bst有1个   2
 *                      /  \
 *                     1    3
 *          从形上看，2为节点的二叉树数量 = 左边1个元素的bst(1) * 右边1个元素的bst(1)
 * 以数字3为节点的bst有2个       3     3
 *                          /     /
 *                         2     1
 *                        /       \
 *                       1         2
 *          从形上来看，以3为节点的二叉树数量 = 左子树有2个元素的bst * 右子树有0个bst
 *
 * 1. dp[n] 表示第n个元素的二叉树数量； 所以要定义n+1长度的数组;
 * dp[3] = dp[0] * dp[2] + dp[1] * dp[1] + dp[2] * dp[0]
 * 2. dp[i] += dp[j - 1] * dp[i - j] 左子树数量 * 右子树数量
 * 3. dp[0] = 1, 空节点也构成了一个bst
 * 4. 遍历顺序: i从1到n开始遍历(n表示有几个节点的bst), j从1到i(i个节点的树)
 * 5. 举例:
 *
 * @Version: 1.0
 */
public class L7_96_numTrees {
    public int numTrees(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) { //j从0遍历到i;能取到i;
                // 一共有i个元素, 去除根结点一个元素, 剩下第j个元素左子树有j - 1个元素，右子树有i - j个元素
                // (j - 1) + j + (i - j) = i
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    @Test
    public void testNumTrees() {
        int n = 3;
        System.out.println(numTrees(n));
    }

}
