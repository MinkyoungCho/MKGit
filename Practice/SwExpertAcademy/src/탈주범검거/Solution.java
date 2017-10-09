package Å»ÁÖ¹ü°Ë°Å;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution {
	static int Answer;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int R = sc.nextInt();
			int C = sc.nextInt();
			int time = sc.nextInt();
			int[][] map = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			Queue<Point> queue = new LinkedList<>();
			queue.add(new Point(R, C, 1));
			Answer = 0;
			boolean[][] mark = new boolean[N][M];
			
			while (!queue.isEmpty()) {
				Point curr = queue.remove();
				int x = curr.x, y = curr.y, level = curr.level;
				if (level > time) {
					break;
				}
			
				if (mark[x][y]) {
					continue;
				} 
				mark[x][y] = true;
				Answer ++;
				
				if (map[x][y] == 1) { // »óÇÏÁÂ¿ì
					if (x - 1 >= 0 && (map[x - 1][y] == 1 || map[x - 1][y] == 2 || map[x - 1][y] == 5 || map[x - 1][y] == 6)) { // »ó
						queue.add(new Point(x - 1, y, level + 1));
					}
					if (x + 1 < N && (map[x + 1][y] == 1 || map[x + 1][y] == 2 || map[x + 1][y] == 4 || map[x + 1][y] == 7)) { // ÇÏ
						queue.add(new Point(x + 1, y, level + 1));
					}
					if (y - 1 >= 0 && (map[x][y - 1] == 1 || map[x][y - 1] == 3 || map[x][y - 1] == 4 || map[x][y - 1] == 5)) { // ÁÂ
						queue.add(new Point(x, y - 1, level + 1));
					}
					if (y + 1 < M && (map[x][y + 1] == 1 || map[x][y + 1] == 3 || map[x][y + 1] == 6 || map[x][y + 1] == 7)) { // ¿ì
						queue.add(new Point(x, y + 1, level + 1));
					}
				} else if (map[x][y] == 2) { // »óÇÏ
					if (x - 1 >= 0 && (map[x - 1][y] == 1 || map[x - 1][y] == 2 || map[x - 1][y] == 5 || map[x - 1][y] == 6)) { // »ó
						queue.add(new Point(x - 1, y, level + 1));
					}
					if (x + 1 < N && (map[x + 1][y] == 1 || map[x + 1][y] == 2 || map[x + 1][y] == 4 || map[x + 1][y] == 7)) { // ÇÏ
						queue.add(new Point(x + 1, y, level + 1));
					}
					
				} else if (map[x][y] == 3) { // ÁÂ¿ì
					if (y - 1 >= 0 && (map[x][y - 1] == 1 || map[x][y - 1] == 3 || map[x][y - 1] == 4 || map[x][y - 1] == 5)) { // ÁÂ
						queue.add(new Point(x, y - 1, level + 1));
					}
					if (y + 1 < M && (map[x][y + 1] == 1 || map[x][y + 1] == 3 || map[x][y + 1] == 6 || map[x][y + 1] == 7)) { // ¿ì
						queue.add(new Point(x, y + 1, level + 1));
					}
					
				} else if (map[x][y] == 4) { // »ó¿ì
					if (x - 1 >= 0 && (map[x - 1][y] == 1 || map[x - 1][y] == 2 || map[x - 1][y] == 5 || map[x - 1][y] == 6)) { // »ó
						queue.add(new Point(x - 1, y, level + 1));
					}
					if (y + 1 < M && (map[x][y + 1] == 1 || map[x][y + 1] == 3 || map[x][y + 1] == 6 || map[x][y + 1] == 7)) { // ¿ì
						queue.add(new Point(x, y + 1, level + 1));
					}
				} else if (map[x][y] == 5) { // ÇÏ¿ì
					if (x + 1 < N && (map[x + 1][y] == 1 || map[x + 1][y] == 2 || map[x + 1][y] == 4 || map[x + 1][y] == 7)) { // ÇÏ
						queue.add(new Point(x + 1, y, level + 1));
					}
					if (y + 1 < M && (map[x][y + 1] == 1 || map[x][y + 1] == 3 || map[x][y + 1] == 6 || map[x][y + 1] == 7)) { // ¿ì
						queue.add(new Point(x, y + 1, level + 1));
					}
				} else if (map[x][y] == 6) { // ÇÏÁÂ
					if (x + 1 < N && (map[x + 1][y] == 1 || map[x + 1][y] == 2 || map[x + 1][y] == 4 || map[x + 1][y] == 7)) { // ÇÏ
						queue.add(new Point(x + 1, y, level + 1));
					}
					if (y - 1 >= 0 && (map[x][y - 1] == 1 || map[x][y - 1] == 3 || map[x][y - 1] == 4 || map[x][y - 1] == 5)) { // ÁÂ
						queue.add(new Point(x, y - 1, level + 1));
					}
				} else if (map[x][y] == 7){ // »óÁÂ
					if (x - 1 >= 0 && (map[x - 1][y] == 1 || map[x - 1][y] == 2 || map[x - 1][y] == 5 || map[x - 1][y] == 6)) { // »ó
						queue.add(new Point(x - 1, y, level + 1));
					}
					if (y - 1 >= 0 && (map[x][y - 1] == 1 || map[x][y - 1] == 3 || map[x][y - 1] == 4 || map[x][y - 1] == 5)) { // ÁÂ
						queue.add(new Point(x, y - 1, level + 1));
					}
				}
				
				
			}
			
			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(Answer);
		}
	}
	
	static class Point {
		int x, y, level;
		Point(int x, int y, int level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}
	}
}