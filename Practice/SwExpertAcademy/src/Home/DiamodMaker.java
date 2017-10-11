package Home;

import java.util.Scanner;

public class DiamodMaker {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
				
		for (int i = 1; i <= K; i++) { // 이것만 반대로 흘러가게 & 아래 " ", "*" 채우는 로직은 동일!
			for (int j = 1; j <= (K - i); j++) {
				System.out.print(" ");
			}
			
			for (int j = 1; j <= (2 * i - 1); j++) {
				System.out.print("*");
			}
			
			System.out.println();
		}
		
		for (int i = K - 1; i >= 1; i--) {
			for (int j = 1; j <= (K - i); j++) {
				System.out.print(" ");
			}
			
			for (int j = 1; j <= (2 * i - 1); j++) {
				System.out.print("*");
			}
			
			System.out.println();
		}

	}

}
