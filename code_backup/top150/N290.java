package com.ganten.tethys.top150;

import java.util.HashMap;

public class N290 {
    public boolean wordPattern(String pattern, String s) {
        String[] split = s.split(" ");
        HashMap<Character, String> map = new HashMap<>();
        char[] chars = pattern.toCharArray();
        if (split.length != chars.length) return false;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            String sss = split[i];
            if (map.containsKey(c)) {
                String s1 = map.get(c);
                if (!s1.equals(sss)) {
                    return false;
                }
            } else {
                if (map.containsValue(sss)) {
                    return false;
                }
                map.put(c, sss);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        N290 n290 = new N290();
        boolean b = n290.wordPattern("abba", "dog cat cat dog");
        System.out.println(b);
    }
}
