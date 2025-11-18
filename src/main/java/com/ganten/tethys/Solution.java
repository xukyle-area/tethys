package com.ganten.tethys;

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] resultArray = new int[nums.length];
        int count = this.countOfZero(nums);
        int multiply = 1;
        for (int n : nums) {
            if (n != 0)
                multiply *= n;
        }
        if (count == 1) {
            int index = this.indexOfZero(nums);
            resultArray[index] = multiply;
        } else if (count == 0) {
            for (int i = 0; i < nums.length; i++) {
                resultArray[i] = multiply / nums[i];
            }
        }
        return resultArray;
    }

    public int countOfZero(int[] nums) {
        int result = 0;
        for (int num : nums) {
            if (num == 0) {
                result++;
            }
        }
        return result;
    }

    public int indexOfZero(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[] {1, 2, 3, 4};
        int[] mm = solution.productExceptSelf(array);
        System.out.println(mm);
    }
}
