package com.ben.followcarl.c11_monostonestack;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-06-25  17:21
 * @Description: 下一个更大的元素
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集（为啥要强调这一句？因为每次找的时候思路是：从nums1中找到
 * 一个数字比如n，在nums2中找到n这个元素，看n此时在nums2的位置的下一个元素的大小，如果比n大，则找到了放到结果集，比n小，则输出-1）

 * 使用单调递增栈，但加入的是元素下标，为了好取。
 * 1. 首先加入nums2元素的首个元素下标，
 * 2. 遍历nums2, 当前元素小于等于栈顶元素，则入栈，维持一个单调递增栈；对外表现是下标从大到小；
 * 3. 当前元素大于栈顶元素，栈不为空；
 *      看此时栈顶元素是否在map中存在，不存在则弹出，因为得找nums1中各元素的下一个最大值；
 *      此时说明找到首个大于栈顶下标对应元素的值，赋值给该下标的元素为当前元素值，弹出，直到该元素不大于栈顶元素；
 *      当前元素下标入栈；

int[] nums1 = {4, 1, 2};
int[] nums2 = {1, 3, 4, 2};

1. map = {4:0, 1:1, 2:2}
2. stack = [0(1)] top is 0; 元素值单调递增栈
3. 遍历nums2, i = 1, 当前元素：3， 栈顶元素：nums2[0] = 1,
栈不空，map[1] = 1 （表示nums1中下标1）存在，拿下标index=1，此时res[1] = nums2[1] = 3, 弹出栈顶元素
当前元素下标1入栈, stack = [1(3)]
4. i = 2, 当前元素是4，栈顶下标1对应元素是3，大于，且栈不空，
map[3]不存在（map的意义是只看nums1中元素的下一个大元素），stack栈顶出栈 stack [], i = 2 入栈，stack = [2(4)]
5. i = 3, 当前元素是2, 栈顶元素是4，小于，i入栈， stack = [3(2), 2(4)]
 * 注意: Arrays.fill(nums, -1);
 * @Version: 1.0
 */
