import java.util.LinkedList;

class Solution {
    public void reorderList(ListNode head) {
        LinkedList<ListNode> linkedList = new LinkedList<ListNode>();
        ListNode iter = head;
        for (; iter != null; iter = iter.next) {
            linkedList.addLast(iter);
        }
        ListNode dummy = new ListNode();
        iter = dummy;
        for (boolean flag = true; !linkedList.isEmpty(); iter = iter.next, flag = !flag) {
            iter.next = flag ? linkedList.removeFirst() : linkedList.removeLast();
        }
        iter.next = null;
    }
}
