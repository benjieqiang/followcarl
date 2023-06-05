package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-03  12:49
 * @Description: h是身高，k是前面有k个人身高大于h；{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}
 * 如果按照k进行排序，发现h和k都不能确定，即第五个元素是[5,2],但是他前面有四个h大于或等于2的人，不满足。
 * 而且此时，h身高也没有按照顺序排序。
 * 所以需要按照h进行降序(因为看的是前面有多少人比我高)，相同身高h按照k从小到大排列，比如[7,0]和【7，1】肯定是后者在后面，所以由小到大排序；
 * @Version: 1.0
 */
public class L11_406_reconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        // 身高从大到小排（身高相同k按照从小到大排，k小的站前面）注意：sort里面的自定义排序，返回值=0，a=b; <0, a小于b；>0, a大于b。
        // 从大到小排，则只需返回b - a; 从小到大排，返回a - b；
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
//        for (int[] num : people) {
//            System.out.print(Arrays.toString(num) + ",");
//        }
        //此时已经排好序，[7, 0],[7, 1],[6, 1],[5, 0],[5, 2],[4, 4],[5, 0]
        LinkedList<int[]> que = new LinkedList<>();
        // 这样插入的原理是啥？我们遍历整个people数组，每次看k的位置，依次加入[7, 0],[7, 1]
        // 遇到[6,1]，必须得放到他两中间，因为我们的people数组已经按照h进行排序了，满足[7,1]前面只有一个比他高的元素
        // void add(int index，Object element)：此方法在列表中的指定索引处插入元素。它将当前位于该位置的元素(如果有)和任何后续元素右移(将在其索引处增加一个)。

        for (int[] p : people) {
            que.add(p[1], p); // 根据k的索引插入p到队列的对应位置；
        }

        return que.toArray(new int[people.length][]);
    }

    @Test
    public void testRes() {
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] res = reconstructQueue(people);
        for (int[] num : res) {
            System.out.println(Arrays.toString(num));
        }
    }

}
