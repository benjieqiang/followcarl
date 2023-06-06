package com.ben.followcarl.c9_greedy;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-06  23:25
 * @Description: 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的
 * 遍历顺序问题：
 * 332，如果从前往后遍历，第2位减一，第3位取最大值得到329，此时还不是单调递增所以需要比较3和2，3减一，第二位取最大值得到299；
 * 如果是从后往前比较：332-》329-》299。始终能保证后面的值是最大的；
 * @Version: 1.0
 */
public class L16_738_monotoneIncreasingDigits {
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int flag = s.length(); //flag是害怕出现1000这种情况，如果每次更新右边的数，那么会得到900，不是最大的数，所以flag用来记录9从哪里开始
        for (int i = s.length() - 1; i > 0; i--) { // i > 0是因为有i-1的情况所以不能=0，否则会越界；
            if (chars[i - 1] > chars[i]) {
                // 更新flag
                flag = i;
                chars[i - 1]--;
            }
        }
        for (int i = flag; i < s.length(); i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(new String(chars));
    }

    @Test
    public void testMonotoneIncreasingDigits() {
//        int n = 1000;
        int n = 4321;
        System.out.println(monotoneIncreasingDigits(n));
    }
}
