package com.ben.hackerrank;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-11-30  12:04
 * @Description: TODO
 * @Version: 1.0
 */
public class L7_frequency {
    public static List<Integer> countingSort(List<Integer> arr) {
        // Write your code here
        int[] resArray = new int[100];
        for (int num : arr) {
            resArray[num]++;
        }
        List<Integer> resultList = new ArrayList<>();
        for (int count : resArray) {
            resultList.add(count);
        }
        return resultList;
    }
}
