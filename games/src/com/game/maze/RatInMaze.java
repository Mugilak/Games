package com.game.maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RatInMaze {
	ArrayList<String> list = new ArrayList<String>();

	public static void main(String[] args) {
		new RatInMaze().getInput();
	}

	private void getInput() {
//		int[][] maze = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 1, 1, 0, 0 }, { 0, 1, 1, 1 } };
		int[][] maze = { { 1, 1 }, { 1, 1 } };
//		int[][] maze = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 },
//				{ 1, 1, 1, 1, 1 } };
		ArrayList<String> ans = findPath(maze, maze.length);
		Collections.sort(ans);
		System.out.println(ans);
		System.out.println();
		ArrayList<String> list2 = new ArrayList<String>();
		try {
			Scanner file = new Scanner(new File("C:\\Users\\91638\\Downloads\\fileOutput.txt"));
			while (file.hasNext()) {
				String[] array = file.nextLine().split(" ");
				for (String s : array) {
					list2.add(s);
				}
			}
			Collections.sort(list2);
//			System.out.println(list2.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> findPath(int[][] m, int n) {
		if (m[0][0] == 1) {
			m[0][0] = 0;
			findAllPaths(m, 0, 0, n - 1, "");
		}
		return list;
	}

	private void findAllPaths(int[][] maze, int row, int column, int target, String path) {
		int[] d = { 0, -1, 0, 1, 0 };
		String currPath = "";
		if (row == target && column == target) {
			list.add(path);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int r = row + d[i];
			int c = column + d[i + 1];
			if (isSafe(r, c, target)) {
				if (maze[r][c] == 1) {
					if (i == 0) {
						currPath = path + "L";
					} else if (i == 1) {
						currPath = path + "U";
					} else if (i == 2) {
						currPath = path + "R";
					} else if (i == 3) {
						currPath = path + "D";
					}
					maze[r][c] = 0;
					findAllPaths(maze, r, c, target, currPath);
					maze[r][c] = 1;
				}
			}
		}
	}

	private boolean isSafe(int row, int col, int n) {
		if (row >= 0 && row <= n && col >= 0 && col <= n) {
			return true;
		}
		return false;
	}

	private void findAllPath(int[][] maze, int row, int column, int target, String path) {
		if (row == target && column == target) {
			list.add(path);
			return;
		}
		if (row < target) {
			if (row > 0) {
				if (maze[row - 1][column] == 1) {
					maze[row - 1][column] = 0;
					findAllPath(maze, row - 1, column, target, path + "U");
					maze[row - 1][column] = 1;
				}
			}
			if (maze[row + 1][column] == 1) {
				maze[row + 1][column] = 0;
				findAllPath(maze, row + 1, column, target, path + "D");
				maze[row + 1][column] = 1;
			}
		}
		if (column < target) {
			if (column > 0) {
				if (maze[row][column - 1] == 1) {
					maze[row][column - 1] = 0;
					findAllPath(maze, row, column - 1, target, path + "L");
					maze[row][column - 1] = 1;
				}
			}
			if (maze[row][column + 1] == 1) {
				maze[row][column + 1] = 0;
				findAllPath(maze, row, column + 1, target, path + "R");
				maze[row][column + 1] = 1;
			}
		}
	}
}
