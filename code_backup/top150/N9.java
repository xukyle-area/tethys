package com.ganten.tethys.top150;

import java.util.LinkedList;

public class N9 {
    public static void main(String[] args) {
        N9 n9 = new N9();
        n9.isPalindrome(10);
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        LinkedList<Integer> linkedList = new LinkedList<>();
        int m = x;
        while (m > 0) {
            int i = m % 10;
            System.out.println(i);
            linkedList.addLast(i);
            m = m / 10;
        }
        while (linkedList.size() > 1) {
            Integer last = linkedList.removeLast();
            Integer first = linkedList.removeFirst();
            if (!first.equals(last)) {
                return false;
            }
        }
        return true;
    }
}
