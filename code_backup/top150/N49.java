package com.ganten.tethys.top150;

import java.util.*;
import java.util.stream.Collectors;

public class N49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> listMap = Arrays.stream(strs).collect(Collectors.groupingBy(
                this::sortString, Collectors.mapping(o -> o, Collectors.toList())
        ));
        return new ArrayList<>(listMap.values());
    }

    public String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}
