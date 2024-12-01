package com.ben.hackerrank;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-11-30  16:00
 * @Description:
 * https://drive.usercontent.google.com/download?id=1t_7fU2LD06qfyIOiFTJ51X8npx0qmcca&export=view&authuser=0
 * 
 * @Version: 1.0
 */
public class L8_flippingMatrix {
    // m[i][j] ⇌ m[i][(2n-1)-j] ⇌ m[(2n-1)-i][j] ⇌ m[(2n-1)-i][(2n-1)-j]
    public static int flippingMatrix(List<List<Integer>> matrix) {
        int length = matrix.size();
        int sum = 0;
        for (int i = 0; i < length / 2; i++) {
            for (int j = 0; j < length / 2; j++) {
                int n1 = matrix.get(i).get(j);
                int n2 = matrix.get(i).get(length - j - 1);
                int n3 = matrix.get(length - i - 1).get(j);
                int n4 = matrix.get(length - i - 1).get(length - j - 1);
                sum += Math.max(Math.max(n1, n2), Math.max(n3, n4));
            }
        }
        return sum;
    }
}
