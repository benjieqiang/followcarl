package com.ben.followcarl.c10_dp;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-28  20:48
 * @Description: 零钱兑换2
 * @Version: 1.0
 */
public class L14_528_change {
    /*
    关于组合和排列的问题还是有些不解。
    以下仅为自己的理解：先遍历物品后遍历背包是这样，
    比如，外层循环固定coins【1】，在内层循环遍历背包时，随着背包不断增加，coins【1】可以重复被添加进来，而由于外层循环固定了，
    因此coins【2】只能在下一次外层循环添加进不同大小的背包中，这么看的话，coins【i+1】只能在coins【i】之后了；
    如果先遍历背包后遍历物品，那么外层循环先固定背包大小j，然后在大小为j的背包中循环遍历添加物品，然后在下次外层循环背包大小变为j+1，
    此时仍要执行内层循环遍历添加物品，也就会出现在上一轮外层循环中添加coins【2】的基础上还能再添加coins【1】的情况，
    那么就有了coins【1】在coins【2】之后的情况了
*/
    public int change(int amount, int[] coins) {

    }
}
