package cn.pys.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Date 2021/5/8 9:18
 * @Created by pengys
 */
public class Solution17 {

    private Map<Character, char[]> phone = new HashMap<Character, char[]>() {{
        put('2', "abc".toCharArray());
        put('3', "def".toCharArray());
        put('4', "ghi".toCharArray());
        put('5', "jkl".toCharArray());
        put('6', "mno".toCharArray());
        put('7', "pqrs".toCharArray());
        put('8', "tuv".toCharArray());
        put('9', "wxyz".toCharArray());
    }};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        // 深度与可以选用的字符数组
        Map<Integer, char[]> map = new HashMap<>();
        for (int i = 0; i < digits.length(); i++) {
            map.put(i, phone.get(digits.charAt(i)));
        }
        dfs(map, result, new StringBuilder(), 0);
        return result;
    }

    private void dfs(Map<Integer, char[]> map, List<String> result, StringBuilder sb, int depth) {
        if (depth == map.size()) {
            result.add(sb.toString());
            return;
        }
        char[] chars = map.get(depth);
        for (char ch : chars) {
            sb.append(ch);
            dfs(map, result, sb, depth + 1);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    public static void main(String[] args) {
        Solution17 test = new Solution17();
        System.out.println(test.letterCombinations("234"));
    }
}
