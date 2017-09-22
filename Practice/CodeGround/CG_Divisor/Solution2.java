/*
 * 고치려고 노력... but 0점 ...
 * 
 */
package CG_Divisor;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

class Solution2 {
	static int Answer;

	public static void main(String args[]) throws Exception	{

		Scanner sc = new Scanner(System.in);
  		//  Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		int N, M, b, l, r;
		int[] numbers;
		
		for(int test_case = 0; test_case < T; test_case++) {
		    System.out.println("Case #"+(test_case+1));
			N = sc.nextInt();
			M = sc.nextInt();

			numbers = new int[N + 1];
			for (int i = 1; i < N + 1; i++) {
				numbers[i] = sc.nextInt();
			}
			
			int count = 0;
			
			for (int line = 0; line < M; line++) {
				b = sc.nextInt();
				l = sc.nextInt();
				r = sc.nextInt();
				ArrayList<Integer> divisors = new ArrayList<>();
				
				// b의 약수 구하기 -> 약수라면 divisor가 될수있는지 check
				for (int i = 1 ; i <= Math.sqrt(b); i++) {
					if (b % i == 0) { // 약수
						int divisor = b / i;
						boolean flag = false;
						if (!divisors.isEmpty()) {
							for (int item : divisors) {
								if (item % divisor == 0) {
									break;
								} else {
									for (int j = l; j <= r; j++) {
							 			if (numbers[j] >= divisor && numbers[j] % divisor == 0) {
							 				divisors.add(divisor);
							 				flag = true;
							 				break;
							 			} 
							 			if (j == r) {
							 				count ++;
							 			}
									}
									
									if (flag) {
										break;
									}
								}
							}
						} else {
							for (int j = l; j <= r; j++) {
					 			if (numbers[j] >= divisor && numbers[j] % divisor == 0) {
					 				divisors.add(divisor);
					 				break;
					 			} 
					 			if (j == r) {
					 				count ++;
					 			}
							}
						}
						if (i == 1 || i == Math.sqrt(b)) {
				 			continue;
				 		}
						
						flag = false;
				 		divisor = i; 
				 		if (!divisors.isEmpty()) {
							for (int item : divisors) { // read
								if (item % divisor == 0) { 
									break;
								} else {
									for (int j = l; j <= r; j++) {
							 			if (numbers[j] >= divisor && numbers[j] % divisor == 0) {
							 				divisors.add(divisor); // write
							 				flag = true;
							 				break;
							 			} 
							 			if (j == r) {
							 				count ++;
							 			}
									}
									
									if (flag) {
										break;
									}
								}
							}
						} else {
							for (int j = l; j <= r; j++) {
					 			if (numbers[j] >= divisor && numbers[j] % divisor == 0) {
					 				divisors.add(divisor);
					 				break;
					 			} 
					 			if (j == r) {
					 				count ++;
					 			}
							}
						}
				 		
					} 
				}
			}
			
// 			Print the answer to standard output(screen).
			System.out.println(count);
		}
	}
}