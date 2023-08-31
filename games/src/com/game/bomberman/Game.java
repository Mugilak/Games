package com.game.bomberman;

import java.util.List;
import java.util.Scanner;

public class Game {
	private Map map;
	private Scanner input = new Scanner(System.in);

	Game() {
		map = new Map();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setup(game);
	}

	public static void fillBlank(List<String> grid) {
		int r = grid.size(), c = 0;
		String s = "";
		for (int i = 0; i < r; i++) {
			c = grid.get(i).length();
			s = "";
			for (int j = 0; j < c; j++) {
				s += 'O';
			}
			grid.set(i, s);
		}
	}

	private void setup(Game game) {
		game.createMap();
		game.fixInputs();
		map.showMap();
	}

	private void fixInputs() {
		int count = 0;
		input.nextLine();
		System.out.println("Player position : (row and column Ex: 23)");
		map.fixPosition(input.nextLine(), 'P');
		System.out.println("Key Position : (row and column Ex: 34)");
		map.fixPosition(input.nextLine(), 'K');
		System.out.println("Villain count : ");
		count = input.nextInt();
		input.nextLine();
		for (int i = 0; i < count; i++) {
			System.out.println("Villain Position : ");
			map.fixPosition(input.nextLine(), 'V');
		}
		System.out.println("Brick count : ");
		count = input.nextInt();
		input.nextLine();
		for (int i = 0; i < count; i++) {
			System.out.println("Bricks Position : ");
			map.fixPosition(input.nextLine(), 'B');
		}
	}

	private void createMap() {
		System.out.println("Enter map size : (max size = 26)");
		int size = checkSize(input.nextInt());
		map.createMap(size);
		map.showMap();
	}

	private int checkSize(int size) {
		while (size <= 3 || size > 26) {
			System.out.println("Invalid size... enter again : ");
			size = input.nextInt();
		}
		return size;
	}

}
