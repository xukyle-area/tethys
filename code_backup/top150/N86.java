package com.ganten.tethys.top150;

import com.ganten.tethys.ListNode;

import java.util.Objects;

public class N86 {
    public ListNode partition(ListNode head, int x) {
        ListNode firstNode = new ListNode();
        ListNode firstIterator = firstNode;
        ListNode secondNode = new ListNode();
        ListNode secondIterator = secondNode;

        ListNode iterator = head;
        while (Objects.nonNull(iterator)) {
            ListNode next = iterator.next;
            iterator.next = null;
            if (iterator.val < x) {
                firstIterator.next = iterator;
                firstIterator = firstIterator.next;
            } else {
                secondIterator.next = iterator;
                secondIterator = secondIterator.next;
            }
            iterator = next;
        }

        firstIterator.next = secondNode.next;
        return firstNode.next;
    }
}
