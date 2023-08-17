package com.ben.followcarl.acm;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-08-15  23:59
 * @Description:
 * 考古学家发现墓碑上有神秘的字符。经过仔细研究，发现原来这是开启古墓入口的方法。
 * 墓碑上有2行字符串，其中第一个串的长度为偶数，现在要求把第2个串插入到第一个串的正中央，如此便能开启墓碑进入墓中。
 *
 * @Version: 1.0
 */
import java.util.Scanner;

public class L15_MysteryCharacter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine(); // 吸收一个回车，因为输入n之后，要输入一个回车
            while (n-- > 0) {
                String line1 = sc.nextLine();
                String line2 = sc.nextLine();

                String res = "";
                int index = line1.length() / 2;
                res = line1.substring(0, index) + line2 + line1.substring(index, line1.length());
                System.out.println(res);
            }
        }
    }

}
