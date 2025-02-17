package com.ganten.tethys.leetcode75;

import com.ganten.tethys.ListNode;

public class N328 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head; // 空链表或只有一个节点，直接返回

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

}
