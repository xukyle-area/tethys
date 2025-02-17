package com.ganten.tethys.top150;

public class N58 {

    public int lengthOfLastWord(String s) {
        s = "  " + s;
        char[] chars = s.toCharArray();
        int cur = chars.length - 1;
        while (chars[cur] == ' ') {
            cur--;
        }
        int endIndex = cur;
        while (chars[cur] != ' ') {
            cur--;
        }
        int startIndex = cur;
        return endIndex - startIndex;
    }

    public static void main(String[] args) {
        N58 n58 = new N58();

        System.out.println(n58.lengthOfLastWord("a"));
        System.out.println(n58.lengthOfLastWord("01234 6789"));
    }
}
