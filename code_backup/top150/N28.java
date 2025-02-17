package com.ganten.tethys.top150;

public class N28 {
    public int strStr(String haystack, String needle) {
        char[] haystackArray = haystack.toCharArray();
        char[] needleArray = needle.toCharArray();
        for (int i = 0; i <= haystackArray.length - needleArray.length; i++) {
            int innerNeedleIndex = 0;
            int innerHaystackIndex = i;
            while (innerNeedleIndex < needleArray.length && needleArray[innerNeedleIndex] == haystackArray[innerHaystackIndex]) {
                innerNeedleIndex++;
                innerHaystackIndex++;
            }
            if (innerNeedleIndex == needleArray.length) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        N28 n28 = new N28();
        System.out.println(n28.strStr("a", "a"));
    }
}
