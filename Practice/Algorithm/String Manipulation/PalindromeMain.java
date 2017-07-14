package string_programming;

import java.util.Scanner;

class Palindrome {
	String input;

	public Palindrome(String input) {
		this.input = input;
	}
	
	int i;
	int j;
	
	public void checkString() {
		for (i = 1; i < (input.length() - 1); i++) {
			for (j = 1; j <= ((input.length() - 1) / 2); j++) {
				if (((i - j) >= 0) && ((i + j) < input.length())) {
					if (input.charAt(i - j) == input.charAt(i + j)) {
							String result = "";

							for (int k = j; k >= 0; k--) {
								result = result + input.charAt(i - k);
							}
							for (int k = 1; k <= j ; k++) {
								result = result + input.charAt(i + k);
							}
							System.out.println(result + " ");
						
					} else {
						break;
					}
				}
			}			
		}
	}
}

public class PalindromeMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testNum = scanner.nextInt();
		String garbage = scanner.nextLine();

		for (int i = 0; i < testNum; i++) {
			String input = scanner.nextLine();
			Palindrome palindrome = new Palindrome(input);
			palindrome.checkString();
			;
		}
	}
}