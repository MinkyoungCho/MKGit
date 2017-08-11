package CG_Marathon;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	static int M, N, K;
	static int min;
	static int[][] matrix;
	static int[][] cache;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			min = Integer.MAX_VALUE;
			N = sc.nextInt(); // 열 개
			M = sc.nextInt();
			K = sc.nextInt();
			
			matrix = new int[M + 1][N + 1];
			cache = new int[M + 1][N + 1];
			
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
			
//			goNext(M + N, 0, 0, 0, 0);
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(goNext(M + N, 0, 0, 0, 0));
		}
	}
	
	static int goNext(int count, int x, int y, int water, int sumOfDiff) {
		int min = Integer.MAX_VALUE;
		
		if (count == 0) {
			if (x == M && y == N && water >= K) {
				if (sumOfDiff < min) {
					min = sumOfDiff;
				}
			}
			return min;
		}
		
		if (cache[x][y] > 0) {
			return cache[x][y]; 
		}
		
		int latitude = matrix[x][y];
		
		if (matrix[x][y] < 0) {
			water++;
		}
		
		
		// 오른쪽
		if (y < N) {
			min = goNext(count - 1, x, y + 1, water, sumOfDiff + Math.abs(Math.abs(latitude) - Math.abs(matrix[x][y + 1])));
		}
		
		// 아래
		if (x < M) {
			min = Math.min(min, goNext(count - 1, x + 1, y, water, sumOfDiff + Math.abs(Math.abs(latitude) - Math.abs(matrix[x + 1][y]))));
		}
		
		cache[x][y] = min;
		return min;
	}
}