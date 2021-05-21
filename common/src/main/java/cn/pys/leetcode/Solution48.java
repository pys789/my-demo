package cn.pys.leetcode;

/**
 * @Description
 * @Date 2021/5/18 17:19
 * @Created by pengys
 */
public class Solution48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int endIdx = n - 1;
        for (int i = 0; i < n / 2; i++) {
            // matrix[col][endIdx−row]=matrix[row][col]
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[endIdx - j][i];
                matrix[endIdx - j][i] = matrix[endIdx - i][endIdx - j];
                matrix[endIdx - i][endIdx - j] = matrix[j][endIdx - i];
                matrix[j][endIdx - i] = temp;
            }

        }


    }
}
