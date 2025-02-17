package com.ganten.tethys.top150;

public class N12 {

    public static void main(String[] args) {
        N12 n12 = new N12();
        System.out.println(n12.intToRoman(3749));
    }

    // num 最大为 3999
    public String intToRoman(int num) {
        String result = "";
        // 千位
        result += helper(num / 1000, ' ', ' ', 'M');
        // 百位
        result += helper((num % 1000) / 100, 'M', 'D', 'C');
        // 十位
        result += helper((num % 100) / 10, 'C', 'L', 'X');
        // 个位
        result += helper(num % 10, 'X', 'V', 'I');
        return result;
    }


    /**
     * @param k 数字位数 (1-9)
     * @param l 10 * k 对应的罗马字符 (如 'X' 对应 10, 'C' 对应 100)
     * @param m 5 * k 对应的罗马字符 (如 'V' 对应 5, 'L' 对应 50)
     * @param n 1 * k 对应的罗马字符 (如 'I' 对应 1, 'X' 对应 10)
     * @return 对应的罗马数字表示
     */
    public String helper(int k, char l, char m, char n) {
        if (k == 0) {
            return "";
        }
        if (k < 0 || k >= 10) {
            throw new IllegalArgumentException("k must be between 1 and 9");
        }

        if (k == 4) {
            return "" + n + m;
        } else if (k == 9) {
            return "" + n + l;
        } else if (k >= 5) {
            StringBuilder sb = new StringBuilder();
            sb.append(m);
            for (int i = 0; i < k - 5; i++) {
                sb.append(n);
            }
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < k; i++) {
                sb.append(n);
            }
            return sb.toString();
        }
    }
}
