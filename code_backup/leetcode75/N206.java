package com.ganten.tethys.leetcode75;

import com.ganten.tethys.ListNode;

public class N206 {
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return cur;
    }
}
