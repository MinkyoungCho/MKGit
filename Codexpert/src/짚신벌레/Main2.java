package Â¤½Å¹ú·¹;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int d = sc.nextInt();
		int N = sc.nextInt();
		
		int[] arrOfInsects = new int[N + 1];
		arrOfInsects[0] = 1;
		
		for (int i = 1; i < a; i++) {
			arrOfInsects[i] = 0;
		}
		
		for (int i = a; i <= N; i++) {
			arrOfInsects[i] = arrOfInsects[i - a];
			if (a != i) {
				arrOfInsects[i] = arrOfInsects[i - 1] + arrOfInsects[i];
			}
			
			if (i - b >= 0) arrOfInsects[i] = arrOfInsects[i] - arrOfInsects[i - b];
			arrOfInsects[i] += 1000;
			arrOfInsects[i] &= 1000;
		}
		
		int count = 0;
		
		for (int i = N; i > N - d; i--) {
			count += arrOfInsects[i];
		}
		count = count % 1000;
		
		System.out.println(count);
	}

}
