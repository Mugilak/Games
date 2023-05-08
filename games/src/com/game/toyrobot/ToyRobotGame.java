package com.game.toyrobot;

import java.util.Scanner;

public class ToyRobotGame {
	private Scanner input = new Scanner(System.in);
	int[][] board;
	int[] obstacles, position;

	public static void main(String[] args) {
		new ToyRobotGame().processBoard();
	}

	private void processBoard() {
		System.out.print("Enter row size: ");
		int row = input.nextInt();
		System.out.print("\nEnter column size: ");
		int column = input.nextInt();
		if (row != 0 || column != 0) {
			setupBoard(row, column);
			getObstacles(row * column);
			printBoard(row, column);
			startGame(row, column);
		} else
			System.out.println("\nInvalid input!");
	}

	private void startGame(int row, int column) {
		int option = 0, pos = 0;
		while (true) {
			showInstruction();
			option = input.nextInt();
			switch (option) {
			case 1:
				System.out.println("enter current position : ");
				pos = input.nextInt();
				setToyPosition(pos, column);
				System.out.println("enter steps: ");
				int steps = input.nextInt();
				play(row, column, steps);
				System.out.println(board[position[0]][position[1]]);
				break;
			case 2:
				System.out.println("exited");
				return;
			default:
				System.out.println("invalid input");
			}
		}
	}

	private void play(int row, int column, int steps) {
		int[] start = { position[0], position[1] };
		for (int i = 1; i <= steps; i++) {
			if (position[0] % 2 == 0) { // is even row ->
				if (position[1] == column - 1 || isInObstacle(board[position[0]][position[1] + 1])) { // is last column
					if (position[0] + 1 < row && isInObstacle(board[position[0] + 1][position[1]])) { // down block
						if (position[0] - 1 >= 0 && !isInObstacle(board[position[0] - 1][position[1]])) { // up not
																											// block
							position[0] = position[0] - 1;
						}
					} else {
						if (position[0] + 1 < row) {
							position[0] = position[0] + 1;
						}
					}
				} else {
					position[1] = position[1] + 1;
				}
			} else {
				if (position[1] == 0 || isInObstacle(board[position[0]][position[1] - 1])) { // is first column
					if (position[0] + 1 < row && isInObstacle(board[position[0] + 1][position[1]])) { // down block
						if (position[0] - 1 >= 0 && !isInObstacle(board[position[0] - 1][position[1]])) { // up not
																											// block
							position[0] = position[0] - 1;
						}
					} else {
						if (position[0] + 1 < row) {
							position[0] = position[0] + 1;
						}
					}
				} else {
					position[1] = position[1] - 1;
				}
			}
			if (start[0] == position[0] && start[1] == position[1]) {
				break;
			}
		}
	}

	private void setToyPosition(int toyPosition, int column) {
		position = new int[2];
		position[0] = (toyPosition - 1) / column;
		if (position[0] % 2 == 0) {
			position[1] = (toyPosition - 1) % column;
		} else {
			position[1] = (column - 1) - ((toyPosition - 1) % column);
		}
	}

	private void showInstruction() {
		System.out.println("""
				1. To play
				2. Exit
				""");
	}

	private void printBoard(int row, int column) {
		for (int[] outer : board) {
			for (int value : outer) {
				if (isInObstacle(value)) {
					System.out.printf("%3s", ("*" + value));
				} else {
					System.out.printf("%3s", ("" + value));
				}
			}
			System.out.println();
		}
	}

	private boolean isInObstacle(int value) {
		for (int obs : obstacles) {
			if (obs == value) {
				return true;
			}
		}
		return false;
	}

	private void getObstacles(int last) {
		System.out.println("\nEnter No. obstacles : ");
		int size = input.nextInt(), obs = 0;
		obstacles = new int[size];
		for (int i = 0; i < size; i++) {
			System.out.println("Enter Obstacle " + (i + 1) + " : ");
			obs = input.nextInt();
			if (obs < 1 && obs > last) {
				i--;
				System.out.println("--- invalid input");
				continue;
			} else {
				obstacles[i] = obs;
			}
		}
	}

	private void setupBoard(int row, int column) {
		board = new int[row][column];
		int count = 1;
		for (int i = 0; i < row; i++) {
			if (i % 2 == 0) {
				for (int j = 0; j < column; j++) {
					board[i][j] = count++;
				}
			} else {
				for (int j = column - 1; j >= 0; j--) {
					board[i][j] = count++;
				}
			}
		}
	}

}
