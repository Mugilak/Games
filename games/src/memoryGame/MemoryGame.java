package memoryGame;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MemoryGame {

	static int[][] cards = new int[4][4];
	static boolean upDown[][] = new boolean[4][4];
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		setup();
		game(upDown, cards); // calls the game
	}

	// print the board
	public static void setup() {
		for (int i = 0; i < 4; i++) {
			for (int a = 0; a < 4; a++) {
				upDown[i][a] = false;
			}
		}
		cards = randomizer(); // Shuffle cards
	}

	// print the board
	public static void displayBoard(boolean[][] upDown, int[][] cards) {

		System.out.println("     1 2 3 4 ");
		System.out.println("---+---------");
		for (int i = 0; i < 4; i++) {
			System.out.print(" " + (i + 1) + " | ");
			for (int a = 0; a < 4; a++) {
				if (upDown[i][a]) {
					System.out.print(cards[i][a] + " ");
				} else
					System.out.print("* ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int[][] randomizer() {
		int num[] = { 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8 };
		int cards[][] = new int[4][4];
		Random random = new Random();
		int temp, t;
		for (int x = 0; x < 16; x++) { // Randomize the card order
			t = random.nextInt(100) % 15;
			temp = num[x];
			num[x] = num[t];
			num[t] = temp;

		}
		t = 0;
		for (int r = 0; r < 4; r++) // Cards receive Numbers
		{
			for (int s = 0; s < 4; s++) {
				cards[r][s] = num[t];
				t = t + 1;
			}
		}
		return cards;
	}

	// Start the Game
	public static void game(boolean[][] upDown, int[][] cards) {
		int noDownCards = 16;
		try {
			while (noDownCards > 0) {
				displayBoard(upDown, cards);
				System.out.println("Enter co-oridinate 1----  (to exit -exit)");
				String g1 = s.next();
				if (g1.equalsIgnoreCase("exit")) {
					throw new InputMismatchException();
				}
				int g1x = Integer.valueOf(g1.substring(0, 1)) - 1;
				int g1y = Integer.valueOf(g1.substring(1, 2)) - 1;
				System.out.println(cards[g1x][g1y]);

				System.out.println("Enter co-oridinate 2----  (to exit -exit)");
				String g2 = s.next();
				if (g2.equalsIgnoreCase("exit")) {
					throw new InputMismatchException();
				}
				int g2x = Integer.valueOf(g2.substring(0, 1)) - 1;
				int g2y = Integer.valueOf(g2.substring(1, 2)) - 1;
				System.out.println(cards[g2x][g2y]);
				if (cards[g1x][g1y] == cards[g2x][g2y]) {
					System.out.println("**  You found a match  **\n");
					upDown[g1x][g1y] = true;
					upDown[g2x][g2y] = true;
					noDownCards -= 2;
				} else {
					System.out.println("Match not found ! .. Try Again\n");
				}
			}
			displayBoard(upDown, cards);
			System.out.println("You win");
		} catch (InputMismatchException e) {
			System.out.println("Game exited");
		}
	}

}