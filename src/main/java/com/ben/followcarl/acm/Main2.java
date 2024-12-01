package com.ben.followcarl.acm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-11-20  11:16
 * @Description: TODO
 * @Version: 1.0
 */
class Main2 {
    public static void main(String[] agrs) {
        int[] array = {2,3,5,7};
        int target = 7;
        int[] resList = getArray(array, target);
        System.out.println(Arrays.toString(resList));
    }

    private static int[] getArray(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(target - array[i])) {
                int[] res = new int[2];
                res[0] = i;
                res[1] = map.get(target - array[i]);
                return res;
            }
            map.put(array[i], i);
        }
        return null;
    }


}