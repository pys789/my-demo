package cn.pys.leetcode;

import cn.pys.leetcode.vo.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2021/4/20 17:53
 * @Created by pengys
 */
public class Solution105 {
    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTreeHelper(int[] preOrder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pStart == pEnd) return null;
        TreeNode root = new TreeNode(preOrder[pStart]);
        int inorderIdx = indexMap.get(preOrder[pStart]);
        int sub = inorderIdx - iStart;
        root.left = buildTreeHelper(preOrder, pStart + 1, pStart + sub + 1, inorder, iStart, inorderIdx);
        root.right = buildTreeHelper(preOrder, pStart + sub + 1, pEnd, inorder, inorderIdx + 1, iEnd);
        return root;
    }

}
