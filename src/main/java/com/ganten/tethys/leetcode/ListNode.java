package com.ganten.tethys.leetcode;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode build(int[] nums) {
        ListNode head = new ListNode(nums[0]);
        ListNode iter = head;
        for (int i = 1; i < nums.length; i++) {
            iter.next = new ListNode(nums[i]);
            iter = iter.next;
        }
        return head;
    }

    public void print() {
        ListNode iterator = this;
        StringBuilder sb = new StringBuilder();
        while (iterator != null) {
            sb.append("[").append(iterator.val).append("]");
            iterator = iterator.next;
        }
        System.out.println(sb);
    }
}
