package com.ganten.tethys.top150;

import java.util.HashSet;
import java.util.Set;

public class N202 {

    public static void main(String[] args) {
        N202 n202 = new N202();
        System.out.println(n202.isHappy(19));
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
            n = this.calculate(n);
        }
        return true;
    }

    public int calculate(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n = n / 10;
        }
        return sum;
    }
}
