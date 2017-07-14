package string_programming;

import java.util.Scanner;

class StringToNumber {
	String string;
	int integer;
	double inputDouble;

	public int stringToInteger(String input) {
		int result = 0;
		
		for (int i = 0; i < input.length(); i++) { // - '0' !!, ÀÎµ¦½ºÃ³¸® Á¶½É!
			result = result * 10 + (input.charAt(i) - '0');
		}
		
		return result;
	}
	
	public double stringToDouble(String input){
		double result = 0;
		String[] inputs = input.split("[.]"); // Çæ!!!!

		for (int j = 0; j < inputs[0].length(); j++) {
			result = 10 * result + (inputs[0].charAt(j) - '0');
		}
		
		int afterPoint = 0;
		int numberOfPoints = 1;
	
		double pointResult = 0.0;
		
		for (int j = 0; j < inputs[1].length(); j++) {
			afterPoint = 10 * afterPoint + (inputs[1].charAt(j) - '0');
			numberOfPoints *= 10;
		}
		
		pointResult = afterPoint / (double) numberOfPoints;

		result += pointResult;
		
		return result;
	}
}

public class NumAndString {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringToNumber stringToNumber = new StringToNumber();
		
		String input = scanner.next();
		double outputDouble = stringToNumber.stringToDouble(input);
		System.out.println(outputDouble);
	}
}


