/*
 * Stack Overflow Error
 * 
 */
package CG_Study;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;

class Solution3 {
	static int Answer;
	static int N, K;
	static int[] courses;
	static HashMap<Integer, Integer>[] dp; 
	
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
			dp = new HashMap[N];
			for (int i = 0; i < N; i++) {
				dp[i] = new HashMap<>();
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
		
		int res;
		if ((!dp[index].isEmpty()) && dp[index].get(remainder) != null) {
			return dp[index].get(remainder);
		}
		
		res = courses[index] + selectCourses(index + 1, remainder - 1); // 선택
		res = Math.max(res, selectCourses(index + 1, remainder)); // 선택 x
		
		dp[index].put(remainder, res);
		return res;
	}
}