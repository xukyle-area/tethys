package com.ganten.tethys.top150;

import com.ganten.tethys.ListNode;

public class N92 {

    public static void main(String[] args) {
        N92 n92 = new N92();
        n92.reverseBetween(ListNode.build(new int[]{1, 2, 3, 4, 5}), 2, 4);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        for (int i = left; i < right; i++) {
            ListNode nextNode = cur.next;
            cur.next = nextNode.next;
            nextNode.next = pre.next;
            pre.next = nextNode;
        }
        return dummy.next;
    }
}
