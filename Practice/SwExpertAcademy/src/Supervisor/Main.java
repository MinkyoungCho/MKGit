package Supervisor;

import java.io.FileInputStream;
import java.util.Scanner;

class Main {
	static int N, Answer, main, sub;
	static int[] map;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));
		
		N = sc.nextInt();
		map = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = sc.nextInt();
		}
	
		Answer = 0;
		main = sc.nextInt();
		sub = sc.nextInt();
		
		for (int i = 1; i <= N; i++) {
			int temp = map[i];
			temp = temp - main;
			Answer ++;
			if (temp > 0) {
				Answer += Math.ceil(Math.ceil((double)temp /  sub));
			}
		}
		
		System.out.println(Answer);
		}
}