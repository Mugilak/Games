package com.game.battleship;

public class Board {
	int board[][];
	int computerShip[][], humanShip[][];

	public void setupBoard() {
		board = new int[10][10];
		computerShip = new int[10][10];
		humanShip = new int[10][10];
	}

	public void showBoard() {
		System.out.println("\n ----------------------------------- \n");
		for (int value[] : board) {
			for (int value2 : value) {
				System.out.print("* ");
			}
			System.out.println();
		}
		System.out.println("\n ----------------------------------- \n");
	}

	public boolean checkComputerShip(int row, int column) {
		if (computerShip[row][column] == 1) {
			return true;
		}
		return false;
	}

	public void setComputerShip(int row, int column) {
		computerShip[row][column] = 1;
	}

	public boolean checkHumanShip(int row, int column) {
		if (row < 0 || row >= 10) {
			return true;
		}
		if (column < 0 || column >= 10) {
			return true;
		}
		if (humanShip[row][column] == 1) {
			return true;
		}
		return false;
	}

	public void setHumanShip(int row, int column) {
		humanShip[row][column] = 1;
	}

	public boolean isHumanShipSinked(int row, int column) {
		if (humanShip[row][column] == 1) {
			humanShip[row][column] = 0;
			return true;
		}
		return false;
	}

	public boolean isComputerShipSinked(int row, int column) {
		if (computerShip[row][column] == 1) {
			computerShip[row][column] = 0;
			return true;
		}
		return false;
	}

}
