package com.ganten.tethys.daily.year2025.month4;

// 1922
public class Day13 {
    // 0 1 2 3 4 5 6 7 8 9
    // 偶数: 5, 基数: 4
    public int countGoodNumbers(long n) {
        int i = 0;
        int result = 1;
        while (i < n) {
            int k = (i % 2 == 0) ? 5 : 4;
            result *= k;
            result = result % (1_000_000_007);
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        Day13 day13 = new Day13();
        System.out.println(day13.countGoodNumbers(50));
    }
}
