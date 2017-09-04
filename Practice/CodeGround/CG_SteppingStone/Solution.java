/*
 * DFS(화살쏘기): TLS(2.00105s)
 * 화살쏘기 backtracking은 하지말자 --> TLS날 가능성 매우매우 높아서 풀고도 0점 ㅠㅠ
 */
package CG_SteppingStone;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	static int Answer;
	static int N, K, L;
	static int count = 0; // static 인 경우 TC끼리섞이지 않도록 초기화 꼬박꼬박!
	static boolean[] obstacles;

	public static void main(String args[]) throws Exception	{
		Scanner sc = new Scanner(System.in);
// 		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			L = sc.nextInt();
			obstacles = null;
			count = 0;

			if (L > 0) {
				obstacles = new boolean[N + 1];
				for (int i = 0; i < L; i++) {
					int temp = sc.nextInt();
					obstacles[temp] = true;
				}
			}
			
			for (int i = 1; i <= K; i++) {
				if ((i <= N)) {
					if (L == 0) {
						dfs(i, i);
					} else {
						if (!(obstacles[i])) {
							dfs(i, i);
						}
					}
				}
			}

			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(count);
		}
	}
	
	static void dfs(int currentStone, int currentK) {
		if (currentStone == N) {
			count++;
			return;
		}
		
		for (int i = 1; i <= K; i++) {
			if ((i != currentK) && (currentStone + i <= N)) {
				if (L == 0) {
					dfs(currentStone + i, i);
				} else {
					if (!(obstacles[currentStone + i])) {
						dfs(currentStone + i, i);
					}
				}
			}
		}
	}
}