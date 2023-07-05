package com.ben.followcarl.c1_array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: benjieqiang
 * @CreateTime: 2023-07-02  20:23
 * @Description: 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * <p>
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。
 * 例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Version: 1.0
 */
public class L2_27_removeElement {

    /**
     * @param nums:
     * @param val:
     * @return int
     * @description 双层for循环，一层用来找目标值，如果找到，则需要for循环把元素依次向前走一步，i--，length--；
     * @author benjieqiang
     * @date 2023/7/2 8:54 PM
     */
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < length; j++) {
                    nums[j - 1] = nums[j];
                }
                i--; // 因为下标i以后的数值都向前移动了一位，所以i也向前移动一位
                length--; // 从i+1位置开始的元素统一往前走一步，所以length减一；
            }
        }
//        System.out.println(Arrays.toString(nums));
        return length;
    }

    /**
     * @param nums:
     * @param val:
     * @return int
     * @description 双指针:
     * @author benjieqiang
     * @date 2023/7/2 8:55 PM
     */
    public int removeElement2(int[] nums, int val) {
        int slow = 0; // slow指向的是新数组的下标；
        // fast用来寻找新数组的元素，就是不含有目标元素的数组；
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    @Test
    public void testRemoveElement() {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
//        int[] nums = {0,1,2,3,4,5,6};
//        int val = 2;
//        int[] nums = {0,1,2,2,3,0,4,2};
//        int val = 2;
        System.out.println(removeElement2(nums, val));
    }
}
