/*
 * heap 메모리 부족
 * dp = new int[N][K + 1];
 * 
 */
package CG_Study;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Solution2 {
	static int Answer;
	static int N, K;
	static int[] courses;
	static int[][] dp; 
	
	public static void main(String args[]) throws Exception	{

//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			courses = new int[N];
			for (int i = 0; i < N; i++) {
				courses[i] = sc.nextInt();
			}
			dp = new int[N][K + 1];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], -1);
			}
			
			Answer = selectCourses(0, K);

			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
	
	static int selectCourses(int index, int remainder) { // (index ...) 중에서 remainder만큼 뽑을때 얻을 수 있는 최대 합
		if ((index == N) || (remainder == 0)) {
			return 0;
		}
		
		if (dp[index][remainder] > -1) {
			return dp[index][remainder];
		}
		
		int res = courses[index] + selectCourses(index + 1, remainder - 1); // 선택
		res = Math.max(res, selectCourses(index + 1, remainder)); // 선택 x
		
		dp[index][remainder]= res;
		return res;
	}
}