package cn.pys.leetcode;

import cn.pys.leetcode.vo.ListNode;

/**
 * @Description
 * @Date 2021/5/19 14:51
 * @Created by pengys
 */
public class Solution24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode n1 = temp.next;
            ListNode n2 = temp.next.next;
            temp.next = n2;
            n1.next = n2.next;
            n2.next = n1;
            temp = n1;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        Solution24 test = new Solution24();
        ListNode head = new ListNode(1);
        ListNode temp = head;
        temp = temp.next = new ListNode(2);
        temp = temp.next = new ListNode(3);
        temp = temp.next = new ListNode(4);
        test.swapPairs(head);
    }
}
