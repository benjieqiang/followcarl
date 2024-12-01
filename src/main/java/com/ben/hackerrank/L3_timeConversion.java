package com.ben.hackerrank;

import org.junit.Test;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-11-30  10:52
 * @Description:
 * Given a time in -hour AM/PM format, convert it to military (24-hour) time.
 * Note:
 * - 12:00:00AM on a 12-hour clock is 00:00:00 on a 24-hour clock.
 * - 12:00:00PM on a 12-hour clock is 12:00:00 on a 24-hour clock.
 * - 1-11 am -> 1-11 on 24-hour clock;
 * - 1-11 pm -> 13-23 on 24-hour clock;
 * @Version: 1.0
 */
public class L3_timeConversion {


    public static String timeConversion(String s) {
        int n = s.length();
        String period = s.substring(n-2);
        int hour = Integer.parseInt(s.substring(0,2));
        String minAndSec = s.substring(2, n-2);
        if (period.equals("AM")) {
            if (hour == 12) hour = 0; // 12:00am -> 0:00
        } else {
            if (hour != 12) hour += 12; // (1-11pm)-> (13->23)
        }

        return String.format("%02d%s", hour, minAndSec);
    }

    @Test
    public void testTimeConversion() {
        String s = "12:00:23PM";
        String s1 = "12:00:23AM";
        String s2 = "10:00:23AM";
        String s3 = "02:22:23PM";

        System.out.println(timeConversion(s));
        System.out.println(timeConversion(s1));
        System.out.println(timeConversion(s2));
        System.out.println(timeConversion(s3));
    }
}
