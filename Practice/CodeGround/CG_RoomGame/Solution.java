/*
 * 실행시간: 2.0012, 메모리: 119712, 점수: 10
 * Backtracking - DFS (화살쏘기 방식)

 * 모든 경우 살펴보고 최대 0개수 선택!
 * Time Limit Exceed (T.T)
 * 
 */
package CG_RoomGame;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	static int Answer;
	static int N;
	static int[][] matrix;
	static int maxCount = -1;
	
	public static void main(String args[]) throws Exception	{

//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			matrix = new int[N][N];
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
				
			dfs(0, 0, 1, 0);
		
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(maxCount);
		}
	}
	
	static void dfs(int x, int y, int value, int count) {
		
		int temp = matrix[x][y] * value;
		int tempCount = countZero(temp);
		count += tempCount;
		while (--tempCount >= 0) {
			temp /= 6;
		}
		
		if (x == N - 1 && y == N - 1) {
			if (count > maxCount) {
				maxCount = count;
			}
			return;
		}
		
		if (x + 1 < N) {
			dfs(x + 1, y, temp, count);
		}
		
		if (y + 1 < N) {
			dfs(x, y + 1, temp, count);
		}
		
		
	}
	
	static int countZero(int a) {
		int count = 0;
		
		while (a % 6 == 0) {
			a /= 6;
			count ++;
		}

		return count;
	}
	
	
}