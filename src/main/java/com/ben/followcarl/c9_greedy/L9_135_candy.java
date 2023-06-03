package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-03  12:29
 * @Description: TODO
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
        for (int i : resCandy) {
            sum += i;
        }
        return sum;
    }

    @Test
    public void testCandy() {
        int[] ratings = {1,0,2};
        System.out.println(candy(ratings));
    }
}
