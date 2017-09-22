/*
 * <47��>
 * �׷� 1: 1 <= N, M <= 1000 �� ���ؼ��� �°� 
 * �׷� 2�� Ʋ�� (TLE...)
 * �ð��� ���̷��� ����غ�����... 0��... TLE...
 */
package CG_Divisor;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;

class Solution {
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
				
				// b�� ��� ���ϱ� -> ������ divisor�� �ɼ��ִ��� check
				for (int i = 1 ; i <= Math.sqrt(b); i++) {
					if (b % i == 0) { // ���
						int divisor = b / i;

						for (int j = l; j <= r; j++) {
				 			if (numbers[j] >= divisor && numbers[j] % divisor == 0) {
				 				break;
				 			} 
				 			if (j == r) {
				 				count ++;
				 			}
						}
						
				 		if (i == 1 || i == Math.sqrt(b)) {
				 			continue;
				 		}
						
				 		divisor = i; 
						
				 		for (int j = l; j <= r; j++) {
				 			if (numbers[j] % divisor == 0) {
				 				break;
				 			} 
				 			
				 			if (j == r) {
				 				count ++;
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