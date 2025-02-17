package com.ganten.tethys.top150;

import com.ganten.tethys.ListNode;

public class N82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode iterator = head;
        ListNode prevNode = null;
        while (iterator != null && iterator.next != null) {
            if (iterator.val != iterator.next.val) {
                prevNode = iterator;
                iterator = iterator.next;
            } else {
                int duplicateNum = iterator.val;
                ListNode skip = iterator;
                while (skip != null && skip.val == duplicateNum) skip = skip.next;
                if (prevNode == null) head = skip;
                else prevNode.next = skip;
                iterator = skip;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        N82 n19 = new N82();
        ListNode listNode = n19.deleteDuplicates(ListNode.build(new int[]{1, 1, 1, 2, 3}));
        listNode.print();
    }
}
