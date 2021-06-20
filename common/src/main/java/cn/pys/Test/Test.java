package cn.pys.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Date 2021/1/7 15:48
 * @Created by pengys
 */
public class Test {


    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null) return null;
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length == 0) return list;
        dfs(list, nums, 0);
        return list;
    }

    private void dfs(List<List<Integer>> list, int[] nums, int idx) {
        if (idx == nums.length) {
            List<Integer> result = new ArrayList<>();
            for (int num : nums) {
                result.add(num);
            }
            list.add(result);
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            if (isRepeat(nums, idx, i)) continue;
            swap(nums, idx, i);
            dfs(list, nums, idx + 1);
            swap(nums, idx, i);
        }

    }

    private boolean isRepeat(int[] nums, int idx, int i) {
        for (int j = idx; j < i; j++) {
            if (nums[j] == nums[i]) return true;
        }
        return false;
    }

    private void swap(int[] nums, int idx, int i) {
        if (idx == i) return;
        int temp = nums[idx];
        nums[idx] = nums[i];
        nums[i] = temp;
    }


    public static void main(String[] args) throws InterruptedException {
        Test t = new Test();
        System.out.println(t.permuteUnique(new int[]{2, 2, 1, 1}));
    }

}
