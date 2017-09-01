/*
 * 시간: 2.00159, 메모리: 469936, 점수: 30
 * Backtracking(화살쏘기)--> Iterative dfs로 구현
 */
package CG_RoomGame;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import CG_RoomGame.Solution2.Score;

public class Solution3 {
	static int Answer;
	static int N;
	static Score[][] matrix;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			matrix = new Score[N + 1][N + 1];
			
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					matrix[i][j] = new Score(sc.nextInt());
				}
			}
			
			Stack<Point> stack = new Stack<>();
			stack.add(new Point(N, N, 0, 0));
			int max = -1;
			
			while (!stack.isEmpty()) { // 일단 dp 제외
				//(1, 1) 에서 current까지 갈때 가능한 모든 path에서의 0의 개수
				Point current = stack.pop();
				
				if (current.x == 1 && current.y == 1) { // leaf
					int temp = Math.min(current.two, current.three);
					if (temp > max) {
						max = temp;
					}
					continue;
				}
				
				if (current.x > 1) { 
					Score next = matrix[current.x - 1][current.y];
					stack.add(new Point(current.x - 1, current.y, current.two + next.two, current.three + next.three));
				}
				if (current.y > 1) {
					Score next = matrix[current.x][current.y - 1];
					stack.add(new Point(current.x, current.y - 1, current.two + next.two, current.three + next.three));
				}
				
			}
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(max);
		}
	}
	
	static class Point { //현재까지 따라가며 쌓은 two, three 개수 저장
		int x, y;
		int two, three;
		
		Point(int x, int y, int two, int three) {
			this.x = x;
			this.y = y;
			this.two = two;
			this.three = three;
		}
	}

}
