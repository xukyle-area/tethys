package com.ganten.tethys.daily.year2025.month2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day17 {

    private final Set<String> result = new HashSet<>();

    public int numTilePossibilities(String tiles) {
        // 使用 HashMap 统计每个字母的出现次数
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : tiles.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }

        backtrack(counter, "");
        return result.size();
    }

    // 回溯方法，生成所有的排列
    private void backtrack(Map<Character, Integer> counter, String path) {
        // 每次都有一个新的排列生成，将其加入结果集
        if (!path.isEmpty()) {
            result.add(path);
        }

        // 尝试每一个字母
        for (char c : counter.keySet()) {
            if (counter.get(c) > 0) {
                // 使用当前字母，递归下去
                counter.put(c, counter.get(c) - 1);
                backtrack(counter, path + c);
                counter.put(c, counter.get(c) + 1);  // 回溯，恢复字母计数
            }
        }
    }

    public static void main(String[] args) {
        Day17 day17 = new Day17();
        System.out.println(day17.numTilePossibilities("AAB"));
    }
}
