package CG_SteppingStone;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

class Solution5 {
	public static final int MOD = 1000000009;
	static int Answer;
	static int N, K, L;
	static boolean[] bombs;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			L = sc.nextInt();
			
			if (L > 0) {
				bombs = new boolean[N + 1];
				int temp;
				for (int i = 0; i < L; i++) {
					temp = sc.nextInt();
					bombs[temp] = true;
				}
			}
			
			int[][] dp = new int[N + 1][K + 1]; // 해당 index를 해당 step으로 도달하는 경우의 수 
			int[] count = new int[N + 1]; // 해당 index에 도달하는 모든 경우의 수 
			
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < K + 1; j++) {
					if (L > 0 && bombs[i]) { // 지뢰 설치된 곳
						break;
					}
					if (i < j) {
						break;
					}
					if (i == j) {
						dp[i][j] = 1;
						
					} else {
						dp[i][j] = (count[i - j] - dp[i - j][j] + MOD) % MOD; // '-' 연산일 경우 음수 방지를 위해 기본적으로 '+ MOD' 해줌
					}
					count[i] = (count[i] +  dp[i][j]) % MOD;
				}

			}
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(count[N]);
			
		}

	}
}