// 다음 인덱스로 재귀 호출할 때 범위 체크 조심!!
package Hope;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Main {
	static int N, Answer;
	static int[][] map;
	static int[] dp;
	
	static int dfs(int date) { //해당 날짜부터 시작했을때 얻을 수 있는 최대 이익
		if (date == N + 1) {
			return 0;
		}
		
		if (dp[date] >= 0) {
			return dp[date]; 
		}
		
		int res = -1;
		
		//선택했을 때 이익
		if (date + map[date][0] <= N + 1) { 
			res = map[date][1] + dfs(date + map[date][0]);
		}
		
		//선택 x일 때 이익
		res = Math.max(res, dfs(date + 1));
		
		// 둘중 최대 값 리턴
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
			
			Answer = dfs(1); //{1...} 기간동안 얻을 수 있는 최대 이익
			
			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(Answer);
		}
	}
}