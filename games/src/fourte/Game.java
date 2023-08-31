package fourte;

import java.util.*;

public class Game {

	public static void main(String[] args) {
		new Game().start();
	}

	private void start() {
		Scanner inp = new Scanner(System.in);
		double arr[] = new double[4];
		System.out.println("Enter Total : ");
		int k = inp.nextInt();
		for (int i = 0; i < 4; i++) {
			System.out.print("\nEnter number " + (i + 1) + " : ");
			arr[i] = inp.nextDouble();
		}
		GameController control = new GameController(k);
		Set<String> answer = control.solve(arr);
		answer.forEach((x) -> System.out.println(x));
		inp.close();
	}

}