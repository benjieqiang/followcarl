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
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释：nums1中拿出4，4在nums2的2号位置，下一个元素是2，比4小，所以res[0] = -1;
 * nums1中拿出1，1在nums2的0号位置，下一个元素是3，3比1大，res[1] = 3;
 * nums1中拿出2，2在nums2的3号位置，已经是最大的下标了，res[2] = -1;
 * <p>
 * 单调栈: 从栈顶到栈底是从小到大,递增的顺序,
 * 作用: 用来看nums2中的某个元素的右边比他大的位置是多少，所以遍历数组2；
 * 遍历数组2，
 * 1. 发现有比栈顶元素位置对应的数值小或等于的元素就放入栈中；
 * 2. 当前元素a比栈顶元素对应位置的数值大，判断当前元素a是否在nums1中存在，存在就能找到该元素在nums1中的下标位置，
 * 3. 把该下标的元素的值赋值成元素a。用完之后就pop当前的栈顶元素。把当前遍历的元素a放入栈中参与下一次的比较。
 * <p>
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
                    // 找到了右边大的的元素，那么需要把该元素的在nums1中的位置先找到，找到之后再赋值成右边大的元素。
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
