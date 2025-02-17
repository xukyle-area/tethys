package com.ganten.tethys.top150;

public class N66 {
    public int[] plusOne(int[] digits) {
        boolean flag = true;
        for (int i = digits.length - 1; i >= 0; i--) {
            int result = digits[i] + (flag ? 1 : 0);
            flag = result >= 10;
            result %= 10;
            digits[i] = result;
        }
        if (flag) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            System.arraycopy(digits, 0, result, 1, digits.length);
            return result;
        }
        return digits;
    }
}
