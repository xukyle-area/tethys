package com.ganten.tethys.top150;

import java.util.LinkedList;

public class N20 {
    public static void main(String[] args) {
        N20 n20 = new N20();
        System.out.println(n20.isValid("([])"));
    }

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        LinkedList<Character> linkedList = new LinkedList<>();
        for (char c : chars) {
            if (c == '{' || c == '[' || c == '(') {
                linkedList.push(c);
            } else {
                Character peek = linkedList.peek();
                if (peek == null || Math.abs(c - peek) > 2) return false;
                else linkedList.pop();
            }
        }
        return linkedList.isEmpty();
    }
}
