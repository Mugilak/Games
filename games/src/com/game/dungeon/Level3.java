package com.game.dungeon;

import java.util.Scanner;

public class Level3 {
	private Scanner input = new Scanner(System.in);
	private int min;
	private String path;

	public static void main(String[] args) {
		new Level3().getInput();
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
		path = "(" + aRow + "," + aCol + ")->";
		findMinimumSteps(dungeon, aRow - 1, aCol - 1, 0, path);
		String aPath = path;
		int adventure = min;
		min = Integer.MAX_VALUE;
		path = "(" + mRow + "," + mCol + ")->";
		findMinimumSteps(dungeon, mRow - 1, mCol - 1, 0, path);
		if (adventure <= min)
			System.out.println("\nMinimum numberof Steps: " + min + "\nPath: " + aPath);
		else
			System.out.println("No possible solution");
	}

	private void findMinimumSteps(char[][] dungeon, int rows, int columns, int noOfMoves, String path2) {
		int chances[][] = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
		int row, col;
		for (int i = 0; i < 4; i++) {
			row = rows + chances[i][0];
			col = columns + chances[i][1];
			if (row >= 0 && col >= 0 && row < dungeon.length && col < dungeon[0].length) {
				if (dungeon[row][col] == 'G') {
					path = path2 + "(" + (row + 1) + "," + (col + 1) + ")";
					noOfMoves += 1;
					if (noOfMoves < min) {
						min = noOfMoves;
					}
					return;
				} else if (dungeon[row][col] == '\u0000') {
					dungeon[row][col] = 'A';
					String currPath = path2 + "(" + (row + 1) + "," + (col + 1) + ")->";
					findMinimumSteps(dungeon, row, col, noOfMoves + 1, currPath);
					dungeon[row][col] = '\u0000';
				}
			}
		}
	}

}
