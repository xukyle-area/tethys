package com.ganten.tethys.daily.year2025.month3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day22 {
    public static void main(String[] args) {
        Day22 day22 = new Day22();
        int[][] edges = new int[][]{{2, 0}, {2, 1}};
        System.out.println(day22.countCompleteComponents(3, edges));
    }

    private boolean[] used;
    private Map<Integer, Set<int[]>> mapSet;
    private final Map<Integer, Integer> resultMap = new HashMap<>();

    public int countCompleteComponents(int n, int[][] edges) {
        this.used = new boolean[n];
        int[][] newEdges = new int[edges.length * 2][2];

        // 正反都连通
        for (int i = 0; i < newEdges.length; i++) {
            if (i < edges.length) {
                newEdges[i][0] = edges[i][0];
                newEdges[i][1] = edges[i][1];
            } else {
                newEdges[i][0] = edges[i - edges.length][1];
                newEdges[i][1] = edges[i - edges.length][0];
            }
        }

        this.mapSet = Stream.of(newEdges).collect(
                Collectors.groupingBy(o -> o[0], Collectors.mapping(o -> o, Collectors.toSet()))
        );

        for (int i = 0; i < n; i++) this.helper(i, i);

        Map<Integer, Set<Integer>> componentMap = resultMap.entrySet().stream().collect(
                Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toSet()))
        );

        int count = 0;
        for (Set<Integer> component : componentMap.values()) {
            if (isComplete(component)) {
                count++;
            }
        }
        return count;
    }

    private void helper(int index, int start) {
        if (used[index]) {
            return;
        }
        used[index] = true;
        resultMap.put(index, start);
        Set<int[]> set = mapSet.get(index);
        if (Objects.nonNull(set)) {
            for (int[] edge : set) {
                this.helper(edge[1], start);
            }
        }
    }

    private boolean isComplete(Set<Integer> component) {
        int size = component.size();
        int expectedEdges = size * (size - 1);
        int actualEdges = 0;

        for (int node : component) {
            Set<int[]> neighbors = mapSet.getOrDefault(node, new HashSet<>());
            for (int[] edge : neighbors) {
                if (component.contains(edge[1])) {
                    actualEdges++;
                }
            }
        }

        return actualEdges == expectedEdges;
    }
}
