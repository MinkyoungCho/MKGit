package ±×·¡ÇÁ;

import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] relation;
	static int[] result;
	static boolean flag = true;
	
	public static boolean isPossible(int current) {
		int adjacent = 0;
		
		while (adjacent < current) {
			if (relation[current][adjacent] == 1 && result[adjacent] == result[current]) {
				return false;
			}
			
			adjacent++;
		}
		
		return true;
	}

	public static void coloring(int i) {
		if (isPossible(i) && flag) {
			if (i == N - 1) {
				for (int  j= 0; j < N; j++) {
					System.out.print(result[j] + " ");
				}
				
				flag = false;
				
			} else {
				for (int color = 1; color <= M; color++) {
					result[i + 1] = color;
					coloring(i + 1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		relation = new int[N][N];
		result = new int[N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i + 1; j++) {
				relation[i][j] = sc.nextInt();
			}
		}
		
		result[0] = 1;
		coloring(0);
		
		if (flag) {
			System.out.println(-1);
		}
		
	}

}
