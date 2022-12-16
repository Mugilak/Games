package com.game.snakesandladders;

public class Controller {
	int[][] board = { { 36, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
			{ -1, 35, -1, -1, 13, -1 }, { -1, -1, -1, -1, -1, -1 }, { -2, 15, -1, -1, -1, -1 } };
	int n = board.length;
	int[] board1D = new int[n * n + 1];
	int r = n - 1, c = 0, s = 1;
	boolean isFound = false;
	private NewGame game;

	public Controller(NewGame newGame) {
		game = newGame;
	}

	public void showBoard() {
		int value = 36;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == -1) {
					if (i % 2 == 0) {
						System.out.printf("%4d", value);
					} else if (i % 2 != 0) {
						System.out.printf("%4d", value);
					}
				} else if (board[i][j] == -2) {
					System.out.print("   ^");
				} else if (board[i][j] == 36) {
					System.out.print("   *");
				} else {
					System.out.printf(" $%2d", board[i][j]);
				}
				if (i % 2 == 0) {
					--value;
				} else {
					++value;
				}
			}
			if (i % 2 == 0) {
				value -= 5;
			} else {
				value -= 7;
			}
			System.out.println();
		}
		System.out.println();
	}

	public void operate(int roll) {
		findLast(roll);
	}

	private void findLast(int roll) {
		int square = s + roll;
		if (square > 0 && square <= n * n) {
			if (board1D[square] == 36) {
				board1D[square - roll] = -1;
				board1D[square] = -2;
				s = square;
				fillBoard();
				System.out.println("current square : " + square);
				game.make("3");
				return;
			} else if (board1D[square] == -1) {
				board1D[square - roll] = -1;
				board1D[square] = -2;
				s = square;
				System.out.println("current square : " + square);
			} else if (board1D[square] != -1 && board1D[square] > 0 && board1D[square] <= n * n) {
				board1D[board1D[square]] = -2;
				System.out.println("current square : " + board1D[square]);
				board1D[s] = -1;
				s = board1D[square];
			}
		} else {
			System.out.println("Unbounded");
		}
		fillBoard();
	}

	public void fillBoard() {
		int k = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (i % 2 == 1) {
				for (int j = 0; j < n; j++) {
					board[i][j] = board1D[k++];
				}
			} else {
				for (int j = n - 1; j >= 0; j--) {
					board[i][j] = board1D[k++];
				}
			}
		}
//		System.out.println(Arrays.deepToString(board));
	}

	public void fill() {
		int k = 1, n = board.length;
		for (int i = n - 1; i >= 0; i--) {
			if (i % 2 == 1) {
				for (int j = 0; j < n; j++) {
					board1D[k++] = board[i][j];
				}
			} else {
				for (int j = n - 1; j >= 0; j--) {
					board1D[k++] = board[i][j];
				}
			}
		}
//		System.out.println(Arrays.toString(board1D));
	}
}
