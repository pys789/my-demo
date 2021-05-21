package cn.pys.leetcode;

import cn.pys.leetcode.vo.ListNode;

/**
 * @Description
 * @Date 2021/5/20 10:36
 * @Created by pengys
 */
public class Solution61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int length = 1;
        ListNode newHead = head;
        ListNode first = head;
        while (head.next != null) {
            length++;
            head = head.next;
        }
        // 构造成环
        //head.next = newHead;
        ListNode last = head;

        int move = k % length;
        if (move == 0) return first;

        // 移动到需要断开链接的前一个元素
        for (int i = 0; i < length - move - 1; i++) {
            newHead = newHead.next;
        }
        ListNode temp = newHead;
        newHead = newHead.next;
        temp.next = null;
        last.next = first;
        return newHead;
    }

    public static void main(String[] args) {
        Solution61 test = new Solution61();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        test.rotateRight(head, 2);
    }
}
