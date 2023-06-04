package com.ben.followcarl.c9_greedy;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-03  12:49
 * @Description: h是身高，k是前面有k个人身高大于h；
 * @Version: 1.0
 */
public class L11_406_reconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        // 身高从大到小排（身高相同k按照从小到大排，k小的站前面）注意：sort里面的自定义排序，返回值=0，a=b; <0, a小于b；>0, a大于b。
        // 从大到小排，则只需返回b - a; 从小到大排，返回a - b；
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                System.out.println(a[1] - b[1]);
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        LinkedList<int[]> que = new LinkedList<>();

        for (int[] p : people) {
            que.add(p[1],p); // 根据k的索引插入p到队列的对应位置；
        }

        return que.toArray(new int[people.length][]);
    }

    @Test
    public void testRes() {
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] res = reconstructQueue(people);
        System.out.println(res);
    }

}
