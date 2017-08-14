package CG_Marathon;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Solution2 {
	static int M, N, K;
	static int min;
	static int[][] matrix;
	static boolean[][] water;
	static int[][][] cache;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			min = Integer.MAX_VALUE;
			N = sc.nextInt(); // �� ��
			M = sc.nextInt();
			K = sc.nextInt();
			
			matrix = new int[M + 1][N + 1];
			water = new boolean[M + 1][N + 1];
			cache = new int[M + 1][N + 1][11];
			
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					Arrays.fill(cache[i][j], -1); // 1D Array�� ��� �������� -1�� �ʱ�ȭ
					
					matrix[i][j] = sc.nextInt();					
					if (matrix[i][j] < 0) {
						water[i][j] = true;
						matrix[i][j] *= -1;
					}
				}
			}
			
			int Answer;
			if (water[M][N]) {
				Answer = findNext(M, N, 1);
			} else {
				Answer = findNext(M, N, 0);
			}

			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
	
	static int findNext(int x, int y, int countOfWater) { 
		if (countOfWater > 10) {
			countOfWater = 10;
		}
		
		if (x == 0 && y == 0 && countOfWater >= K) { // (M, N)���� (0, 0)���� ���� �濡 water���� count, ������ ��� �ԾƳ���
			return 0;
		}
		
		if (cache[x][y][countOfWater] > -1) { // (0, 0)���� (M, N)���� ���ư��� �濡 �߰������ �����ؼ� �ߺ� ��� ���ϱ�(DP)
			return cache[x][y][countOfWater];
		}
		
		int min = 1000;
		if (x - 1 >= 0) {
			if (water[x - 1][y]) { // ���� ����
				min = Math.abs(matrix[x][y] - matrix[x - 1][y]) + findNext(x - 1, y, countOfWater + 1);
			} else {
				min = Math.abs(matrix[x][y] - matrix[x - 1][y]) + findNext(x - 1, y, countOfWater);
			}
		}
		
		if (y - 1 >= 0) {
			if (water[x][y - 1]) { // ���� ����
				min = Math.min(min, Math.abs(matrix[x][y] - matrix[x][y - 1]) + findNext(x, y - 1, countOfWater + 1));
			} else {
				min = Math.min(min, Math.abs(matrix[x][y] - matrix[x][y - 1]) +  findNext(x, y - 1, countOfWater));
			}
		}
		
		cache[x][y][countOfWater] = min;
		return min;
	}
}