package com.ganten.tethys.top150;

import java.util.HashMap;
import java.util.Map;

public class N6 {

    public static void main(String[] args) {
        // 1.   m * (2k-2)
        // n.   m * (2k-2) + n
        // k.   m * (2k-2) + k - 1
        N6 n6 = new N6();
        System.out.println(n6.convert("AB", 2));
    }


    public String convert0(String s, int k) {
        if (k == 1)
            return s;

        StringBuilder[] rows = new StringBuilder[k];
        for (int i = 0; i < k; i++) {
            rows[i] = new StringBuilder();
        }

        int m = 0;
        boolean isDown = true;

        for (char c : s.toCharArray()) {
            rows[m].append(c);
            if (isDown) {
                m++;
                if (m == k) {
                    m = k - 2;
                    isDown = false;
                }
            } else {
                m--;
                if (m == -1) {
                    m = 1;
                    isDown = true;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    public String convert(String s, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        String[] stringArray = new String[k];
        if (k == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        boolean isDown = true;
        int m = 0;
        for (int i = 0; i < chars.length; i++) {
            map.put(i, m);
            if (isDown) {
                m++;
                if (m == k) {
                    m = k - 2;
                    isDown = false;
                }
            } else {
                m--;
                if (m == -1) {
                    m = 1;
                    isDown = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            for (int index = 0; index < chars.length; index++) {
                if (map.get(index).equals(i)) {
                    sb.append(chars[index]);
                }
            }
        }
        return sb.toString();
    }
}
