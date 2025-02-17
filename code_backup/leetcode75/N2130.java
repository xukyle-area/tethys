package com.ganten.tethys.leetcode75;

import com.ganten.tethys.ListNode;

public class N2130 {
    public int pairSum(ListNode head) {
        ListNode vHead = new ListNode();
        vHead.next = head;
        ListNode slow = vHead;
        ListNode fast = vHead;
        int size = 0;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            size++;
        }
        ListNode cur = vHead;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            ListNode slowNext = slow.next;
            ListNode startNext = cur.next;
            result[i] += startNext.val;
            result[size - 1 - i] += slowNext.val;
            slow = slow.next;
            cur = cur.next;
        }
        int max = Integer.MIN_VALUE;
        for (int i : result) {
            max = Math.max(max, i);
        }
        return max;
    }
}
