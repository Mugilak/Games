package com.game.chess;

public class KnightProbability {
	double totalMoves;
	boolean[][] visit;

	public static void main(String[] args) {
		new KnightProbability().start();
	}

	private void start() {
		// 3, 2, 0, 0
		double prob = knightProbability(3, 2, 1, 2);
		System.out.println( prob);
	}

	public double knightProbability(int n, int k, int row, int column) {
		int[][] board = new int[n][n];
		findPossibleMoves(board, k, row, column);
		return totalMoves;
	}// (totalMoves == 0) ? 1.0 :

	private void findPossibleMoves(int[][] board, int k, int row, int column) {
		int n = board.length, r, c, t = k;
		double[][] dp2 = new double[n][n];
		int[][] d = { { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 } };
//		if (0 == k) {
//			totalMoves += 1;
//		}
		dp2[row][column]=1;
		while (t > 0) {
			double dp[][] = new double[n][n];
			for (r = 0; r < n; r++) {
				for (c =0; c < n; c++) {
					for (int dir = 0; dir < 8; dir++) {
						int r1 = r + d[dir][0];
						int col = c + d[dir][1];
						if (r1 >= 0 && r1 < n && col >= 0 && col < n) {
//							moves++;
//							if (moves == k) {
//								totalMoves += 1;
////								t--;
//								break loop;
//							}
							dp[r1][col] += dp2[r][c] / 8.0;
						}
					}
				}
			}
			dp2 = dp;
			t--;
		}

		for (double[] value : dp2) {
			for (double val : value) {
				totalMoves += val;
			}
		}
	}

	private void findPossibleMove(int[][] board, int k, int r, int c, int moves) {
		int n = board.length, row, column;
		int[][] d = { { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 } };
		if (0 == k) {
			totalMoves += 1;
		}
		for (int i = 0; i < 8; i++) {
			row = r + d[i][0];
			column = c + d[i][1];
			if (row >= 0 && row < n && column >= 0 && column < n) {
				if (moves == k) {
					totalMoves += 1;
					return;
				}
				findPossibleMove(board, k, row, column, moves + 1);
			}
		}
	}

}
