package com.game.snakesandladders;

import java.util.*;

public class NewGame {
	private Controller control;
	private static String choice;
	private boolean isSucceed = false;
	private Scanner input = new Scanner(System.in);

	private NewGame() {
		control = new Controller(this);
	}

	public static void main(String[] args) {
		new NewGame().start();
	}

	private void start() {
		control.fill();
		getInput();
	}

	private void getInput() {
		System.out.println("current square : 1");
		do {
			control.showBoard();
			System.out.println();
			System.out.println("Enter (1 - throw dice, 2 - exit)");
			choice = input.nextLine();
			switch (choice) {
			case "1":
				Random random = new Random();
				int val = random.nextInt(1, 7);
				System.out.println("Dice : " + val);
				control.operate(val);
				break;
			case "2":
				choice = "3";
				break;
			default:
				System.out.println("Invalid input ");
			}
		} while (!(choice.equals("3")));
		control.showBoard();
		if (isSucceed) {
			System.out.println("\n*****You are succeeded !*****\nexited game");
		}
	}

	public void make(String ch) {
		choice = ch;
		isSucceed = true;
	}
}
