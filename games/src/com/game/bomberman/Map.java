package com.game.bomberman;

import java.util.Arrays;

public class Map {
	char[][] map;
	int size;

	public void createMap(int size) {
		this.size = size;
		map = new char[size][size];
		for (char[] array : map) {
			Arrays.fill(array, ' ');
		}
		createInitialMap(size);
	}

	private void createInitialMap(int size) {
		int j = 0;
		for (int i = 0; i < size; i++) {
			j = 0;
			if (i != 0) {
				if (j == 0) {
					map[i][j] = (char) (i + 64);
					j++;
				}
				if (j == 1) {
					map[i][j] = '*';
					j = size - 1;
				}
				if (j == size - 1) {
					map[i][j] = '*';
				}
			}
			for (j = 1; j < size; j++) {
				if (i == 0) {
					map[i][j] = (char) (j + 64);
				}
				if ((i == 1 && j != 0) || i == size - 1) {
					map[i][j] = '*';
				}
				if (i >= 3 && j >= 3) {
					if (i % 2 != 0 && j % 2 != 0) {
						map[i][j] = '*';
					}
				}
			}
		}
	}

	public void showMap() {
		for (char[] row : map) {
			for (char value : row) {
				System.out.printf("%3s", value);
			}
			System.out.println();
		}
	}

	public boolean fixPosition(String position, char hold) {
		int row = position.charAt(0) - 48, column = position.charAt(1) - 48;
		if (row >= 0 && row < size - 1 && column >= 2 && column < size - 1) {
			if (map[row][column] == ' ') {
				map[row][column] = hold;
				return true;
			}
		}
		return false;
	}
}
