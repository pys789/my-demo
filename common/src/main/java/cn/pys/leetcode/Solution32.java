package cn.pys.leetcode;

/**
 * @Description
 * @Date 2021/5/11 17:37
 * @Created by pengys
 */
public class Solution32 {
    public int longestValidParentheses(String s) {
        int max = 0;
        int[] dp = new int[s.length()];
        char[] cs = s.toCharArray();
        for (int i = 1; i < cs.length; i++) {
            if (cs[i] == ')') {
                if (cs[i - 1] == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && cs[i - dp[i - 1] - 1] == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] > 2 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution32 test = new Solution32();
        test.longestValidParentheses("(())(");
    }
}
