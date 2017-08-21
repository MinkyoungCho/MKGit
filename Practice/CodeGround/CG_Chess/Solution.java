/*
 * Bottom up - DP
 */

package CG_Chess;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Solution {
	static int Answer;
	static ArrayList<Obstacle> obstacles;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			int step = 1000;
			
			int col = Math.max(N, M);
			int[][] matrix = new int[2][col];
			Arrays.fill(matrix[0], 1);
			matrix[1][0] = 1;
			
			obstacles = new ArrayList<>();

			int row;
			if (col == N) { // ������ ����
				row = M;
				for (int i = 0; i < K; i++) {
					int a = sc.nextInt();
					int b = sc.nextInt();
					if (b < row && a < col) {
						if (b > 1) {
							obstacles.add(new Obstacle(b - 1, a - 1));
						} else {
							matrix[0][a - 1] = 0;
						}
					} 
				}
			} else { // col == M(��������)
				row = N;
				for (int i = 0; i < K; i++) {
					int a = sc.nextInt();
					int b = sc.nextInt();
					if (a < row && b < col) {
						if (a > 1) {
							obstacles.add(new Obstacle(a - 1, b - 1));
						} else {
							matrix[0][b - 1] = 0;
						}
					} 
				}				
			}
			
			Collections.sort(obstacles);
			
			// ��ֹ� Ȯ���ϸ鼭 ĭ ä��� -> ���ٷ� �̵�
			for (int iter = 0; iter < (row / step); iter++) {
				for (int i = 1; i < row; i++) { 
					if (hasObstacle(i, 0)) {
						matrix[1][0] = 0;
					} else {
						matrix[1][0] = 1;
					}
					
					for (int j = 1; j < col; j++) {
						if (hasObstacle(i, j)) {
							matrix[1][j] = 0;
							continue;
						}
						matrix[1][j] = (matrix[0][j] + matrix[1][j - 1]);
						if (matrix[1][j] > 1000000006) {
							matrix[1][j] = (matrix[0][j] + matrix[1][j - 1]) % 1000000007;
						}
					}
					
//					System.arraycopy(src, srcPos, dest, destPos, length);
				}
			}
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(matrix[1][col - 1]);
//			return;
		}
	}
	
	static boolean hasObstacle(int x, int y) { // obstacle Ž�� - Binary Search
		int first = 0;
		int last = obstacles.size() - 1;
		int mid;
		
		while (first <= last) {
			mid = (first + last) / 2;
//			System.out.println(mid);

			Obstacle temp = obstacles.get(mid);
			
			if (temp.x == x) {
				if (temp.y == y) {
					return true;
				} else if (y > temp.y) {
					first = mid + 1;
				} else {
					last = mid - 1;
				}
			} else if (x > temp.x) {
				first = mid + 1;
			} else {
				last = mid - 1;
			}
		}
		
		return false;
	}
	
	static class Obstacle implements Comparable<Obstacle> {
		int x;
		int y;
		
		Obstacle(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Obstacle o) {
			if (this.x != o.x) {
				return this.x - o.x; // ����
			} else {
				return this.y - o.y; // ����
			}
		}
		
		
	}
}