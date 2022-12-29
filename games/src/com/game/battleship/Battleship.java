package com.game.battleship;

import java.util.*;

public class Battleship {
	private Scanner input = new Scanner(System.in);
	private Board board;
	private char currentPlayer;
	private int row, column, computerShips, humanShips;
	private String playerName;

	Battleship() {
		board = new Board();
	}

	public static void main(String[] args) {
		Battleship game = new Battleship();
		game.setup();
	}

	private void setup() {
		System.out.println(" ----- GAME OF BATTLESHIPS -----\n");
		board.setupBoard();
		System.out.println("\nPlayer Vs Computer");
		setupComputerShip(5);
		System.out.println("Enter your Name to continue : ");
		playerName = input.nextLine();
		System.out.println("\nSetup your ships ...");
		setupHumanShip(1);
		startGame();
	}

	private void startGame() {
		input.nextLine();
		startingPlayer();
		System.out.println("\n ----- Game Starts -----\n");
		playGame(currentPlayer, 0, 0);
		printWinner();
	}

	private void printWinner() {
		if (computerShips == 5 && humanShips == 5) {
			System.out.println("Match draw ... Both won the game");
			return;
		}
		if (computerShips == 5) {
			System.out.println("\n----------------------\n ---- " + playerName + " won the game ----");
		} else if (humanShips == 5) {
			System.out.println("\n----------------------\n ---- Computer won the game ----");
		} else {
			System.out.println("None won the game");
		}
	}

	private void playGame(char currentPlayer, int computerShips, int humanShips) {
		if (computerShips == 5 || humanShips == 5) {
			this.computerShips = computerShips;
			this.humanShips = humanShips;
			return;
		}
		if (currentPlayer == 'h') {
			System.out.println("--------------\n");
			getInput();
			if (board.isComputerShipSinked(row, column)) {
				computerShips++;
			}
			showScore("You ", computerShips);
			playGame('c', computerShips, humanShips);
		} else if (currentPlayer == 'c') {
			getComputerInput();
			if (board.isHumanShipSinked(row, column)) {
				humanShips++;
			}
			showScore("Computer ", humanShips);
			playGame('h', computerShips, humanShips);
		}
	}

	private void getComputerInput() {
		Random random = new Random();
		row = random.nextInt(0, 10);
		column = random.nextInt(0, 10);
	}

	private void showScore(String string, int ships) {
		System.out.println(string + " sinked : " + ships);
	}

	private void getInput() {
		System.out.print("Enter row : ");
		row = input.nextInt();
		System.out.print("Enter column : ");
		column = input.nextInt();
		if (!(isSafe(row, column))) {
			System.out.println("\nInvalid place ...");
			getInput();
		}
	}

	private boolean isSafe(int row, int column) {
		if (row >= 0 && row < 10 && column >= 0 && column < 10) {
			return true;
		}
		return false;
	}

	private void startingPlayer() {
		System.out.println("Do you want to start the game ???  (yes/no)");
		String choice = input.nextLine();
		if (choice.equalsIgnoreCase("yes")) {
			currentPlayer = 'h';
		} else if (choice.equalsIgnoreCase("no")) {
			currentPlayer = 'c';
		} else {
			System.out.println("Invalid input (type yes / no)");
			startingPlayer();
		}
	}

	private void setupHumanShip(int index) {
		if (index == 6) {
			System.out.println("You are setuped your ships\n");
			return;
		}
		row = 0;
		column = 0;
		boolean flag = false;
		do {
			flag = false;
			System.out.println("\nrow : ");
			row = input.nextInt();
			System.out.print("column : ");
			column = input.nextInt();
			if (board.checkHumanShip(row, column)) {
				System.out.println("Invalid place \n----------------");
				flag = true;
			}
		} while (flag);
		board.setHumanShip(row, column);
		System.out.println("ship " + index + " placed");
		setupHumanShip(index + 1);
	}

	private void setupComputerShip(int index) {
		Random random = new Random();
		if (index == 0) {
			System.out.println("\nComputer setuped its ships");
			return;
		}
		row = 0;
		column = 0;
		do {
			row = random.nextInt(0, 10);
			column = random.nextInt(0, 10);
		} while (board.checkComputerShip(row, column));
		board.setComputerShip(row, column);
		setupComputerShip(index - 1);
	}

}
