package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-31  21:02
 * @Description: TODO
 * @Version: 1.0
 */

import java.util.*;

public class L41_islandsNum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[][] nums = new int[m][n];
            for (int index = 0; index < k; index ++){
                int i = sc.nextInt();
                int j = sc.nextInt();

                nums[i][j] = 1;
            }

            landsNum(nums, k);
        }
    }

    private static void landsNum(int[][] nums, int k) {
        int res = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                if (nums[i][j] == 1) {
                    res++;
                    count++;
                    System.out.print(res + " ");

                    dfs(nums, i, j);
                }
            }
        }
    }

    private static void dfs(int[][] nums, int i, int j) {
        if (i < 0 || i >= nums.length || j < 0 || j >= nums[0].length || nums[i][j] == 0) {
            return;
        }

        nums[i][j] = 0;

        dfs(nums, i - 1, j);
        dfs(nums, i + 1, j);
        dfs(nums, i, j - 1);
        dfs(nums, i, j + 1);
    }
}
