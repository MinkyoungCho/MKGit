package Tetro;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Main {
	static int N, M, Answer;
	static int[][] map;
	static boolean[][] mark;

	static int dfs (int i, int j, int count) {
		if (count == 5) {
			return 0;
		}
		
		mark[i][j] = true;
		
		int res = -1;
		if (j + 1 <= M && !mark[i][j + 1]) {
			res = Math.max(res, map[i][j] + dfs (i, j + 1, count + 1));
		}
		if (i + 1 <= N && !mark[i + 1][j]) {
			res = Math.max(res, map[i][j] + dfs (i + 1, j, count + 1));
		}

		if (j - 1 > 0 && !mark[i][j - 1]) {
			res = Math.max(res, map[i][j] + dfs (i, j - 1, count + 1));
		}
		if (i - 1 > 0 && !mark[i - 1][j]) {
			res = Math.max(res, map[i][j] + dfs (i - 1, j, count + 1));
		}

		mark[i][j] = false;
		return res;
	}
	
	public static void main(String args[]) throws Exception	{
		long start = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
//		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));
		
			Answer = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N + 1][M + 1];
			mark = new boolean[N + 1][M + 1];
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			//가장 위&왼쪽 지점 선택
			// 	-만들 수 있는 모든 모양 만들기
			//	-최대값 선택
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					mark[i][j] = true;
					
					if (j + 1 <= M && !mark[i][j + 1]) {
						Answer = Math.max(Answer, map[i][j] + dfs (i, j + 1, 2));
					}
					if (i + 1 <= N && !mark[i + 1][j]) {
						Answer = Math.max(Answer, map[i][j] + dfs (i + 1, j, 2));
					}
					
					if (i <= N - 1 && j >= 2 && j <= M - 1) { // ㅗ
						int temp = map[i][j];
						temp += map[i + 1][j - 1];
						temp += map[i + 1][j];
						temp += map[i + 1][j + 1];
						if (temp > Answer) {
							Answer = temp;
						}
					}
					if (i <= N - 2 && j <= M - 1) { // ㅏ
						int temp = map[i][j];
						temp += map[i + 1][j];
						temp += map[i + 1][j + 1];
						temp += map[i + 2][j];
						if (temp > Answer) {
							Answer = temp;
						}
					}
					if (i <= N - 1 && j <= M - 2) { //ㅜ
						int temp = map[i][j];
						temp += map[i][j + 1];
						temp += map[i][j + 2];
						temp += map[i + 1][j + 1];
						if (temp > Answer) {
							Answer = temp;
						}
					}
					if (i <= N - 2 && j >= 2) { //ㅓ
						int temp = map[i][j];
						temp += map[i + 1][j];
						temp += map[i + 1][j - 1];
						temp += map[i + 2][j];
						if (temp > Answer) {
							Answer = temp;
						}
					}
				}
			}

			System.out.println(Answer);
		}
//		long end = System.currentTimeMillis();
//		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );

	
}