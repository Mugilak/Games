package com.game.nimgame;

import java.util.Scanner;

public class NimGame {
	private Scanner input = new Scanner(System.in);
	private char winner;

	class Human {
		int position, remove;
	}

	Human A = new Human();

	public static void main(String[] args) {
		NimGame game = new NimGame();
		game.startGame();
	}

	private void startGame() {
		char start = ' ';
		int[] board = { 5, 8, 3, 1 };
		System.out.println("...Game started...\nHuman Vs Computer");
		System.out.println("do you want to start ( press..  1 - yes, 0 - No )");
		int choice = input.nextInt();
		if (choice == 1) {
			start = 'h';
		} else if (choice == 0) {
			start = 'c';
		} else {
			System.out.println("Invalid input");
		}
		playGame(board, start);
		showBoard(board);
		System.out.println((winner == 'h') ? "You Won the game !" : "Computer won the game");
	}

	private void playGame(int[] board, char currentPlayer) {
		while (gameOver(board)) {
			showBoard(board);
			if (currentPlayer == 'h') {
				System.out.println("----------\nYour turn -->\n");
				getInput(board);
				remove(board);
				playGame(board, 'c');
			} else if (currentPlayer == 'c') {
				System.out.println("----------\nComputer turn -->\n");
				int max1 = findMax(board, Integer.MAX_VALUE);
				int max2 = findMax(board, board[max1]);
				System.out.print("chose " + (max1 + 1) + " position");
				if (board[max2] % 2 == 0) {
					System.out.println("\n removed " + board[max1]);
					board[max1] = 0;
				} else {
					System.out.println("\n removed " + board[max2]);
					board[max1] -= board[max2];
				}
				playGame(board, 'h');
			}
		}
		winner = (currentPlayer == 'c') ? 'h' : 'c';
		return;
	}

	private void showBoard(int[] board) {
		System.out.println("__  __  __  __");
		for (int value : board) {
			System.out.print(value + " | ");
		}
		System.out.println("\n__  __  __  __");
	}

	private int findMax(int[] board, int max) {
		int maximum = 0;
		for (int i = 1; i < board.length; i++) {
			if (board[i] != 0 && board[maximum] < board[i] && board[i] < max) {
				maximum = i;
			}
		}
		return maximum;
	}

	private void remove(int[] board) {
		board[A.position - 1] -= A.remove;
	}

	private boolean eval(int[] board) {
		if (A.position < 0 && A.position > board.length) {
			return false;
		}
		if (board[A.position - 1] < A.remove || board[A.position - 1] == 0) {
			System.out.println("Invalid input");
			return false;
		}
		return true;
	}

	private void getInput(int[] board) {
		System.out.println("Enter position : ");
		int position = input.nextInt();
		System.out.println("Enter removing numbers : ");
		int remove = input.nextInt();
		A.position = position;
		A.remove = remove;
		if (eval(board)) {
			return;
		} else {
			getInput(board);
		}
	}

	private boolean gameOver(int[] board) {
		int count = 0;
		for (int number : board) {
			if (number == 0) {
				count++;
			}
		}
		return (count == board.length) ? false : true;
	}

}
