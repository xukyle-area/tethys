package com.ganten.tethys.daily.year2025.month3;

// TODO
public class Day10 {
    private int[] countForVowel = new int[6];
    private int k;

    public long countOfSubstrings(String word, int k) {
        this.k = k;
        char[] chars = word.toCharArray();
        int left = 0;
        int right = -1;
        long result = 0;
        while (true) {
            int meet = this.ifMeet();
            if (meet == 3) {
                System.out.println(left + " " + right + " " + word.substring(left, right + 1));
                result++;

                char leftChar = chars[left];
                countForVowel[this.calculateIndex(leftChar)] -= 1;
                left++;
            }
            if (meet == 0 || meet == 1) {
                if (right == chars.length - 1) {
                    break;
                }
                right++;
                char rightChar = chars[right];
                countForVowel[this.calculateIndex(rightChar)] += 1;
            }
            if (meet == 2) {
                char leftChar = chars[left];
                countForVowel[this.calculateIndex(leftChar)] -= 1;
                left++;
            }
        }
        return result;
    }

    /**
     * 0 代表 元音缺失
     * 1 代表 元音满足，但是辅音缺少
     * 2 代表 元音满足，但是辅音多了
     * 3 代表 全部满足
     */
    private int ifMeet() {
        if (countForVowel[5] < k) {
            return 1;
        } else if (countForVowel[5] > k) {
            return 2;
        } else {
            for (int i = 0; i < 5; i++) {
                if (countForVowel[i] == 0) {
                    return 0;
                }
            }
            return 3;
        }
    }

    private int calculateIndex(char letter) {
        switch (letter) {
            case 'a':
                return 0;
            case 'e':
                return 1;
            case 'i':
                return 2;
            case 'o':
                return 3;
            case 'u':
                return 4;
            default:
                return 5;
        }
    }

    public static void main(String[] args) {
        Day10 day10 = new Day10();
        // iqeaouqi
        System.out.println("result: " + day10.countOfSubstrings("iqeaouqi", 2));
    }
}
