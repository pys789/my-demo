package cn.pys.leetcode;

import cn.pys.leetcode.vo.TreeNode;

/**
 * @Description
 * @Date 2021/4/12 17:52
 * @Created by pengys
 */
public class Solution98 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return false;
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val >= upper || node.val <= lower) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

}


