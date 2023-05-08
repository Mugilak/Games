package com.game.tictactoe;

import java.util.Scanner;

public class TicTacToe {
	char[][] board = setupBoard();
	private Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		new TicTacToe().start();
	}

	private void start() {
		int position = 0, count = 0;
		char currentPlayer = 'X';
		while (count < 9) {
			printBoard();
			position = getInput(currentPlayer);
			if (position == 0) {
				System.out.println("invalid position!");
				continue;
			}
			setAtPosition(position, currentPlayer);
			if (checkForWin(currentPlayer) != '0') {
				System.out.println(currentPlayer + " wins the game");
				printBoard();
				return;
			}
			if (currentPlayer == 'X') {
				currentPlayer = 'O';
			} else {
				currentPlayer = 'X';
			}
			count++;
		}
		printBoard();
		System.out.println("Match Draw");
	}

	private char checkForWin(char currentPlayer) {
		String winingPossible = (char) currentPlayer + "" + (char) currentPlayer + "" + (char) currentPlayer + "";
		if ((board[0][0] + "" + board[0][1] + "" + board[0][2] + "").equals(winingPossible)
				|| ((board[1][0] + "" + board[1][1] + "" + board[1][2] + "").equals(winingPossible))
				|| ((board[2][0] + "" + board[2][1] + "" + board[2][2] + "").equals(winingPossible))) {
			return currentPlayer;
		} else if ((board[0][0] + "" + board[1][0] + "" + board[2][0] + "").equals(winingPossible)
				|| ((board[0][1] + "" + board[1][1] + "" + board[2][1] + "").equals(winingPossible))
				|| ((board[0][2] + "" + board[1][2] + "" + board[2][2] + "").equals(winingPossible))) {
			return currentPlayer;
		} else if (((board[0][0] + "" + board[1][1] + "" + board[2][2] + "").equals(winingPossible))
				|| ((board[0][2] + "" + board[1][1] + "" + board[2][0] + "").equals(winingPossible))) {
			return currentPlayer;
		}
		return '0';
	}

	private void setAtPosition(int position, char currentPlayer) {
		int row = 0, column = 0;
		if (position <= 3) {
			board[0][position - 1] = currentPlayer;
		} else {
			row = position / 3;
			column = (position % 3) - 1;
			if (position % 3 == 0) {
				row -= 1;
				column = 2;
			}
			board[row][column] = currentPlayer;
		}
	}

	private int getInput(char currentPlayer) {
		System.out.println(currentPlayer + "'s Turn\nEnter position : ");
		int position = input.nextInt();
		if (position > 0 & position <= 9) {
			return position;
		}
		return 0;
	}

	private void printBoard() {
		System.out.println("+------");
		for (char[] row : board) {
			System.out.print("|");
			for (char col : row) {
				System.out.printf("%2s", col);
			}
			System.out.println();
		}
		System.out.println();
	}

	private char[][] setupBoard() {
		char[][] board = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 0) {
					board[i][j] = (char) (j + 1 + 48);
				} else {
					board[i][j] = (char) ((i * i) + 3 + j + 48);
				}
			}
		}
		return board;
	}

}
