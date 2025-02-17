package com.ganten.tethys.top150;

import com.ganten.tethys.ListNode;

public class N61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        ListNode iterator = head;
        int size = 1;
        while (iterator.next != null) {
            size++;
            iterator = iterator.next;
        }
        ListNode end = iterator;
        end.next = head;
        int count = 1;
        iterator = head;
        while (count < size - k % size) {
            iterator = iterator.next;
            count++;
        }
        ListNode result = iterator.next;
        iterator.next = null;
        return result;
    }
}
