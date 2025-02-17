package com.ganten.tethys.top150;

public class N130 {
    private boolean[][] used;

    public void solve(char[][] board) {
        this.used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1) {
                    this.helper(board, i, j);
                }
            }
        }
        this.replace(board, 'O', 'X');
        this.replace(board, 'M', 'O');
    }

    public void replace(char[][] board, char a, char b) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == a) board[i][j] = b;
            }
        }
    }

    private void helper(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length) return;
        if (j < 0 || j >= board[0].length) return;
        if (board[i][j] == 'X' || used[i][j]) return;
        used[i][j] = true;
        board[i][j] = 'M';
        this.helper(board, i + 1, j);
        this.helper(board, i - 1, j);
        this.helper(board, i, j + 1);
        this.helper(board, i, j - 1);
    }
}
