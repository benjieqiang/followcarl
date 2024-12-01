package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-11-20  11:16
 * @Description: TODO
 * @Version: 1.0
 */
class Main {
    public static void main(String[] agrs) {
        int a = 2;
        int b = 3;
        int res = pow(a, b);
        System.out.println(res);
    }

    private static int pow(int a, int b) {
        if (b == 0) return 1;
        int res = 1;
        while (b-- > 0) {
            res *= a;
        }
        return res;
//        for (int i = 0; i < b; i ++) {
//            res *= a;
//        }
//        return res * res;

    }
}