package com.game.chess;

import java.util.Scanner;

public class NQueens {
	private Scanner input = new Scanner(System.in);
	private int N, total;

	public static void main(String[] args) {
		new NQueens().start();
	}

	private void start() {
		System.out.println("Enter board size :");
		N = input.nextInt();
		int[][] board = new int[N][N];
		total = (N == 3) ? N - 1 : N;
		if (N <= 2) {
			total = 1;
		}
		placeQueen(board, 0);
//		if (placeQueen(board, 0)) {
//			printBoard(board);
//		}
	}

	private void printBoard(int[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0)
					System.out.print("x ");
				else
					System.out.print("Q ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private boolean placeQueen(int[][] board, int queenPlaced) {
		if (queenPlaced >= total) {
			printBoard(board);
			return true;
		}
		for (int i = 0; i < N; i++) {
			if (isSafe(board, queenPlaced, i)) {
				board[queenPlaced][i] = 1;
//				if (placeQueen(board, queenPlaced + 1)) {
//					return true;
//				}
				placeQueen(board, queenPlaced + 1);
				board[queenPlaced][i] = 0;
			}
		}
		return false;
	}

	private boolean isSafe(int[][] board, int row, int col) {
		// col check
		for (int i = 0; i < N; i++) {
			if (board[i][col] == 1) {
				return false;
			}
		}
		// right diagonal
//		for (int i = row, j = col; i < N && j < N; i++, j++) {
//			if (board[i][j] == 1) {
//				return false;
//			}
//		}
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}
		// left diagonal
//		for (int i = row, j = col; i < N && j >= 0; i++, j--) {
//			if (board[i][j] == 1) {
//				return false;
//			}
//		}
		for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
			if (board[i][j] == 1) {
				return false;
			}
		}
		return true;
	}

}
