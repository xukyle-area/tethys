package com.ganten.tethys.top150;

/**
 * TODO 433
 */
public class N433 {
    int ans = Integer.MAX_VALUE;
    private boolean[] used;

    public int minMutation(String start, String end, String[] bank) {
        used = new boolean[bank.length];
        this.helper(start, end, bank, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void helper(String start, String end, String[] bank, int t) {
        if (t >= ans) {
            return;
        }
        if (start.equals(end)) {
            ans = t;
            return;
        }
        for (int i = 0; i < bank.length; i++) {
            if (used[i] || this.count(start, bank[i]) != 1) continue;
            used[i] = true;
            this.helper(bank[i], end, bank, t + 1);
            used[i] = false;
        }
    }

    private int count(String start, String bank) {
        int count = 0;
        for (int i = 0; i < start.length(); i++) {
            count += start.charAt(i) != bank.charAt(i) ? 1 : 0;
        }
        return count;
    }
}
