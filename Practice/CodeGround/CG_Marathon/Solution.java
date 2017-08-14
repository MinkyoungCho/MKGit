/**
 * (0, 0)에서 시작 --> 가지 뻗어나가기 (2^n)
 */
package CG_Marathon;

import java.io.FileInputStream;
import java.util.PriorityQueue;
import java.util.Scanner;

class Solution {
	static int M, N, K;
	static int min;
	static int[][] matrix;
	static int[][] cache;
	static PriorityQueue<Integer> minheap;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			min = Integer.MAX_VALUE;
			N = sc.nextInt(); // 열 개
			M = sc.nextInt();
			K = sc.nextInt();
			
			matrix = new int[M + 1][N + 1];
			minheap = new PriorityQueue<>();
			
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
			
			goNext(M + N, 0, 0, 0, 0);
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(minheap.poll());
		}
	}
	
	static void goNext(int count, int x, int y, int water, int sumOfDiff) {
		int min = Integer.MAX_VALUE;
		
		if (count == 0) {
			if (x == M && y == N && water >= K) {
				minheap.add(sumOfDiff);
			}
			return;
		}
		
		int latitude = Math.abs(matrix[x][y]);
		
		if (matrix[x][y] < 0) {
			water++;
		}
		
		
		// 오른쪽
		if (y < N && count  == M - x + N - y) {
			goNext(count - 1, x, y + 1, water, sumOfDiff + Math.abs(latitude - Math.abs(matrix[x][y + 1])));
		}
		
		// 아래
		if (x < M  && count == M - x + N - y) {
			goNext(count - 1, x + 1, y, water, sumOfDiff + Math.abs(latitude - Math.abs(matrix[x + 1][y])));
		}
		

	}
}