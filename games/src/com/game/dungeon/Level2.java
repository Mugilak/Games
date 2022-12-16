package com.game.dungeon;

import java.util.Scanner;

public class Level2 {
	private Scanner input = new Scanner(System.in);
	private int min;

	public static void main(String[] args) {
		new Level2().getInput();
	}

	private void getInput() {
		System.out.println("Dimensions of the dungeon(Row x Column): ");
		int row = input.nextInt();
		int col = input.nextInt();
		char[][] dungeon = new char[row][col];
		System.out.println("Position of Adventurer: ");
		int aRow = input.nextInt();
		int aCol = input.nextInt();
		dungeon[aRow - 1][aCol - 1] = 'A';
		System.out.println("Position of Monster: ");
		int mRow = input.nextInt();
		int mCol = input.nextInt();
		dungeon[mRow - 1][mCol - 1] = 'M';
		System.out.println("Position of Gold: ");
		int gRow = input.nextInt();
		int gCol = input.nextInt();
		dungeon[gRow - 1][gCol - 1] = 'G';
		min = Integer.MAX_VALUE;
		findMinimumSteps(dungeon, aRow - 1, aCol - 1, 0);
		int adventure = min;
		min = Integer.MAX_VALUE;
		findMinimumSteps(dungeon, mRow - 1, mCol - 1, 0);
		if (adventure <= min)
			System.out.println("\nMinimum numberof Steps: " + min);
		else
			System.out.println("No possible solution");
	}

	private void findMinimumSteps(char[][] dungeon, int rows, int columns, int noOfMoves) {
		int chances[][] = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
		int row, col;
		for (int i = 0; i < 4; i++) {
			row = rows + chances[i][0];
			col = columns + chances[i][1];
			if (row >= 0 && col >= 0 && row < dungeon.length && col < dungeon[0].length) {
				if (dungeon[row][col] == 'G') {
					noOfMoves += 1;
					if (noOfMoves < min) {
						min = noOfMoves;
					}
					return;
				} else if (dungeon[row][col] == '\u0000') {
					dungeon[row][col] = 'A';
					findMinimumSteps(dungeon, row, col, noOfMoves + 1);
					dungeon[row][col] = '\u0000';
				}
			}
		}
	}

}
