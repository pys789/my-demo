package cn.pys.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description
 * @Date 2021/5/16 15:44
 * @Created by pengys
 */
public class Solution56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            if (i == 0 || res.get(res.size() - 1)[1] < start) {
                res.add(new int[]{start, end});
            } else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], end);
            }
        }

        return res.toArray(new int[res.size()][2]);

    }

    public static void main(String[] args) {
        // intervals = [[l,3],[2,6],[8,10],[15,18]]
        Solution56 test = new Solution56();
        int[][] arr = new int[][]{{15, 18}, {1, 3}, {2, 6}, {8, 10}};
        int[][] merge = test.merge(arr);
        System.out.println(merge);

    }
}
