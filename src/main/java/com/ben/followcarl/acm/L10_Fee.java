package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  22:54
 * @Description: TODO: 小明每天的话费是1元，运营商做活动，手机每充值K元就可以获赠1元，一开始小明充值M元，问最多可以用多少天？
 * 注意赠送的话费也可以参与到奖励规则中
 * 输入:
 * 2 2
 * 4 3
 * 13 3
 * 0 0
 *
 * 输出:
 * 3
 * 5
 * 19
 * @Version: 1.0
 */
import java.util.Scanner;

public class L10_Fee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int m = sc.nextInt(); // 话费充值m元
            int k = sc.nextInt(); // 充值了k元
            if (m == 0 && k == 0) break;
            int sum = m + m / k; //  每充值k元,获赠1元,实际上话费总额是m + m/k;
            int remain = m / k + m % k;
            while (remain / k != 0) {
                sum += remain / k;
                remain = remain / k + remain % k;
            }
            System.out.println(sum);
        }
    }
}