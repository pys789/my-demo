package cn.pys.leetcode;

/**
 * @Description
 * @Date 2021/5/11 16:01
 * @Created by pengys
 */
public class Solution31 {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int firstIdx = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstIdx = i;
                break;
            }
        }
        // 找不到前一个比后一个小的数字，表示当前是最大数字，饭转即可
        if (firstIdx == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int secondIdx = -1;
        for (int i = nums.length - 1; i > firstIdx; i--) {
            if (nums[i] > nums[firstIdx]) {
                secondIdx = i;
                break;
            }
        }
        if (secondIdx != -1) {
            swap(nums, firstIdx, secondIdx);
        }
        // firstIdx之后的数字是递减的数字，翻转之后变成最小数字
        reverse(nums, firstIdx + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        Solution31 test = new Solution31();
        int[] arr = new int[]{1, 6, 3, 4, 7, 5, 2};
        test.nextPermutation(arr);
        for (int a : arr) {
            System.out.print(a + ",");
        }
    }
}
