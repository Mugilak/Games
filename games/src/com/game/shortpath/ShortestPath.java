package com.game.shortpath;

import java.util.*;

public class ShortestPath {
	private int r, c, min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		new ShortestPath().getInput();
	}

	private void getInput() {
		r = 10;
		c = 10;
//		int[][] mat = {
//				{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
//						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1,
//						1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 } };

//		int[][] mat = { { 0, 0, 1 }, { 0, 1, 1 }, { 1, 1, 0 } };
//		int[][] mat = { { 0, 1 }, { 1, 0 } };

		int[][] mat = new int[r][c];
//		int[][] mat = { { 0, 1, 1, 0, 0, 0 }, { 1, 0, 0, 0, 1, 0 } };

//		int[][][][] a = new int[r][c][4 ][3];
//		for (int i = 0; i < r; i++) {
//			for (int j = 0; j < c; j++) {
//				for (int k = 0; k < 4; k++) {
//					for (int l = 0; l < 3; l++) {
//						System.out.print(a[i][j][k][l] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		System.out.println("bfs : "+shortestPath(mat, r, c, 3));
//		findMinPath(mat, 0, 0, 3, 0);
//		System.out.print("backtrack : ");
//		System.out.println((min==Integer.MAX_VALUE)?-1:min);
	}

	private int shortestPath(int[][] mat, int n, int m, int k) {
		int[] dir = { 0, 1, 0, -1, 0 };
		int min = Integer.MAX_VALUE;
		boolean[][][] visit = new boolean[n][m][k + 1];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 0, 0, k, 0 });
		visit[0][0][0] = true;
		int rows, cols;
		while (!(q.isEmpty())) {
			int[] val = q.poll();
			int row = val[0];
			int col = val[1];
			int obs = val[2];
			int path = val[3];
			if (row == n - 1 && col == m - 1) {
				if (path < min) {
					min = path;
				}
				return path;
			}
			for (int i = 0; i < 4; i++) {
				rows = row + dir[i];
				cols = col + dir[i + 1];
				if (rows >= 0 && cols >= 0 && rows < n && cols < m) {
					if (visit[rows][cols][obs]) {
						continue;
					}
					if (mat[rows][cols] == 0) {
						q.offer(new int[] { rows, cols, obs, path + 1 });
						visit[rows][cols][obs] = true;
					}
					if (mat[rows][cols] == 1 && obs > 0) {
						q.offer(new int[] { rows, cols, obs - 1, path + 1 });
						visit[rows][cols][obs] = true;
					}
				}
			}
		}
		return -1;
	}

	private void findMinPath(int[][] mat, int row, int col, int k, int path) {
		int[] dir = { 0, 1, 0, -1, 0 };
		int rows, cols;
		if (row == r - 1 && col == c - 1) {
			path += 1;
			System.out.println(path);
			min=path;
			return;
		}
		for (int i = 0; i < 4; i++) {
			rows = row + dir[i];
			cols = col + dir[i + 1];
			if (rows >= 0 && cols >= 0 && rows < r && cols < c) {
				if (rows == r - 1 && cols == c - 1) {
					path += 1;
					System.out.println(path);
					min=path;
					return;
				} else if ((mat[rows][cols] == 1) && (k > 0)) {
					mat[rows][cols] = -1;
					findMinPath(mat, rows, cols, k - 1, path + 1);
					mat[rows][cols] = 1;
				} else if (mat[rows][cols] == 0) {
					mat[rows][cols] = -1;
					findMinPath(mat, rows, cols, k, path + 1);
					mat[rows][cols] = 0;
				}
			}
		}
	}
}
