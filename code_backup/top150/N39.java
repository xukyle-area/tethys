package com.ganten.tethys.top150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class N39 {

    public static void main(String[] args) {
        N39 n39 = new N39();
        System.out.println(n39.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        this.backtracking(nums, 0, target, new LinkedList<>(), result);
        return result;
    }

    private void backtracking(int[] nums, int index, int target, LinkedList<Integer> tempResult, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(tempResult));
            return;
        } else if (target < 0) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            tempResult.addLast(nums[i]);
            this.backtracking(nums, i, target - nums[i], tempResult, result);
            tempResult.removeLast();
        }
    }
}
