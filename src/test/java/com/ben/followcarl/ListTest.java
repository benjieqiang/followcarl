package com.ben.followcarl;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-16  21:10
 * @Description: TODO
 * @Version: 1.0
 */
public class ListTest {

    @Test
    public void testLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("baidu");
        list.add("alibaba");
        list.add("tencent");
        list.add("tiktok");
        list.forEach(s -> {
            System.out.println(s);
        });
        list.removeLast();

        System.out.println(list); //[baidu, alibaba, tencent]
    }
    @Test
    public void testLinkedListAddFirst() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("baidu");
        list.addFirst("alibaba");
        list.addFirst("tencent");
        list.addFirst("tiktok");
        list.forEach(s -> {
            System.out.println(s);
        });
        list.removeLast();

        System.out.println(list); // [tiktok, tencent, alibaba]

    }
}
