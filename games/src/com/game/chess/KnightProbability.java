package com.game.chess;

public class KnightProbability {
	int totalMoves;
	boolean[][] visit;

	public static void main(String[] args) {
		new KnightProbability().start();
	}

	private void start() {
		// 3, 2, 0, 0
		double prob = knightProbability(1, 0, 0, 0);
		System.out.println(totalMoves + " " + prob);
	}

	public double knightProbability(int n, int k, int row, int column) {
		int[][] board = new int[n][n];
		visit = new boolean[n][n];
		board[row][column] = -1;
		visit[row][column] = true;
		findPossibleMoves(board, k, row, column, 0);
		return (totalMoves == 0) ? 1.0 : totalMoves / (double) 64;
	}

	private void findPossibleMoves(int[][] board, int k, int r, int c, int moves) {
		int n = board.length, row, column;
		int[][] d = { { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 } };
		for (int i = 0; i < 8; i++) {
			row = r + d[i][0];
			column = c + d[i][1];
			if (row >= 0 && row < n && column >= 0 && column < n) {
				if (moves >= k) {
					totalMoves += moves;
					return;
				}
				if (visit[row][column] == true) {
					continue;
				}
				if (board[row][column] == 0) {
					board[row][column] = -1;
					visit[row][column] = true;
					findPossibleMoves(board, k, row, column, moves + 1);
					board[row][column] = 0;
				}
			}
		}
	}

}