public class L2_496_nextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            hashMap.put(nums1[i], i);
        }

        Deque<Integer> st = new LinkedList<>();
        st.push(0);
        for (int i = 1; i < nums2.length; i++) {
            if (nums2[i] <= nums2[st.peek()]) {
                st.push(i);
            } else {
                while (!st.isEmpty() && nums2[i] > nums2[st.peek()]) {
                    // 找到了栈顶元素的首大元素，先看栈顶元素是否在map中存在，存在则忘对应index赋值首大元素
                    if (hashMap.containsKey(nums2[st.peek()])) {
                        int index = hashMap.get(nums2[st.peek()]);
                        res[index] = nums2[i];
                    }
                    st.pop();
                }
                st.push(i);
            }
        }
        return res;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        if (nums1.length == 0) return res;
        Arrays.fill(res, -1);
        HashMap<Integer, Integer> map = new HashMap<>(); // key: 元素, val: 下标
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }

        Deque<Integer> st = new LinkedList<>();
        st.push(0);
        // 从nums2中找
        for (int i = 1; i < nums2.length; i++) {
            if (nums2[i] <= nums2[st.peek()]) {
                st.push(i);
            } else {
                while (!st.isEmpty() && nums2[i] > nums2[st.peek()]) {
                    // 找到某值右边的大值nums2[i]. 先看是不是nums1中的元素;
                    if (map.containsKey(nums2[st.peek()])) {
                        int index = map.get(nums2[st.peek()]);
                        res[index] = nums2[i];
                    }
                    st.pop();
                }
                st.push(i);
            }
        }
        return res;
    }

    /*


 */

    @Test
    public void testNextGre() {
        int[] nums1 = {137, 59, 92, 122, 52, 131, 79, 236, 94, 171, 141, 86, 169, 199, 248, 120, 196, 168, 77, 71, 5, 198, 215, 230, 176, 87, 189, 206, 115, 76, 13, 216, 197, 26, 183, 54, 250, 27, 109, 140, 147, 25, 96, 105, 30, 207, 241, 8, 217, 40, 0, 35, 221, 191, 83, 132, 9, 144, 12, 91, 175, 65, 170, 149, 174, 82, 102, 167, 62, 70, 44, 143, 10, 153, 160, 142, 188, 81, 146, 212, 15, 162, 103, 163, 123, 48, 245, 116, 192, 14, 211, 126, 63, 180, 88, 155, 224, 148, 134, 158, 119, 165, 130, 112, 166, 93, 125, 1, 11, 208, 150, 100, 106, 194, 124, 2, 184, 75, 113, 104, 18, 210, 202, 111, 84, 223, 173, 238, 41, 33, 154, 47, 244, 232, 249, 60, 164, 227, 253, 56, 157, 99, 179, 6, 203, 110, 127, 152, 252, 55, 185, 73, 67, 219, 22, 156, 118, 234, 37, 193, 90, 187, 181, 23, 220, 72, 255, 58, 204, 7, 107, 239, 42, 139, 159, 95, 45, 242, 145, 172, 209, 121, 24, 21, 218, 246, 49, 46, 243, 178, 64, 161, 117, 20, 214, 17, 114, 69, 182, 85, 229, 32, 129, 29, 226, 136, 39, 36, 233, 43, 240, 254, 57, 251, 78, 51, 195, 98, 205, 108, 61, 66, 16, 213, 19, 68, 237, 190, 3, 200, 133, 80, 177, 97, 74, 138, 38, 235, 135, 186, 89, 201, 4, 101, 151, 31, 228, 231, 34, 225, 28, 222, 128, 53, 50, 247};
        int[] nums2 = {137, 59, 92, 122, 52, 131, 79, 236, 94, 171, 141, 86, 169, 199, 248, 120, 196, 168, 77, 71, 5, 198, 215, 230, 176, 87, 189, 206, 115, 76, 13, 216, 197, 26, 183, 54, 250, 27, 109, 140, 147, 25, 96, 105, 30, 207, 241, 8, 217, 40, 0, 35, 221, 191, 83, 132, 9, 144, 12, 91, 175, 65, 170, 149, 174, 82, 102, 167, 62, 70, 44, 143, 10, 153, 160, 142, 188, 81, 146, 212, 15, 162, 103, 163, 123, 48, 245, 116, 192, 14, 211, 126, 63, 180, 88, 155, 224, 148, 134, 158, 119, 165, 130, 112, 166, 93, 125, 1, 11, 208, 150, 100, 106, 194, 124, 2, 184, 75, 113, 104, 18, 210, 202, 111, 84, 223, 173, 238, 41, 33, 154, 47, 244, 232, 249, 60, 164, 227, 253, 56, 157, 99, 179, 6, 203, 110, 127, 152, 252, 55, 185, 73, 67, 219, 22, 156, 118, 234, 37, 193, 90, 187, 181, 23, 220, 72, 255, 58, 204, 7, 107, 239, 42, 139, 159, 95, 45, 242, 145, 172, 209, 121, 24, 21, 218, 246, 49, 46, 243, 178, 64, 161, 117, 20, 214, 17, 114, 69, 182, 85, 229, 32, 129, 29, 226, 136, 39, 36, 233, 43, 240, 254, 57, 251, 78, 51, 195, 98, 205, 108, 61, 66, 16, 213, 19, 68, 237, 190, 3, 200, 133, 80, 177, 97, 74, 138, 38, 235, 135, 186, 89, 201, 4, 101, 151, 31, 228, 231, 34, 225, 28, 222, 128, 53, 50, 247};
//        int[] nums2 = {1, 3, 4, 2};
        int[] res = nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }

    @Test
    public void testNextGre2() {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] res = nextGreaterElement2(nums1, nums2);

        System.out.println(Arrays.toString(res));
    }
}
