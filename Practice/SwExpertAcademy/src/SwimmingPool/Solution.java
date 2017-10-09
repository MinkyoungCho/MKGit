package SwimmingPool;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Solution {
	static int Answer;
	static int[] cost = new int[4];
	static int[] plan = new int[13];
	static int[] dp = new int[13];

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			for (int i = 0; i < 4; i++) {
				cost[i] = sc.nextInt();
			}
			for (int i = 1; i <= 12; i++) {
				plan[i] = sc.nextInt();
			}
			
			Arrays.fill(dp, -1);
 
			Answer = findMax(1);
			// Print the answer to standard output(screen).
			System.out.print("#"+(test_case+1) + " ");
			System.out.println(Answer);
		}
	}
	
	static int findMax(int index) { // [index ..]의 기간에서 가능한 최소 비용 구하기
		if (index > 12) {
			return 0;
		}
		
		if (dp[index] > -1) {
			return dp[index];
		}
		
		int res = Integer.MAX_VALUE;
		
		//1일
		res = cost[0] * plan[index] + findMax(index + 1);
		
		//1달
		res = Math.min(res, cost[1] + findMax(index + 1));
		
		//3달
		res = Math.min(res, cost[2] + findMax(index + 3));
		
		//1년
		if (index == 1) {
			res = Math.min(res, cost[3]);
		}
		
		dp[index] = res;
		return res;
	}
}