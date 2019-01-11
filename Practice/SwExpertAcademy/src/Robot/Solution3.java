// 반복
package Robot;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution3 {
	static int N, M, Answer;
	static int[][] map;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][M];

			int x = sc.nextInt(), y = sc.nextInt(), dir = sc.nextInt();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			int count = 1;
			map[x][y] = 2;
			
			while (true) { //0: 북, 1: 동, 2: 남, 3: 서
				int i;
				
				for (i = 1; i <= 4; i++) {
					if (dir == 0) {
						dir = 1;
						if (y - 1 >= 0 && map[x][y - 1] == 0) {
							y = y - 1;
							map[x][y] = 2;
							count ++;
							break;
						}
					} else if (dir == 1) {
						dir = 2;
						if (x + 1 < N && map[x + 1][y] == 0) {
							x = x + 1;
							map[x][y] = 2;
							count ++;
							break;
						}
					} else if (dir == 2) {
						dir = 3;
						if (y + 1 < N && map[x][y + 1] == 0) {
							y = y + 1;
							map[x][y] = 2;
							count ++;
							break;
						}
					} else {
						dir = 0;
						if (x - 1 >= 0 && map[x - 1][y] == 0) {
							x = x - 1;							
							map[x][y] = 2;
							count ++;
							break;
						}
						
					}
				}
				
				if (i > 4) {
					boolean flag2 = false;
					
					switch(dir) {
					case 0:
						if (x + 1 < N && map[x + 1][y] == 2) {
							x = x + 1;
							flag2 = true;
							break;
						}
					case 1:
						if (y + 1 < N && map[x][y + 1] == 2) {
							y = y + 1;
							flag2 = true;
							break;
						}
					case 2:
						if (x - 1 >= 0 && map[x - 1][y] == 2) {
							x = x - 1;
							flag2 = true;
							break;
						}
					case 3:
						if (y - 1 >= 0 && map[x][y - 1] == 2) {
							y = y - 1;
							flag2 = true;
							break;
						}

					}
					
					if (!flag2) {
						break;
					}
				}
			}

			System.out.print("#" + (test_case+1) + " ");
			System.out.println(count);
		}
	}
}
