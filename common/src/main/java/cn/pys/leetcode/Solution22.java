package cn.pys.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Date 2021/5/10 17:27
 * @Created by pengys
 */
public class Solution22 {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) return null;
        List<String> res = new ArrayList<>();
        dfs(res, 0, new char[n << 1], n, n);
        return res;
    }

    private void dfs(List<String> res, int depth, char[] cs, int leftRemain, int rightRemain) {
        if (depth == cs.length) {
            res.add(new String(cs));
            return;
        }
        if (leftRemain > 0) {
            cs[depth] = '(';
            dfs(res, depth + 1, cs, leftRemain - 1, rightRemain);
        }
        if (rightRemain > 0 && leftRemain != rightRemain) {
            cs[depth] = ')';
            dfs(res, depth + 1, cs, leftRemain, rightRemain - 1);
        }

    }

    public static void main(String[] args) {
        Solution22 test = new Solution22();
        System.out.println(test.generateParenthesis(3));
    }

}
