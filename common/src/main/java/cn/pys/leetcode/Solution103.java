package cn.pys.leetcode;

import cn.pys.leetcode.vo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Date 2021/4/21 17:06
 * @Created by pengys
 */
public class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level;
        boolean change = false;
        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            level = queue.size();
            while (level > 0) {
                TreeNode t = queue.poll();
                if (t == null) break;
                if (change) {
                    tmp.addFirst(t.val);
                } else {
                    tmp.addLast(t.val);
                }
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
                level--;
            }
            list.add(tmp);
            change = !change;
        }
        return list;
    }
}
