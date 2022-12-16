package com.game.snakesandladders;

import java.util.*;

public class Level1 {
	int[] newBoard;
	static int min, r, c;

	public static void main(String[] args) {
		new Level1().getInput();
	}

	private void getInput() {
//		int[][] board = { { -1, -1 }, { -1, 3 } };
//		int[][] board = { { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
//				{ -1, 35, -1, -1, 13, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, 15, -1, -1, -1, -1 } };
		int[][] board = { { -1, -1, -1 }, { -1, 9, 8 }, { -1, 8, 9 } };
		snakesAndLadders(board);
	}

	public void snakesAndLadders(int[][] board) {
		int n = board.length, square = 1, moves = 0, totalSquare = n * n, min = Integer.MAX_VALUE;
		newBoard = new int[(n * n) + 1];
		boolean[] visited = new boolean[(n * n) + 1];
		fill(board, n);
		System.out.println(Arrays.toString(newBoard));
		Queue<int[]> queue = new LinkedList<>();
		boolean isFound = false;
		queue.offer(new int[] { square, moves });
		visited[1] = true;
		int[] arr = { 0, 0 };
		while (!(queue.isEmpty())) {
			arr = queue.poll();
			for (int box = 1; box <= 6; box++) {
				square = arr[0] + box;
				moves = arr[1];
				if (square > 0 && square <= totalSquare) {
					if ((visited[square]) == false) {
						visited[square] = true;
						if (square == totalSquare) {
							isFound = true;
							if (moves < min) {
								min = moves + 1;
							}
						} else if (newBoard[square] != -1) {
							queue.offer(new int[] { newBoard[square], moves + 1 });
						} else if (newBoard[square] == -1) {
							queue.offer(new int[] { square, moves + 1 });
						}
					}
				}
			}
		}
		if (isFound) {
			System.out.println(min);
		} else {
			System.out.println("-1");
		}
	}

	private void findMinMoves(int[][] realBoard, int[][] board, int row, int col, boolean canInc, int moves) {
		int[] die = { 1, 2, 3, 4, 5, 6 };
		int rows, cols, n = board.length;
		for (int i = 0; i < 6; i++) {
			if (canInc) {
				rows = row;
				cols = col + die[i];
			} else {
				rows = row;
				cols = col - die[i];
			}
			if (cols >= n) {
				cols = n - (cols - (n - 1));
				rows -= 1;
				canInc = false;
			} else if (cols < 0) {
				rows -= 1;
				cols = 0 - (cols + 1);
				canInc = true;
			}
			if (rows >= 0 && cols >= 0 && rows < n && cols < n) {
				if (realBoard[rows][cols] == 36) {
					moves += 1;
					if (moves < min) {
						min = moves;
					}
					return;
				} else if (board[rows][cols] != -1) {
					findPos(board[rows][cols]);
					if (r % 2 == 0) {
						canInc = false;
					} else {
						canInc = true;
					}
					findMinMoves(realBoard, board, r, c, canInc, moves);
					board[rows][cols] = realBoard[r][c];
				} else if (board[rows][cols] == -1) {
					findMinMoves(realBoard, board, rows, cols, canInc, moves + 1);
				}
			}
		}
	}

	private void findPos(int num) {
		int n = newBoard.length;
		for (int i = 0; i < n; i++) {
			if (newBoard[i] == num) {
				r = i;
				break;
			}
		}
	}

	private void fill(int[][] board, int n) {
		int k = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (i % 2 == 1) {
				for (int j = 0; j < n; j++) {
					newBoard[k++] = board[i][j];
				}
			} else {
				for (int j = n - 1; j >= 0; j--) {
					newBoard[k++] = board[i][j];
				}
			}
		}
	}

}
