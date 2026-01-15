class Solution {
    public int shipWithinDays(int[] w, int days) {
        int n = w.length;

        int start = Integer.MIN_VALUE;
        int end = 0;
        for (int i = 0; i < n; i++) {
            end += w[i];
            if (w[i] > start) {
                start = w[i];
            }
        }

        int leastcapacity = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int sum = 0;
            int d = 0;
            for (int i = 0; i < n; i++) {
                sum = sum + w[i];
                if (sum > mid) {
                    d++;
                    --i;
                    sum = 0;
                }
            }
            if (d >= days) {
                start = mid + 1;
            } else {
                leastcapacity = Math.min(mid, leastcapacity);
                end = mid - 1;
            }
        }
        return leastcapacity;
    }
}
