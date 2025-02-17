package com.ganten.tethys.top150;

import com.ganten.tethys.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class N19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        int index = 0;
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode iterator = head;
        while (Objects.nonNull(iterator)) {
            map.put(index, iterator);
            index++;
            iterator = iterator.next;
        }
        int deleteIndex = index - n;
        if (deleteIndex == 0) return head.next;
        ListNode prev = map.get(deleteIndex - 1);
        prev.next = map.get(deleteIndex + 1);
        return head;
    }

    public static void main(String[] args) {
        N19 n19 = new N19();
        n19.removeNthFromEnd(ListNode.build(new int[]{1, 2, 3, 4, 5}), 2);
    }
}
