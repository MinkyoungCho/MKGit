package Dessert;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	static int Answer;
	static int N;
	static int[][] map;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			map = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			Answer = -1;
			int maxX = 0, maxY = 0, maxA = 0, maxB = 0;
			for (int x = 1; x <= (N - 2); x++) {
				for (int y = 2; y <= (N - 1); y++) {
					for (int a = 1; (x + a) <= N && (y - a) >= 1; a++) {
						for (int b = 1; (x + a + b) <= N && (y + b) <= N; b++) {
							if (!hasDuplication(x, y, a, b)) {
								
								Answer = Math.max(Answer, 2 * (a + b));
								maxX = x; maxY = y; maxA = a; maxB = b;
//								System.out.println("x: " + maxX + ", y: " + maxY + ", a: " + maxA + ", b: " + maxB +  ", Ans: " + Answer);

							}
						}
					}
				}
			}
			
			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(Answer);
		}
	}
	
	static boolean hasDuplication(int x, int y, int a, int b) {
		int x1 = x, y1 = y;
		int x2 = x + b, y2 = y + b;
		boolean[] mem = new boolean[101]; // false: 아직 없음, true: 이미 나옴
		
		for (int i = 0; i <= a; i++) {
			if (!mem[map[x1 + i][y1 - i]]) {
				mem[map[x1 + i][y1 - i]] = true;
			} else {
				return true;
			}
			
			if (!mem[map[x2 + i][y2 - i]]) {
				mem[map[x2 + i][y2 - i]] = true;
			} else {
				return true;
			}
		}
		
		x2 = x + a;
		y2 = y - a;
		
		for (int i = 1; i < b; i++) {
			if (!mem[map[x1 + i][y1 + i]]) {
				mem[map[x1 + i][y1 + i]] = true;
			} else {
				return true;
			}
			
			if (!mem[map[x2 + i][y2 + i]]) {
				mem[map[x2 + i][y2 + i]] = true;
			} else {
				return true;
			}
		}
		
		return false;
	}
}