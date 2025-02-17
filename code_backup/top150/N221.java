package com.ganten.tethys.top150;

public class N221 {
    public int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        // init dp, dp[i][j] 表示, 以 [i][j] 为终点的正方形的边长

        for (int i = 0; i < matrix.length; i++) dp[i][0] = matrix[i][0] - '0';
        for (int i = 0; i < matrix[0].length; i++) dp[0][i] = matrix[0][i] - '0';
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] - '0' == 0) {
                    dp[i][j] = 0;
                } else {
                    int min = Math.min(dp[i][j - 1], dp[i - 1][j]);
                    dp[i][j] = Math.min(dp[i - 1][j - 1], min) + 1;
                }
            }
        }
        int result = 0;
        for (int[] ints : dp) {
            for (int anInt : ints) {
                result = Math.max(result, anInt);
            }
        }
        return result * result;
    }

    public static void main(String[] args) {
        N221 n221 = new N221();
        n221.maximalSquare(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}});
    }
}
