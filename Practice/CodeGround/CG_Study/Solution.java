/*
 * Pass!
 */

package CG_Study;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Solution {	
	static int N, K;
	static int[] courses;
	static int Answer = 0;

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

			Arrays.sort(courses);
			for (int i = 1; i <= K; i++) {
				Answer += courses[N - i];
			}
			

			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}