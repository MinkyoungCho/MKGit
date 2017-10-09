package Honey;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	static int Answer;
	static int N, M, C;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			C = sc.nextInt();
			
			int[][] map = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			int[][] profit = new int[N + 1][N - M + 2];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= (N - M + 1); j++) {
					int[] temp = new int[M + 1];
					int tempJ = j;
					for (int k = 1; k <= M; k++) {
						temp[k] = map[i][tempJ++];
					}
					profit[i][j] = selectMax(1, C, temp);
				}
			}
			
			//값 두개 선택
			int max = -1;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= (profit[0].length - 1); j++) {
					int p1 = profit[i][j];
					
					for (int m = i; m <= N; m++) {
						for (int n = 1; n < profit[0].length; n++) {
							if (i == m && n < (j + M)) {
								continue;
							}
							if (p1 + profit[m][n] >= max) {
								max = p1 + profit[m][n];
							}
						}
					}
				}
			}
			
			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(max);
		}
	}
	
	static int selectMax(int curr, int rest, int[] map) { // [curr ..]의 arr에서 구할 수 있는 최대 제곱합
		if (curr > M || rest <= 0) {
			return 0;
		}
		
		//선택 x
		int res = selectMax(curr + 1, rest, map);
		
		//선택
		if (rest >= map[curr]) {
			res = Math.max(res, res = map[curr] * map[curr] + selectMax(curr + 1, rest - map[curr], map));
		}
		
		return res;
	}
}