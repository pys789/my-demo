package cn.pys.leetcode;

import cn.pys.leetcode.vo.ListNode;

/**
 * @Description
 * @Date 2021/5/20 16:59
 * @Created by pengys
 */
public class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;

        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummyHead.next;
                }
            }
            reverseNode(head, tail);
            pre.next = tail;
            pre = head;
            head = head.next;
        }
        return dummyHead.next;
    }

    public void reverseNode(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
    }

    public static void main(String[] args) {
        Solution25 test = new Solution25();
        ListNode head = new ListNode(1);
        ListNode tmp = head;

        tmp = tmp.next = new ListNode(2);
        tmp = tmp.next = new ListNode(3);
        tmp = tmp.next = new ListNode(4);
        tmp = tmp.next = new ListNode(5);

        test.reverseKGroup(head, 4);
    }
}
