package com.ganten.tethys.top150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class N46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        this.backtracking(nums, new boolean[nums.length], new LinkedList<>(), result);
        return result;
    }

    private void backtracking(int[] nums, boolean[] used, LinkedList<Integer> tempResult, List<List<Integer>> result) {
        if (tempResult.size() == nums.length) {
            result.add(new ArrayList<>(tempResult));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            used[i] = true;
            tempResult.addLast(nums[i]);
            this.backtracking(nums, used, tempResult, result);
            tempResult.removeLast();
            used[i] = false;
        }
    }


    public static void main(String[] args) {
        N46 n46 = new N46();
        System.out.println(n46.permute(new int[]{1, 2, 3}));
    }
}

