package com.ganten.tethys.top150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class N71 {
    public String simplifyPath(String path) {
        List<String> list = this.read(path.toCharArray());
        LinkedList<String> linkedList = new LinkedList<>();
        for (String s : list) {
            if (linkedList.isEmpty()) {
                if (!"/..".equals(s) && !"/.".equals(s)) linkedList.push(s);
            } else if ("/..".equals(s)) linkedList.pop();
            else if (!"/.".equals(s)) linkedList.push(s);
        }
        StringBuilder sb = new StringBuilder();
        while (!linkedList.isEmpty()) {
            sb.append(linkedList.removeLast());
        }
        String result = sb.toString();
        if (result.isEmpty()) result = "/";
        return result;

    }

    private List<String> read(char[] chars) {
        List<String> list = new ArrayList<>();
        int start = 0;
        int index = 0;
        while (index < chars.length) {
            if (index == chars.length - 1 || chars[index + 1] == '/') {
                String str = this.buildString(chars, start, index);
                if (str != null) list.add(str);
                index++;
                start = index;
            } else {
                index++;
            }
        }
        return list;
    }

    private String buildString(char[] chars, int start, int end) {
        if (start >= end) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) sb.append(chars[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
        N71 n71 = new N71();
        System.out.println(n71.simplifyPath("/."));
    }
}
