class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int xSum = (0 + nums.length) * (nums.length + 1) / 2;
        return xSum == sum ? 0 : xSum - sum;
    }
}
