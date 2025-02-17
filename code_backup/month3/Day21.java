package com.ganten.tethys.daily.year2025.month3;

import java.util.*;

public class Day21 {

    public static void main(String[] args) {
        Day21 day21 = new Day21();
    }

    private final HashMap<String, Boolean> recipesResult = new HashMap<>();
    private final HashMap<String, Integer> recipeMap = new HashMap<>();
    private final Set<String> suppliesSet = new HashSet<>();
    private List<List<String>> needsList;

    /**
     * @param recipes   菜名
     * @param needsList 食材
     * @param supplies  供应
     * @return 可以提供的菜
     * @link <a href="https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/description"/>
     */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> needsList, String[] supplies) {
        suppliesSet.addAll(Arrays.asList(supplies));
        this.needsList = needsList;
        for (int i = 0; i < recipes.length; i++) {
            recipeMap.put(recipes[i], i);
        }
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < recipes.length; i++) {
            this.helper(recipes[i], i, visited);
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : recipesResult.entrySet()) {
            if (entry.getValue()) result.add(entry.getKey());
        }
        return result;
    }

    public void helper(String recipe, int recipeIndex, Set<String> visited) {
        if (recipesResult.containsKey(recipe)) {
            return; // 如果已经计算过，直接返回
        }
        if (visited.contains(recipe)) {
            recipesResult.put(recipe, false); // 发现环，直接返回 false
            return;
        }
        visited.add(recipe);

        boolean match = true;
        List<String> needs = needsList.get(recipeIndex);

        for (String need : needs) {
            if (recipeMap.containsKey(need)) {
                helper(need, recipeMap.get(need), visited);
                if (!recipesResult.get(need)) {
                    match = false;
                    break;
                }
            } else if (!suppliesSet.contains(need)) {
                match = false;
                break;
            }
        }
        recipesResult.put(recipe, match);
        visited.remove(recipe); // 递归返回时移除
    }
}
