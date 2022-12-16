package com.game.minesweeper;

import java.util.*;

public class MinesweeperLeetcode {
	int dir[][] = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

	public static void main(String[] args) {
		new MinesweeperLeetcode().getInput();
	}

	private void getInput() {
		char[][] board = { { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'M', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' },
				{ 'E', 'E', 'E', 'E', 'E' } };
		int click[] = { 3, 0 };
		System.out.println("type 1 - " + Arrays.deepToString(updateBoard(board, click)));
		System.out.println("type 2 - " + Arrays.deepToString(boardUpdate(board, click)));
	}

	public char[][] updateBoard(char[][] board, int[] click) {
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { click[0], click[1] });
		while (!(q.isEmpty())) {
			int[] val = q.poll();
			int r = val[0];
			int c = val[1];
			if (board[r][c] == 'E') {
				int mine = 0, blank = 0;
				for (int i = 0; i < 8; i++) {
					int row = r + dir[i][0];
					int col = c + dir[i][1];
					if (row >= 0 && col >= 0 && row < board.length && col < board[0].length) {
						if (board[row][col] == 'M') {
							mine++;
						} else if (board[row][col] == 'B') {
							blank++;
						} else if (board[row][col] == 'E') {
							q.offer(new int[] { row, col });
						}
					}
				}
				if (mine > 0 && blank > 0) {
					board[r][c] = (char) (mine + 48);
				} else if (mine < 1 && blank >= 0) {
					board[r][c] = 'B';
				}
			}
		}
		return board;
	}

	private char[][] boardUpdate(char[][] board, int[] click) {
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { click[0], click[1] });
		while (!(q.isEmpty())) {
			int[] val = q.poll();
			int r = val[0];
			int c = val[1];
			int mine = findMinesAround(r, c, board);
			if (mine > 0) {
				board[r][c] = (char) (mine + 48);
			} else {
				board[r][c] = 'B';
				for (int i = 0; i < 8; i++) {
					int row = r + dir[i][0];
					int col = c + dir[i][1];
					if (row >= 0 && col >= 0 && row < board.length && col < board[0].length) {
						if (board[row][col] == 'E') {
							q.offer(new int[] { row, col });
						}
					}
				}
			}
		}
		return board;
	}

	private int findMinesAround(int r, int c, char[][] board) {
		int mine = 0;
		for (int i = 0; i < 8; i++) {
			int row = r + dir[i][0];
			int col = c + dir[i][1];
			if (row >= 0 && col >= 0 && row < board.length && col < board[0].length) {
				if (board[row][col] == 'M') {
					mine++;
				}
			}
		}
		return mine;
	}

}
