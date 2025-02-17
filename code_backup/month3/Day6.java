package com.ganten.tethys.daily.year2025.month3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 {


    /**
     * 找到缺失值和重复值
     *
     * @param grid 二维数组
     * @return int[] {重复值, 缺失值}
     */
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int size = grid.length;
        int length = size * size;
        int exceptSum = (1 + length) * length / 2; // 理论总和

        List<Integer> nums = this.toList(grid);
        int duplicate = this.findDuplicate(nums);
        int realSum = nums.stream().mapToInt(Integer::intValue).sum(); // 真实总和

        // 计算缺失的数字
        int missing = exceptSum - (realSum - duplicate);

        return new int[]{duplicate, missing};
    }

    /**
     * 将二维数组转换为一维列表
     *
     * @param grid 二维数组
     * @return List<Integer>
     */
    public List<Integer> toList(int[][] grid) {
        List<Integer> nums = new ArrayList<>();
        for (int[] row : grid) {
            for (int num : row) {
                nums.add(num);
            }
        }
        return nums;
    }

    /**
     * 查找重复的数字
     *
     * @param nums 一维列表
     * @return int 重复的数字
     */
    public int findDuplicate(List<Integer> nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num; // 找到重复的数字
            }
            seen.add(num);
        }
        return -1; // 没有找到
    }


    public static void main(String[] args) {
        Day6 day6 = new Day6();
    }
}
