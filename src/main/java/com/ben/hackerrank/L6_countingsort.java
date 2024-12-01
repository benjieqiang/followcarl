package com.ben.hackerrank;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-11-30  11:52
 * @Description: TODO
 * @Version: 1.0
 */
public class L6_countingsort {
    public static List<Integer> countingSort(List<Integer> arr) {
        // Write your code here
        List<Integer> resList = new ArrayList<>(100);
        for (int num : arr) {
            if (resList.get(num) != null) resList.set(num, resList.get(num) + 1);
            else resList.set(num, 1);
        }

        return resList;
    }
}
