package cn.pys.leetcode;

/**
 * @Description
 * @Date 2020/12/21 10:11
 * @Created by pengys
 */
public class Solution5 {

    public static void main(String[] args) {
        Solution5 test = new Solution5();
        System.out.println(test.longestPalindrome("bababccad"));
    }

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int right = 0;
        int left = 0;

        boolean[] dp = new boolean[len];
        char[] charArray = s.toCharArray();

        boolean leftTop;
        for (int j = 1; j < len; j++) {
            leftTop = dp[j - 1];
            for (int i = 0; i <= j; i++) {
                if (charArray[i] == charArray[j]) {
                    if (j - i < 3) {
                        dp[j] = true;
                    } else {
                        dp[j] = leftTop;
                    }
                } else {
                    dp[j] = false;
                }
                if (dp[j] && j - i + 1 > right - left) {
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }
}
