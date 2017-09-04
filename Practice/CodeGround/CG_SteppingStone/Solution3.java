/*
 * 화살쏘기 by iterative dfs는?
 * TLE --> 2.00146
 * 
 */

package CG_SteppingStone;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

class Solution3 {
	static int Answer;
	static int N, K, L;
	static boolean[] obstacles;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			L = sc.nextInt();
			int[] dp = new int[N + 1];
			obstacles = null;
			Answer = 0;

			if (L > 0) {
				obstacles = new boolean[N + 1];
				for (int i = 0; i < L; i++) {
					int temp = sc.nextInt();
					obstacles[temp] = true;
				}
			}
			
			Stack<Movement> stack = new Stack<>();
			for (int i = 1; i <= K; i++) {
				if ((L == 0) && (i <= N)) {
					stack.add(new Movement(i, i));
				} else if ((L != 0) && !obstacles[i] && (i <= N)) {
					stack.add(new Movement(i, i)); //아쉬운건 파라미터로 주기
				}
			}
			
			while (!stack.isEmpty()) {
				Movement current = stack.pop();
				if (current.currentStone == N) {
					Answer++;
					continue;
				}
				
				for (int i = 1; i <= K; i++) {
					int candidate = current.currentStone + i;
					if ((L == 0) && (current.numOfMoving != i) && (candidate <= N)) {
						stack.add(new Movement(candidate, i));
					} if ((L > 0) && !obstacles[candidate] && (current.numOfMoving != i) && (candidate <= N)) {
						stack.add(new Movement(candidate, i));
					}
				}
			}
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
	
	static class Movement {
		int currentStone;
		int numOfMoving;
		
		Movement(int currentStone, int numOfMoving) {
			this.currentStone = currentStone;
			this.numOfMoving = numOfMoving;
		}
	}
}