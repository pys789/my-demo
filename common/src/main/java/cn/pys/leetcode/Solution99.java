package cn.pys.leetcode;

import ch.qos.logback.core.joran.conditional.ElseAction;
import cn.pys.leetcode.vo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Date 2021/4/20 14:49
 * @Created by pengys
 */
public class Solution99 {
    TreeNode t1, t2, pre;



    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        } else {
            if (p.val == q.val) {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            } else {
                return false;
            }
        }

    }

    public void recoverTree(TreeNode root) {
        inorder(root);
        int tmp = t2.val;
        t2.val = t1.val;
        t1.val = tmp;
    }

    /**
     * 1 3 2 4 5 6  此时只存在一个前面的大于后面的(3>2)，交换 3 2
     * 1 5 3 4 2 6  此时只存在两个前面的大于后面的(5>3,4>2)，交换5 2
     * 上下两种情况,，交互的第一个元素在第一个值
     */
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (pre != null && pre.val > root.val) {
            if (t1 == null) {
                t1 = pre;
            }
            t2 = root;
        }
        pre = root;
        inorder(root.right);
    }


}
