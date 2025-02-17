package com.ganten.tethys.daily.year2025.month3;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day27 {

    public static void main(String[] args) {
        Day27 day27 = new Day27();
        System.out.println(day27.minimumIndex(Stream.of(4, 2, 5, 2, 9, 2, 2, 2, 2).collect(Collectors.toList())));
    }

    public int minimumIndex(List<Integer> nums) {
        Integer maxRepeat = findMax(nums);
        if (maxRepeat == null) {
            return -1;
        }
        int[] leftCount = this.leftCount(nums, maxRepeat);
        int[] rightCount = this.rightCount(nums, maxRepeat);
        for (int i = 0; i < nums.size() - 1; i++) {
            double leftRate = 1.0 * leftCount[i] / (i + 1);
            double rightRate = 1.0 * rightCount[i + 1] / (nums.size() - i - 1);
            if (leftRate > 0.5 && rightRate > 0.5) return i;
        }
        return -1;
    }

    private Integer findMax(List<Integer> nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (Integer num : nums) {
            if (stack.isEmpty()) {
                stack.push(num);
            } else if (stack.peek().equals(num)) {
                stack.push(num);
            } else {
                stack.pop();
            }
        }
        if (stack.isEmpty()) {
            return null;
        }
        return stack.pop();
    }

    private int[] leftCount(List<Integer> nums, int maxRepeat) {
        int[] leftCount = new int[nums.size()];
        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            Integer num = nums.get(i);
            if (num == maxRepeat) {
                count++;
            }
            leftCount[i] = count;
        }
        return leftCount;
    }

    private int[] rightCount(List<Integer> nums, int maxRepeat) {
        int[] rightCount = new int[nums.size()];
        int count = 0;
        for (int i = nums.size() - 1; i >= 0; i--) {
            Integer num = nums.get(i);
            if (num == maxRepeat) {
                count++;
            }
            rightCount[i] = count;
        }
        return rightCount;
    }
}
