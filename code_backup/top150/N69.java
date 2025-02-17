package com.ganten.tethys.top150;

public class N69 {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        long left = 1;
        long right = Integer.MAX_VALUE;
        while (true) {
            if (right - left <= 1) return (int) left;
            long mid = (left + right) / 2;
            long power = mid * mid;
            if (power > x) {
                right = mid;
            } else if (power < x) {
                left = mid;
            } else {
                return (int) mid;
            }
        }
    }

    public static void main(String[] args) {
        N69 n69 = new N69();
        System.out.println(n69.mySqrt(Integer.MAX_VALUE));
    }
}
