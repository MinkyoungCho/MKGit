package 등산로;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 마크 & 백업 신경쓰기
class Solution {
	static int Answer;
	static int N;
	static int K;

	public static void main(String args[]) throws Exception	{

//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			int[][] map = new int[N + 1][N + 1];
			boolean[][] mark = new boolean[N + 1][N + 1];
			int max = -1;
			Queue<Point> queue = new LinkedList<>();
			for (int i = 1; i < (N + 1); i++) {
				for (int j = 1; j < (N + 1); j++) {
					map[i][j] = sc.nextInt();
					
					if (map[i][j] > max) {
						max = map[i][j];
					}
				}
			}
			
			for (int i = 1; i < (N + 1); i++) {
				for (int j = 1; j < (N + 1); j++) {
					if (map[i][j] == max) {
						queue.add(new Point(i, j));
					}
				}
			}
			
			int res = -1;
			while (!queue.isEmpty()) {
				Point current = queue.remove();
				res = Math.max(res, 1 + findPath(1, current, map, false, mark));
				
			}
				
			// Print the answer to standard output(screen).
			System.out.print("#"+(test_case+1) + " ");
			System.out.println(res);
		}
	}
	
	static int findPath(int num, Point curr, int[][] map, boolean flag, boolean[][] mark) { // t: 이미 깎임찬스 사용, f: 아직 사용 x
//		System.out.println(num + " : " + curr.x + " " + curr.y);
		int x = curr.x, y = curr.y;
		int res = 0;
		int currentVal = map[x][y];
		boolean[][] currentMark = new boolean[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			currentMark[i] = Arrays.copyOf(mark[i], N + 1);
		}
		currentMark[x][y] = true;
		
		if (x - 1 >= 1  && map[x - 1][y] < map[x][y]) {
			res = Math.max(res, 1 + findPath(num + 1, new Point(x -1, y), map, flag, currentMark));
		} else if (x - 1 >= 1 && !mark[x - 1][y] ) {
			if (!flag) {
				if ((map[x - 1][y] - K) < map[x][y]) {
					int[][] tempMap = new int[N + 1][N + 1];
					for (int i = 1; i <= N; i++) {
						tempMap[i] = Arrays.copyOf(map[i], N + 1);
					}
					tempMap[x - 1][y] = tempMap[x][y] - 1;
					
					res = Math.max(res, 1 + findPath(num + 1, new Point(x - 1, y), tempMap, true, currentMark));
				}
			}
		}
		
		if (y - 1 >= 1 && map[x][y - 1] < map[x][y]) {
			res = Math.max(res, 1 + findPath(num + 1, new Point(x, y - 1), map, flag, currentMark));
		} else if (y - 1 >= 1 && !mark[x][y - 1]) {
			if (!flag) {
				if ((map[x][y - 1] - K) < map[x][y]) {
					int[][] tempMap = new int[N + 1][N + 1];
					for (int i = 1; i <= N; i++) {
						tempMap[i] = Arrays.copyOf(map[i], N + 1);
					}
					tempMap[x][y - 1] = tempMap[x][y] - 1;
					res = Math.max(res,  1 + findPath(num + 1, new Point(x, y - 1), tempMap, true, currentMark));
				}
			}
		}
		
		if (x + 1 <= N && map[x + 1][y] < map[x][y]) {
			res = Math.max(res, 1 + findPath(num + 1, new Point(x + 1, y), map, flag, currentMark));
		} else if (x + 1 <= N && !mark[x + 1][y]) {
			if (!flag) {
				if ((map[x + 1][y] - K) < map[x][y]) {
					int[][] tempMap = new int[N + 1][N + 1];
					for (int i = 1; i <= N; i++) {
						tempMap[i] = Arrays.copyOf(map[i], N + 1);
					}
					tempMap[x + 1][y] = tempMap[x][y] - 1;
					res = Math.max(res, 1 + findPath(num + 1, new Point(x + 1, y), tempMap, true, currentMark));
				}
			}
		}
		
		if (y + 1 <= N && map[x][y + 1] < map[x][y]) {
			res = Math.max(res, 1 + findPath(num + 1, new Point(x, y + 1), map, flag, currentMark));
		} else if (y + 1 <= N && !mark[x][y + 1]) {
			if (!flag) {
				if ((map[x][y + 1] - K) < map[x][y]) {
					int[][] tempMap = new int[N + 1][N + 1];
					for (int i = 1; i <= N; i++) {
						tempMap[i] = Arrays.copyOf(map[i], N + 1);
					}
					tempMap[x][y + 1] = tempMap[x][y] - 1;
					res = Math.max(res, 1 + findPath(num + 1, new Point(x, y + 1), tempMap, true, currentMark));
				}
			}
		}
		
		return res;
	}
	
	static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}