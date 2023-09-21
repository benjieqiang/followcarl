package com.ben.followcarl.c1_array;

import org.junit.Test;

/**
 * @description 有序，找下标；
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 *
 * 时间复杂度：O(log n)
 * 空间复杂度：O(1)
 *
 *
 * 对于查找一个有序数组, 目标元素的下标位置, 首先我们记录left是起始位置,right指向数组最后一个元素位置,
 * 利用while循环只要left元素小于等于right, 求出两个元素之间的中间位置mid = right + (right - left)/2,
 * 之后比较该位置的元素和target元素的大小,如果比target指向的元素要大的话,则说明target出现在左区间,把right缩小成mid - 1;
 * 如果比target元素小的话, 说明target出现在右区间, left指向mid+1,如果相等则说明找到了,直接返回.
 * 如果遍历完数组没有找到,直接返回-1;
 * @author benjieqiang
 * @date 2023/7/2 8:19 PM
 */
public class L1_704_search {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Test
    public void testSearch() {

    }

}
