package com.ganten.tethys.top150;

public class N14 {
    public String longestCommonPrefix(String[] strs) {
        String base = strs[0];
        for (int n = base.length(); n > 0; n--) {
            boolean isCommon = true;
            for (int j = 1; j < strs.length; j++) {
                if (!matchFirstN(base, strs[j], n)) {
                    isCommon = false;
                    break;
                }
            }
            if (isCommon) {
                return base.substring(0, n);
            }
        }
        return "";
    }

    public boolean matchFirstN(String s1, String s2, int n) {
        for (int i = 0; i < n; i++) {
            if (s1.length() < n || s2.length() < n || s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        N14 n14 = new N14();
        System.out.println(n14.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }
}
