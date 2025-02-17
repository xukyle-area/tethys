package com.ganten.tethys.top150;

public class N50 {
    public double myPow(double x, int n) {
        return n < 0 ? (1 / subPow(x, -n)) : subPow(x, n);
    }

    public double subPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;

        double v = subPow(x, n / 2);
        return v * v * (n % 2 == 0 ? 1 : x);
    }

    public static void main(String[] args) {
        N50 n50 = new N50();
        System.out.println(n50.myPow(2.0d, -2147483648));
    }
}
