package com.ganten.tethys.top150;

public class N289 {
    public static void main(String[] args) {
        N289 n289 = new N289();
        n289.gameOfLife(new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}});
    }

    public void gameOfLife(int[][] board) {
        int[][] temp = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                temp[i][j] = this.isLife(board, i, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(temp[i], 0, board[i], 0, board[0].length);
        }
    }

    private int isLife(int[][] board, int i, int j) {
        int count = 0;
        int xStart = Math.max(i - 1, 0);
        int xEnd = Math.min(i + 1, board.length - 1);
        int yStart = Math.max(j - 1, 0);
        int yEnd = Math.min(j + 1, board[0].length - 1);
        for (int k = xStart; k <= xEnd; k++) {
            for (int l = yStart; l <= yEnd; l++) {
                if (k == i && l == j) continue;
                if (board[k][l] == 1) count++;
            }
        }
        if (count == 3) {
            return 1;
        }
        if (count > 3 || count < 2) {
            return 0;
        }
        return board[i][j];
    }
}
