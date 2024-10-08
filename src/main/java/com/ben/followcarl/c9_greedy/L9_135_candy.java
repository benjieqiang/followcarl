package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-03  12:29
 * @Description: N个孩子发糖果;
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 思路: 先确定右边, 再确定左边;
 *  1. 从左往右, 只要右孩子比左孩子大,就在左孩子基础上加1个糖, 否则只给1个糖;
 *  2. 从右往左, 只要左孩子比右孩子大，且 左孩子糖比右孩子糖要少或等于, 那就得给左孩子在右孩子的基础上加上一个糖;
 * @Version: 1.0
 */
public class L9_135_candy {
    public int candy(int[] ratings) {
        int[] resCandy = new int[ratings.length]; //用来记录每个孩子的糖果
        resCandy[0] = 1; //第一个孩子肯定有一个糖果
        // 从左向右，右孩子大于（评分高）左孩子，要在它的基础上多一个糖,否则只给1个糖
        for (int i = 1; i < ratings.length; i++) {
            resCandy[i] = ratings[i] > ratings[i - 1] ? resCandy[i - 1] + 1 : 1;
        }
        // 从右向左，左孩子比右孩子大，且左孩子糖少于或等于右孩子的糖；
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && resCandy[i] <= resCandy[i + 1]) {
                resCandy[i] = resCandy[i + 1] + 1;
            }
        }
        int sum = 0;
        for (int num : resCandy) sum += num;
        return sum;
    }

    @Test
    public void testCandy() {
//        int[] ratings = {1,0,2};
        int[] ratings = {1,3,2,2,1};
        System.out.println(candy(ratings));
    }
}
