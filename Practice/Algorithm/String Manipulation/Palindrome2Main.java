package string_programming;

import java.util.Scanner;

/* 팰린드롬 확인 : end -> center
 * 
 * 
 */
class Palindrome2 {
	String input;
	int[] lengthOfPal;
	
	public Palindrome2(String input) {
		this.input = input;
	}
	
	public boolean isPalindrome(int first, int last) { // end -> center (Not! center -> end)
		for (int i = 0; (first + i) < (last - i); i++) {
			if (input.charAt(first + i) != input.charAt(last - i)) {
				return false;
			}
		}
		return true;
	}
	
	public int countRecursive(int first, int last) {
		if (isPalindrome(first, last)) { // no need to split
			return 0;
		} else { // find minimum split case
			int min = 1000;
			for (int i = first; i < last; i++) {
				int currentResult = countRecursive(first, i) + countRecursive(i + 1, last) + 1; 
				if (min > currentResult) {
					min = currentResult;
				}
			}
			return min;
		}
	}

}

public class Palindrome2Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String input = scanner.next();
		Palindrome2 palindrome = new Palindrome2(input);
		
		System.out.println(1 + palindrome.countRecursive(0, input.length() - 1)); // Chunk count = 1 + split count
		
	}

}
