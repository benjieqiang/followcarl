package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  17:56
 * @Description: 每门课的成绩分为A、B、C、D、F五个等级，为了计算平均绩点，规定A、B、C、D、F分别代表4分、3分、2分、1分、0分。
 * 输入:
 * A B C D F
 * B F F C C A
 * D C E F
 * 每组输入数据占一行，由一个或多个大写字母组成，字母之间由空格分隔。
 * 输出:
 * 2.00
 * 1.83
 * Unknown
 * 每组输出结果占一行。如果输入的大写字母都在集合｛A,B,C,D,F｝中，则输出对应的平均绩点，结果保留两位小数。否则，输出“Unknown”。
 * @Version: 1.0
 */
import java.util.Scanner;
public class L7_AveragePoint {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            double avg = 0;
            boolean flag = false;
            String[] digits = line.split(" ");
            for (String digit : digits) {
                int num = convert(digit);
                if (num < 0) {
                    flag = true;
                    break;
                }
                avg += num * 1.0 / digits.length;
            }
            if (flag) System.out.println("Unknown");
            else System.out.printf("%.2f\n", avg);
        }
    }
    private static int convert(String digit) {
        switch (digit) {
            case "A":
                return 4;
            case "B":
                return 3;
            case "C":
                return 2;
            case "D":
                return 1;
            case "F":
                return 0;
            default:
                return -1;
        }

    }

}
