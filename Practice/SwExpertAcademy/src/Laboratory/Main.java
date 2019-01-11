package Laboratory;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static int N, M, Answer;
	static ArrayList<Point> virus;
	static int[][] map;
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int spreadVirus(Point w1, Point w2, Point w3) {
		int res = 0;
		int[][] tempMap = new int[N + 1][M + 1];
		for (int i = 0; i < tempMap.length; i++) {
			tempMap[i] = Arrays.copyOf(map[i], map[i].length);
		}
		
		tempMap[w1.x][w1.y] = 1;
		tempMap[w2.x][w2.y] = 1;
		tempMap[w3.x][w3.y] = 1;
		
		Queue<Point> q = new LinkedList<>();
		for (Point p : virus) {
			q.add(p);
		}
		
		//virus spread
		while (!q.isEmpty()) {
			Point curr = q.remove();
			int x = curr.x, y = curr.y;
			
			if (x - 1 > 0 && tempMap[x - 1][y] == 0) {
				tempMap[x - 1][y] = 2;
				q.add(new Point(x - 1, y));
			}
			if (y - 1 > 0 && tempMap[x][y - 1] == 0) {
				tempMap[x][y - 1] = 2;
				q.add(new Point(x, y - 1));
			}
			if (x + 1 <= N && tempMap[x + 1][y] == 0) {
				tempMap[x + 1][y] = 2;
				q.add(new Point(x + 1, y));
			}
			if (y + 1 <= N && tempMap[x][y + 1] == 0) {
				tempMap[x][y + 1] = 2;
				q.add(new Point(x, y + 1));
			}
		}
		
		//count safe place
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (tempMap[i][j] == 0) {
					res ++;
				}
			}
		}

		return res;
	}

	public static void main(String args[]) throws Exception	{
		long start = System.currentTimeMillis();
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N + 1][M+ 1];
			virus = new ArrayList<>();
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					map[i][j] = sc.nextInt();
					
					if (map[i][j] == 2) {
						virus.add(new Point(i, j));
					}
				}
			}
			
			Point wall1, wall2, wall3;
			int max = -1, res;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (map[i][j] != 0) {
						continue;
					}
					wall1 = new Point(i, j);
					
					for (int i2 = i; i2 <= N; i2++) {
						for (int j2 = 1; j2 <= M; j2++) {
							if (map[i2][j2] != 0 || ((i2 == i) && (j2 <= j))) {
								continue;
							}
							wall2 = new Point(i2, j2);
							
							for (int i3 = i2; i3 <= N; i3++) {
								for (int j3 = 1; j3 <= M; j3 ++) {
									if (map[i3][j3] != 0 || ((i3 == i2) && (j3 <= j2))) {
										continue;
									}
									wall3 = new Point(i3, j3);
									max = Math.max(max, spreadVirus(wall1, wall2, wall3));
								}
							}
						}
					}
				}
			}
			
			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(max);
		}
		long end = System.currentTimeMillis();
		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
	}
}