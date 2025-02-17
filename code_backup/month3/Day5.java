package com.ganten.tethys.daily.year2025.month3;

public class Day5 {

    public long coloredCells(int n) {
        if (n == 1) {
            return 1;
        }
        return coloredCells(n - 1) + (n - 1) * 4L;
    }

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        System.out.println(day5.coloredCells(3));
    }
}
