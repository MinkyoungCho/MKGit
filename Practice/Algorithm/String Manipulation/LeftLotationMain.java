package string_programming;

import java.util.Scanner;

public class LeftLotationMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		int index = scanner.nextInt();
		
		String temp = input.substring(0, index);
		System.out.println(input.substring(index) + temp);		
	}

}
