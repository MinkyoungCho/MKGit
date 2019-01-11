//재귀 호출시 바로리턴! 안그러면 독립적으로 운영되어야할 옆가지에 영향미침
package Robot;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution2 {
	static int N, M, Answer;
	static int[][] map;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

//		int T = sc.nextInt();
		for(int test_case = 0; test_case < 1; test_case++) {
			Answer = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][M];
			int cnt = 0;
			int x = sc.nextInt(), y = sc.nextInt(), dir = sc.nextInt();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			clean(x, y, dir, 0);

			System.out.print("#" + (test_case+1) + " ");
			System.out.println(Answer);
		}
	}
	
	static int clean(int x, int y, int dir, int level) {
		if (map[x][y] == 0) { 
			map[x][y] = 2;
			Answer ++;

		} else if (map[x][y] == 1) {
			return Answer;
		} 
		int i;
		for (i = 0; i < 4; i++) {
			if (checkLeft(x, y, dir)) { // 왼쪽 체크 - 비었다면 그리로 가기
				switch (dir) {
				case 0:
					return clean(x, y - 1, (dir + 1) % 4, level + 1);
//					break;
				case 1:
					return clean(x + 1, y, (dir + 1) % 4, level + 1);
//					break;
				case 2:
					return clean(x, y + 1, (dir + 1) % 4, level + 1);
//					break;
				case 3:
					return clean(x - 1, y, (dir + 1) % 4, level + 1);
//					break;
				}
			} else {
				dir = (dir + 1) % 4; // 왼쪽 회전
			}
		}
		
		if (i == 4) {
			switch (dir) {
			case 0:
//				if (map[x + 1][y] == 2) {
					return clean(x + 1, y, dir, level + 1); // 후진
//				}
//				break;
			case 1:
//				if (map[x][y + 1] == 2) {
					return clean(x, y + 1, dir, level + 1);
//				}
//				break;
			case 2:
//				if (map[x - 1][y] == 2) {
					return clean(x - 1, y, dir, level + 1);
//				}
//				break;
			case 3:
//				if (map[x][y - 1] == 2) {
					return clean(x, y - 1, dir, level + 1);
//				}
//				break;
			}
		}
		return 0;
		
	}
	
	static boolean checkLeft(int x, int y, int dir) {
		switch (dir) {
		case 0:
			if (map[x][y - 1] == 0) {
				return true;
			} else {
				return false;
			}
		case 1:
			if (map[x + 1][y] == 0) {
				return true;
			} else {
				return false;
			}

		case 2:
			if (map[x][y + 1] == 0) {
				return true;
			} else {
				return false;
			}

		case 3:
			if (map[x - 1][y] == 0) {
				return true;
			} else {
				return false;
			}

		}
		
		return false;
	}
}