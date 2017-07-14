package string_programming;

import java.util.Scanner;

class NumberToString {
	int caseCount = 0;
	String result= "";
	
	public char changeToAlphabet(int num) {
		return (char) (num - 1 + 'a');
	}
	
	public void findAllCase(String input, String result) {
		if (input.equals("")) { //null과 빈 스트링은 다른것!!!
			caseCount ++;	
			System.out.println("# " + result);
			return;
		} else { //할당 함부로 하지 않기!!!
			if (input.length() >= 2) {
				String temp = result + changeToAlphabet(Integer.parseInt("" + input.charAt(0) + input.charAt(1)));
				findAllCase(input.substring(2), temp);
			} 
			
			String temp = result + changeToAlphabet(input.charAt(0) - '0');	//char과 Integer사이에 업캐스팅 주의
			findAllCase(input.substring(1), temp);
		}
	}
	
}

public class TraslatingMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String input = scanner.next();
		NumberToString numberToString = new NumberToString();
		numberToString.findAllCase(input, "");
		System.out.println("Total count: " + numberToString.caseCount);
	}

}
