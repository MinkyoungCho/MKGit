// ���� �ε����� ��� ȣ���� �� ���� üũ ����!!
package Hope;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Main {
	static int N, Answer;
	static int[][] map;
	static int[] dp;
	
	static int dfs(int date) { //�ش� ��¥���� ���������� ���� �� �ִ� �ִ� ����
		if (date == N + 1) {
			return 0;
		}
		
		if (dp[date] >= 0) {
			return dp[date]; 
		}
		
		int res = -1;
		
		//�������� �� ����
		if (date + map[date][0] <= N + 1) { 
			res = map[date][1] + dfs(date + map[date][0]);
		}
		
		//���� x�� �� ����
		res = Math.max(res, dfs(date + 1));
		
		// ���� �ִ� �� ����
		dp[date] = res;
		return res;
	}
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			Answer = 0;
			N = sc.nextInt();
			map = new int[N + 1][2];
			dp = new int[N + 1];
			Arrays.fill(dp, -1);
			
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < 2; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			Answer = dfs(1); //{1...} �Ⱓ���� ���� �� �ִ� �ִ� ����
			
			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(Answer);
		}
	}
}