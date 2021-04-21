package cn.pys.leetcode;

import cn.pys.leetcode.vo.TreeNode;

import java.util.*;

/**
 * @Description
 * @Date 2021/4/21 16:32
 * @Created by pengys
 */
public class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level;
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            level = queue.size();
            while (level > 0) {
                TreeNode t = queue.poll();
                if (t == null) break;
                tmp.add(t.val);
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
                level--;
            }
            list.add(tmp);
        }
        return list;
    }
}
