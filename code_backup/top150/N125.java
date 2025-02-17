package com.ganten.tethys.top150;

public class N125 {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toLowerCase().toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) sb.append(c);
        }
        System.out.println(sb.toString());
        char[] chars = sb.toString().toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        N125 n125 = new N125();
        System.out.println(n125.isPalindrome("race a car"));
    }
}
