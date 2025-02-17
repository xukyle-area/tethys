package com.ganten.tethys.top150;

import java.util.HashMap;
import java.util.Map;

public class N205 {
    public static void main(String[] args) {
        N205 n205 = new N205();
        System.out.println(n205.isIsomorphic("badc", "baba"));
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        int i = 0;
        while (i < s.length()) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (map.containsKey(sc)) {
                if (map.get(sc) != tc) {
                    return false;
                }
            } else {
                if (map.containsValue(tc)) {
                    return false;
                }
                map.put(sc, tc);
            }
            i++;
        }
        return true;
    }
}
