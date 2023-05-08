package com.game.snakesandladders;

import java.util.Scanner;

public class MinimumDiceThrow {
	private Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		new MinimumDiceThrow().getInput();
	}

	private void getInput() {
		System.out.println("size :");
		int n = input.nextInt();
		int[] a = new int[n * 2];
		System.out.println("enter elements : ");
		for (int i = 0; i < n * 2; i++) {
			a[i] = input.nextInt();
		}
		System.out.println(minThrow(n, a));
	}

	// 3 22 5 8 11 26 20 29 17 4 19 7 27 1 21 9

	private int minThrow(int N, int arr[]) {
		int position = 1, min = 0, moves = 7;
//		boolean isFound = false;
		for (int i = 0; i < (N * 2) - 1; i += 2) {
			// isFound = true;
			// for(int j=1;j<=6;j++){
			if (isMin(arr, arr[i]) && (position) < arr[i]) {
				// isFound= false;
				if (arr[i] < arr[i + 1]) {
					while (moves < arr[i]) {
						moves += 6;
						min++;
					}
					if (moves >= arr[i]) {
						position = arr[i + 1];
						moves += arr[i];
						arr[i] = Integer.MAX_VALUE;
						min++;
						while (isValid(position, i, arr) == false) {
							for (int j = 6; j > 0; j--) {
								if (isValid(position + i, arr)) {
//									System.out.println(i);
									position += j;
									min++;
									break;
								}
							}
						}
					}
				}
			}
		}
		while (position < 30) {
			for (int i = 6; i > 0; i--) {
				if (isValid(position + i, arr)) {
//					System.out.println(i);
					position += i;
					min++;
					break;
				}
			}
		}
		return min;
	}

	private boolean isValid(int position, int index, int[] arr) {
		if (position > 30) {
			return false;
		}
		int len = arr.length;
		for (int i = 0; i < len - 1; i += 2) {
			if (arr[i] >= position && arr[i] < arr[i + 1]) {
				return false;
			}
		}
		return true;
	}

	private boolean isMin(int[] arr, int value) {
		int len = arr.length - 1, min = 0, max = 1;
		for (int i = 2; i < len; i += 2) {
			if (arr[i] == Integer.MAX_VALUE) {
				continue;
			}
			if (arr[i] < arr[min] && arr[max] < arr[i + 1]) {
				min = i;
				max = i + 1;
			}
		}
		if (value == arr[min]) {
			return true;
		}
		return false;
	}

	private boolean isValid(int position, int[] arr) {
		if (position > 30) {
			return false;
		}
		int len = arr.length;
		for (int i = 0; i < len - 1; i += 2) {
			if (arr[i] == position) {
				return false;
			}
		}
		return true;
	}

}
