package com.ben.hackerrank;

import java.util.List;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-11-30  10:10
 * @Description: int 2 float
 * @Version: 1.0
 */
public class L1_plusMinus {
        /*
         * Complete the 'plusMinus' function below.
         *
         * The function accepts INTEGER_ARRAY arr as parameter.
         */

        public static void plusMinus(List<Integer> arr) {
            // Write your code here
            int length = arr.size();
            int n1 = 0;
            int n2 = 0;
            int n3 = 0;
            for (int num : arr) {
                if (num == 0) n3++;
                else if (num > 0) n1++;
                else if (num < 0) n2++;
            }
            System.out.println((float)n1 / length);
            System.out.println((float)n2 / length);
            System.out.println((float)n3 / length);
        }
}
