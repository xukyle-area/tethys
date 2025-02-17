package com.ganten.tethys.top150;

public class N79 {
    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean[][] used = new boolean[board.length][board[0].length];
                if (this.helper(used, board, i, j, chars, 0)) {
                    System.out.println("start: " + i + " " + j);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean helper(boolean[][] used, char[][] board, int i, int j, char[] chars, int index) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || index == chars.length) return false;
        if (used[i][j] || board[i][j] != chars[index]) return false;
        if (index == chars.length - 1) {
            System.out.println("end: " + i + " " + j);
            return true;
        }
        used[i][j] = true;
        boolean result = this.helper(used, board, i + 1, j, chars, index + 1) ||
                this.helper(used, board, i - 1, j, chars, index + 1) ||
                this.helper(used, board, i, j + 1, chars, index + 1) ||
                this.helper(used, board, i, j - 1, chars, index + 1);
        used[i][j] = false;
        return result;
    }
}
