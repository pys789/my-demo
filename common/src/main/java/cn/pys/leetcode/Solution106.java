package cn.pys.leetcode;

import cn.pys.leetcode.vo.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2021/4/21 15:50
 * @Created by pengys
 */
public class Solution106 {
    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTreeHelper(postorder, 0, postorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTreeHelper(int[] postorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pStart == pEnd) return null;
        TreeNode root = new TreeNode(postorder[pEnd - 1]);
        int inorderIdx = indexMap.get(postorder[pEnd - 1]);
        int sub = inorderIdx - iStart;
        root.left = buildTreeHelper(postorder, pStart, pStart + sub, inorder, iStart, inorderIdx);
        root.right = buildTreeHelper(postorder, pStart + sub, pEnd - 1, inorder, inorderIdx + 1, iEnd);
        return root;
    }
}
