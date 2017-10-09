package Film;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution2 {
	static int Answer;
	static int D, W, K;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			int[][] map = new int[D + 1][W + 1];
			
			for (int i = 1; i <= D; i++) {
				for (int j = 1; j <= W; j++) {
					map[i][j] = sc.nextInt(); // 0: A, 1: B
				}
			}
			
			if (passTest(map)) {
				System.out.println("success: ");

				System.out.print("#" + (test_case+1) + " ");
				System.out.println(0);
				return;
			}
			
			Queue<Film> queue = new LinkedList<>();
			for (int i = 1; i <= D; i++) {
				queue.add(new Film(i, 0, map, 1));
				queue.add(new Film(i, 1, map, 1));
			}
			
			while (!queue.isEmpty()) {
				Film curr = queue.remove();
				int depth = curr.depth, mode = curr.mode, numOfFilms = curr.numOfFilms;
				int[][] tempMap = new int[D + 1][W + 1];
				for (int i = 1; i <= D; i++) {
					if (i != depth) {
						tempMap[i] = Arrays.copyOf(map[i], map[i].length);
					} else {
						Arrays.fill(tempMap[i], mode);
					}
				}
				if (passTest(tempMap)) {
					Answer = numOfFilms;
					break;
				} else {
					for (int i = depth + 1; i <= D; i++) {
						queue.add(new Film(i, 0, tempMap, numOfFilms + 1));
						queue.add(new Film(i, 1, tempMap, numOfFilms + 1));
					}
				}
			}

		}
	}

	static boolean passTest(int[][] map) {
		for (int i = 1; i <= W; i++) {
			int[] count = new int[2]; // 0: A, 1: B
			int last = map[1][i];
			int tempCount = 1;

			for (int j = 2; j <= D; j++) {
				if (map[j][i] == last) {
					tempCount ++;
					
					if (tempCount > count[last]) {
						count[last] = tempCount;
					}
				} else {
					if (tempCount > count[last]) {
						count[last] = tempCount;
					}
					last = map[j][i];
					tempCount = 1;
				}
				
			}
			if (count[0] < K && count[1] < K) {
				return false;
			}
		}
		
		return true;
	}
	
	static class Film { // 몇번째 depth에 어떤 mode(A or B)의 보호막을 씌울것인가
		int depth;
		int mode;
		int[][] tempMap = new int[D][W];
		int numOfFilms;
		
		Film(int depth, int mode, int[][] tempMap, int numOfFilms) {
			this.depth = depth;
			this.mode = mode;
			this.tempMap = tempMap;
			this.numOfFilms = numOfFilms;
		}
		
	}
}