package fourte;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController {
	List<String> possibleOperators;
	Set<String> answers;
	final int target;
	char[] operators = { '+', '-', '*', '/' };

	GameController(int k) {
		this.target = k;
		possibleOperators = new ArrayList<>(64);
		fillPossibleOperators();
		answers = new HashSet<>();
	}

	public Set<String> solve(double[] arr) {
		findPermutations(arr, 0);
		return answers;
	}

	private void findPermutations(double[] arr, int index) {
		if (index == arr.length) {
			findPossibleOperators(arr);
		}
		for (int i = index; i < arr.length; i++) {
			swap(arr, i, index);
			findPermutations(arr, i + 1);
			swap(arr, i, index);
		}
	}

	private void swap(double[] arr, int i, int index) {
		double temp = arr[i];
		arr[i] = arr[index];
		arr[index] = temp;
	}

	private void findPossibleOperators(double[] arr) {
		for (String operator : possibleOperators) {
			findAnswer(operator, arr);
		}
	}

	private void findAnswer(String op, double[] arr) {
		double value = 0;
		String answer = "";
		double left = arr[0] * 10 + arr[1], middle = arr[1] * 10 + arr[2], right = arr[2] * 10 + arr[3],
				left1 = arr[0] * 100 + (arr[1] * 10 + arr[2]), right1 = (arr[1] * 100 + (arr[2] * 10 + arr[3]));
		;
		// 3 symbols
		answer = "((" + (int) arr[0] + "" + op.charAt(0) + "" + +(int) arr[1] + ")" + op.charAt(1) + "" + (int) arr[2]
				+ ")" + op.charAt(2) + "" + (int) arr[3];
		value = operate(arr[0], arr[1], op.charAt(0));
		value = operate(value, arr[2], op.charAt(1));
		value = operate(value, arr[3], op.charAt(2));
		if (value == target) {
			answers.add(answer);
		}

		answer = "(" + (int) arr[0] + "" + op.charAt(0) + "" + +(int) arr[1] + ")" + op.charAt(1) + "(" + (int) arr[2]
				+ "" + op.charAt(2) + "" + (int) arr[3] + ")";
		double v1 = operate(arr[0], arr[1], op.charAt(0));
		double v2 = operate(arr[2], arr[3], op.charAt(2));
		value = operate(v1, v2, op.charAt(1));
		if (value == target)
			answers.add(answer);

		answer = (int) arr[0] + "" + op.charAt(0) + "(" + +(int) arr[1] + "" + op.charAt(1) + "" + (int) arr[2] + ")"
				+ op.charAt(2) + "" + (int) arr[3];
		value = operate(arr[1], arr[2], op.charAt(1));
		value = operate(arr[0], value, op.charAt(0));
		value = operate(value, arr[3], op.charAt(2));
		if (value == target)
			answers.add(answer);

		// 2 symbols

		answer = (int) left + "" + op.charAt(1) + "" + (int) arr[2] + "" + op.charAt(2) + "" + (int) arr[3];
		value = operate(left, arr[2], op.charAt(1));
		value = operate(value, arr[3], op.charAt(2));
		if (value == target)
			answers.add(answer);
		answer = (int) left + "" + op.charAt(1) + "(" + (int) arr[2] + "" + op.charAt(2) + "" + (int) arr[3] + ")";
		value = operate(arr[2], arr[3], op.charAt(2));
		value = operate(left, value, op.charAt(1));
		if (value == target)
			answers.add(answer);

		answer = (int) arr[0] + "" + op.charAt(1) + "" + (int) middle + "" + op.charAt(2) + "" + (int) arr[3];
		value = operate(arr[0], middle, op.charAt(1));
		value = operate(value, arr[3], op.charAt(2));
		if (value == target)
			answers.add(answer);
		answer = (int) arr[0] + "" + op.charAt(1) + "(" + (int) middle + "" + op.charAt(2) + "" + (int) arr[3] + ")";
		value = operate(middle, arr[3], op.charAt(2));
		value = operate(arr[0], value, op.charAt(1));
		if (value == target)
			answers.add(answer);

		answer = (int) arr[0] + "" + op.charAt(1) + "" + (int) arr[1] + "" + op.charAt(2) + "" + (int) right;
		value = operate(arr[0], arr[1], op.charAt(1));
		value = operate(value, right, op.charAt(2));
		if (value == target)
			answers.add(answer);
		answer = (int) arr[0] + "" + op.charAt(1) + "(" + (int) arr[1] + "" + op.charAt(2) + "" + (int) right + ")";
		value = operate(arr[1], right, op.charAt(2));
		value = operate(arr[0], value, op.charAt(1));
		if (value == target)
			answers.add(answer);

		// 1 symbol
		answer = (int) left + "" + op.charAt(2) + "" + (int) right;
		value = operate(left, right, op.charAt(2));
		if (value == target)
			answers.add(answer);

		answer = (int) left1 + "" + op.charAt(2) + "" + (int) arr[3];
		value = operate(left1, arr[3], op.charAt(2));
		if (value == target)
			answers.add(answer);

		answer = (int) arr[0] + "" + op.charAt(2) + "" + (int) right1;
		value = operate(arr[0], right1, op.charAt(2));
		if (value == target)
			answers.add(answer);
	}

	private double operate(double i, double j, char operator) {
		switch (operator) {
		case '+':
			return i + j;
		case '-':
			return i - j;
		case '*':
			return i * j;
		case '/':
			if (j != 0 && i >= j) {
				return i / j;
			}
		}
		return 0.0;
	}

	private void fillPossibleOperators() {
		int possibles = 64, number, index;
		String temp;
		for (int i = 0; i < possibles; i++) {
			temp = "";
			number = i;
			index = 2;
			while (index-- > -1) {
				temp = operators[number % 4] + temp;
				number /= 4;
			}
			possibleOperators.add(temp);
		}
	}
}
