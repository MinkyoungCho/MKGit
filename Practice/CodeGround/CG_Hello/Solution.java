package CG_Hello;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	static int Answer;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		String gar = sc.nextLine();
		
		for(int test_case = 0; test_case < T; test_case++) {
			String stmt = sc.nextLine();
			
			int[] hello = new int[4];
			
			for (int i = 0;  i < stmt.length(); i++) {
				char current = stmt.charAt(i);

				if (current == 'h') {
					hello[0] ++;
				} else if (current == 'e') {
					hello[1] ++;
				} else if (current == 'l') {
					hello[2] ++;
				} else if (current == 'o') {
					hello[3] ++;
				}
			}
			
			hello[2] /= 2;
			
			Answer = Integer.MAX_VALUE;
			for (int i = 0; i < hello.length; i++) {
				if (hello[i] < Answer) {
					Answer = hello[i];
				}
			}
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}