package snakeGame;

public class Screen {
	private final int length = 9;
	private char[][] screen = new char[length][length];

	public static void main(String[] args) {
		new Screen().start();
	}

	private void start() {
		printScreen();
	}

	private void printScreen() {
		for (int i = 1; i <= length + 2; i++) {
			System.out.print("`");
			for (int j = 1; j <= length; j++) {
				if (i == 1 || i == length + 2) {
					System.out.print("  ` ");
				} else {
					System.out.print("| ");
					System.out.print("_ ");
				}
			}
			System.out.print("|");
			System.out.print("`");
			System.out.println();
		}
	}
}
