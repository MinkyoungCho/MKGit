/*
 * Stack 직접 구현한 DFS
 * 5.001s --> TLE!!
 */

package CG_Study;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

class Solution4 {
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

			int max = -1;

			Stack<Case> stack = new Stack<>();
			stack.push(new Case(0, K, 0));

			while (!stack.isEmpty()) {
				Case current = stack.pop();
				if (current.N == N || current.K == 0) {
					if (current.sum > max) {
						max = current.sum;
					}
					continue;
				}
				
				// 자식 PUSH
				stack.push(new Case(current.N + 1, current.K - 1, current.sum + courses[current.N])); // 선택 O
				stack.push(new Case(current.N + 1, current.K, current.sum)); // 선택 X
				
			}

			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(max);
		}
	}
	
	static class Case {
		int N;
		int K;
		int sum;
		
		Case(int N, int K, int sum) {
			this.N = N;
			this.K = K;
			this.sum = sum;
		}
	}
}