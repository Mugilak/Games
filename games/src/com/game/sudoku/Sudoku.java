package com.game.sudoku;

public class Sudoku {

	public static void main(String[] args) {
		new Sudoku().getInput();
	}

	private void getInput() {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		solveSudoku(board);
	}

	public void solveSudoku(char[][] board) {
		if (solve(board)) {
			printBoard(board);
		}
	}

	private void printBoard(char[][] board) {
		int len = board.length;
		// System.out.print("[");
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean solve(char[][] board) {
		boolean isFound = false;
		int row = 0, col = 0;
		loop: for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') {
					row = i;
					col = j;
					isFound = true;
					break loop;
				}
			}
		}
		if (!(isFound)) {
			return true;
		}
		for (int k = 1; k <= 9; k++) {
			if (isValid(board, k, row, col)) {
				board[row][col] = (char) (k + 48);
				if (solve(board)) {
					return true;
				}
				board[row][col] = '.';
			}
		}
		return false;
	}

	private boolean isValid(char[][] board, int num, int row, int col) {
		int len = board.length;
		// row check
		int j = 0;
		while (j < len) {
			if ((board[row][j] - '0') == num) {
				return false;
			}
			j++;
		}
		// col check
		j = 0;
		while (j < len) {
			if ((board[j][col] - '0') == num) {
				return false;
			}
			j++;
		}
		int rows = row - row % 3;
		int cols = col - col % 3;
		for (int i = rows; i < rows + 3; i++) {
			for (j = cols; j < cols + 3; j++) {
				if ((board[i][j] - '0') == num) {
					return false;
				}
			}
		}
		return true;
	}
}
