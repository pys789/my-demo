package cn.pys.leetcode;

import java.util.List;

/**
 * @Description
 * @Date 2021/5/12 9:47
 * @Created by pengys
 */
public class Solution34 {
    int end = 0;

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int leftIdx = leftBound(nums, target);
        int rightIdx = rightBound(nums, target, leftIdx, end > 0 ? end : nums.length);
        if (leftIdx > rightIdx) {
            return new int[]{-1, -1};
        } else {
            return new int[]{leftIdx, rightIdx};
        }
    }

    private int leftBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
                end = mid;
            }
        }
        return left;
    }

    private int rightBound(int[] nums, int target, int start, int end) {
        int left = start, right = end;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left - 1;
    }

    public static void main(String[] args) {
        Solution34 test = new Solution34();
        int[] ints = test.searchRange(new int[]{5,7,7,8,8,10}, 8);
        for (int i : ints) {
            System.out.print(i + ",");
        }
    }


}
