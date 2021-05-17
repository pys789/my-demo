package cn.pys.leetcode;

/**
 * @Description
 * @Date 2021/5/14 11:11
 * @Created by pengys
 */
public class Solution72 {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        // 第一行
        for (int j = 1; j <= n2; j++) dp[0][j] = dp[0][j - 1] + 1;
        // 第一列
        for (int i = 1; i <= n1; i++) dp[i][0] = dp[i - 1][0] + 1;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 相当于remove
                    int top = dp[i - 1][j];
                    // 相当于add
                    int left = dp[i][j - 1];
                    // 相当于替换
                    int top_left = dp[i - 1][j - 1];
                    dp[i][j] = Math.min(Math.min(top, left), top_left) + 1;
                }
            }
        }
        return dp[n1][n2];
    }

    public static void main(String[] args) {
        Solution72 test = new Solution72();
        System.out.println(test.minDistance("horse", "ros"));
    }
}
