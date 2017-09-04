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
			
			int[][] dp = new int[N + 1][K + 1]; // �ش� index�� �ش� step���� �����ϴ� ����� �� 
			int[] count = new int[N + 1]; // �ش� index�� �����ϴ� ��� ����� �� 
			
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < K + 1; j++) {
					if (L > 0 && bombs[i]) { // ���� ��ġ�� ��
						break;
					}
					if (i < j) {
						break;
					}
					if (i == j) {
						dp[i][j] = 1;
						
					} else {
						dp[i][j] = (count[i - j] - dp[i - j][j] + MOD) % MOD; // '-' ������ ��� ���� ������ ���� �⺻������ '+ MOD' ����
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