package com.ben.followcarl.c3_hashmap;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-11  20:28
 * @Description: hashMap常用的方法
 * @Version: 1.0
 */
public class L1_hashMapTest {

    @Test
    public void testHashMap() {
        HashMap<Integer, String> hmap = new HashMap<>();
        hmap.put(1, "baidu");
        hmap.put(2, "google");
        hmap.put(3, "java");
        hmap.put(4, "python");
        System.out.println(hmap); //{1=baidu, 2=google, 3=java, 4=python}
    }

    @Test
    public void testSubstring() {
        String s1 = "ate";
        String s2 = "eat";
        System.out.println(s1.substring(0, 2)); // 左闭右开
    }
    @Test
    public void testHashMapRemove() {
        HashMap<Integer, String> hmap = new HashMap<>();
        hmap.put(1, "baidu");
        hmap.put(2, "google");
        hmap.put(3, "java");
        hmap.put(4, "python");
        hmap.remove(3);
        System.out.println(hmap); //{1=baidu, 2=google, 4=python}
    }

    @Test
    public void testHashMapUpdate(){
        HashMap<Integer, String> hmap = new HashMap<>();
        hmap.put(1, "baidu");
        hmap.put(2, "google");
        hmap.put(3, "java");
        hmap.put(4, "python");

        // 改
        hmap.put(3, "c++");
        System.out.println(hmap); //{1=baidu, 2=google, 3=c++}

        // 查。 get(key)
        // 遍历
        for (Integer i : hmap.keySet()) {
            System.out.println(i + ":" + hmap.get(i));
        }
        // 遍历迭代 HashMap 中的所有值。
        for (String value : hmap.values()) {
            System.out.println(value);
        }
        // containsKey(key)
        System.out.println(hmap.containsKey(3)); //true
    }

    @Test
    public void testHashMapGetOrDefault() {
        HashMap<Integer, String> hmap = new HashMap<>();
        hmap.put(1, "baidu");
        hmap.put(2, "google");
        hmap.put(3, "java");
        hmap.put(4, "python");

        // getOrDefault(key, 0), 获取指定 key 对应对 value，如果找不到 key ，则返回设置的默认值
        System.out.println(hmap.getOrDefault(2, "哈哈")); //google，能找到
        System.out.println(hmap.getOrDefault(11, "哈哈")); // 哈哈，找不到
    }

    @Test
    public void testHashMapReplace() {
        HashMap<Integer, String> hmap = new HashMap<>();
        // 增
        hmap.put(1, "baidu");
        hmap.put(2, "google");
        hmap.put(3, "java");
        hmap.put(4, "python");
        // hashmap.replace(K key, V newValue) :
        // hashmap.replace(K key, V oldValue, V newValue)
        // key 存在,返回旧值
        String oldValue = hmap.replace(2, "zhihu");
        System.out.println("replace方法返回oldValue: " + oldValue); //replace方法返回oldValue: google
        System.out.println(hmap); //{1=baidu, 2=zhihu, 3=java, 4=python}
        // key 不存在,返回null
        String value = hmap.replace(10, "gao");
        System.out.println("key 10 返回的值：" + value); //null
        System.out.println(hmap); //{1=baidu, 2=zhihu, 3=java, 4=python}

        // key 存在， oldValue 存在，返回true,替换成功。否则返回false，替换不成功。
        boolean res = hmap.replace(2, "www", "haha");
        System.out.println(res); // false
        System.out.println(hmap); // {1=baidu, 2=zhihu, 3=java, 4=python}
        boolean res2 = hmap.replace(2, "zhihu", "haha");
        System.out.println(res2); //true
        System.out.println(hmap); // {1=baidu, 2=haha, 3=java, 4=python}
        // key不存在， 返回false,
        boolean res3 = hmap.replace(20, "zhihu", "haha");
        System.out.println(res3); //false
        System.out.println(hmap); // {1=baidu, 2=haha, 3=java, 4=python}
    }

}
