/*
 * DFS + MEMO: Stack Overflow!
 * Stack 용량이 1MB인경우 Stack overflow위험 높음
 */

package CG_SteppingStone;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution2 {
	static int Answer;
	static int N, K, L;
	static int[][] dp;
	static boolean[] obstacles;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			L = sc.nextInt();
			obstacles = null;
			dp = new int[N + 1][K + 1];
			Answer = 0;

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
						Answer += dfs(N - i, i);
					} else {
						if (!(obstacles[i])) {
							Answer += dfs(N - i, i);
						}
					}
				}
			}
			
			for (int i = 0; i < dp.length; i++) {
				for (int j = 0; j < dp[0].length; j++) {
					System.out.print(dp[i][j] + " ");
				}
				System.out.println();
			}

			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
	
	static int dfs(int currentStone, int currentK) {
		if (currentStone == 0) {
			dp[currentStone][currentK] = 1;
			return 1;
		}
		
		if (dp[currentStone][currentK] > 0) {
			return dp[currentStone][currentK];
		}
		
		int res = 0;
		for (int i = 1; i <= K; i++) {
			if ((i != currentK) && (currentStone - i > -1)) {
				if (L == 0) {
					res += dfs(currentStone - i, i);
				} else {
					if (!(obstacles[currentStone - i])) {
						res += dfs(currentStone - i, i);
					}
				}
			}
		}
		
		dp[currentStone][currentK] = res;
		return res;
	}
}