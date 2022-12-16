package com.game.dungeon;

import java.util.Scanner;

public class Level7 {
	private Scanner input = new Scanner(System.in);
	private int min;

	public static void main(String[] args) {
		new Level7().getInput();
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
		System.out.println("Position of Trigger: ");
		int tRow = input.nextInt();
		int tCol = input.nextInt();
		dungeon[tRow - 1][tCol - 1] = 'T';
		System.out.println("Position of Gold: ");
		int gRow = input.nextInt();
		int gCol = input.nextInt();
		dungeon[gRow - 1][gCol - 1] = 'G';
		min = Integer.MAX_VALUE;
		findMinimumSteps(dungeon, mRow - 1, mCol - 1, 0, 'G');
		int monster = min;
		System.out.print("\nEnter number of pits: ");
		int pits = input.nextInt();
		for (int i = 0; i < pits; i++) {
			System.out.print("\nPosition of Pit " + (i + 1) + ": ");
			dungeon[input.nextInt() - 1][input.nextInt() - 1] = 'P';
		}
		min = Integer.MAX_VALUE;
		findMinimumSteps(dungeon, aRow - 1, aCol - 1, 0, 'G');
		if (min <= monster)
			System.out.println("\nMinimum numberof Steps: " + min);
		else {
			int noOfMoves = 0;
			min = Integer.MAX_VALUE;
			findMinimumSteps(dungeon, aRow - 1, aCol - 1, 0, 'T');
			noOfMoves += min;
			min = Integer.MAX_VALUE;
			findMinimumSteps(dungeon, tRow - 1, tCol - 1, 0, 'G');
			noOfMoves += min;
			System.out.println("\nMinimum numberof Steps: " + noOfMoves);
		}
	}

	private void findMinimumSteps(char[][] dungeon, int rows, int columns, int noOfMoves, char target) {
		int chances[][] = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
		int row, col;
		for (int i = 0; i < 4; i++) {
			row = rows + chances[i][0];
			col = columns + chances[i][1];
			if (row >= 0 && col >= 0 && row < dungeon.length && col < dungeon[0].length) {
				if (dungeon[row][col] == target) {
					noOfMoves += 1;
					if (noOfMoves < min) {
						min = noOfMoves;
					}
					return;
				} else if (dungeon[row][col] == '\u0000') {
					dungeon[row][col] = 'A';
					findMinimumSteps(dungeon, row, col, noOfMoves + 1, target);
					dungeon[row][col] = '\u0000';
				}
			}
		}
	}

}
