package random;

import java.util.Random;
import java.util.Scanner;

public class RandomGame {
	private Scanner input = new Scanner(System.in);
	private Random random = new Random();
	private int size = 3, step, i = 0, j = 0, m = 0, n = size - 1;
	private String board[][] = new String[size][size];
	private int player1, player2;

	public static void main(String[] args) {
		RandomGame game = new RandomGame();
		game.setUp();
		game.start();
	}

	private static void instructions() {
		System.out.println("Enter co-ordinates : ");
	}

	private void setUp() {
		board[0][0] = "P1";
		board[0][size - 1] = "P2";
		System.out.println("Both players are located at the position...... \n         ***START GAME***");
	}

	private void printBoard() {
		System.out.println("   1 2 3 ");
		System.out.println("--+------------");
		for (int i = 0; i < size; i++) {
			System.out.print(i + 1 + " |");
			for (int j = 0; j < size; j++) {
				if (board[i][j] != null)
					System.out.print(board[i][j] + " ");
				else
					System.out.print("* ");
			}
			System.out.println();
		}
	}

	private void generateRandom() {
		int i, j;
		i = random.nextInt(0, size);
		j = random.nextInt(0, size);
		board[i][j] = "5";
	}

	private void start() {
		boolean isSamePlace = false;
		do {
			generateRandom();
			printBoard();
			instructions();
			p1Turn();
			p1Turn();

		} while (!isSamePlace);
		System.out.println("crashed--- game drops");
	}

	private void p1Turn() {
		// TODO Auto-generated method stub
		getInput();
	}

	private void p2Turn() {
		// TODO Auto-generated method stub
		getInput();

	}

	private void setP1() {

	}

	private void setP2() {

	}

	private void chooseBlock(int step) {
		int temp;
		switch (step) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			System.out.println("Invalid step : ");
		}
	}

	private void getInput() {
		step = input.nextInt();
		check(step);
	}

	private void check(int step) {

	}
}
