/*
 * heap 메모리 부족!
 * [N][N]이 오바다
 */

package CG_Room;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	static int Answer;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			long[][] room = new long[N][N];
			room[0][0] = 1;
		
			int num = 1, lastX = 0, lastY = 0;
			boolean dir = false; // false: 위 -> 아래 , true: 아래 -> 위
		
			//fill the room
			for (int i = 2; i <= N; i++) {
				if (!dir) { // 위 -> 아래
					lastY++;
					lastX = 0;
					room[0][lastY] = ++num;
					
					for (int j = 0; j < (i - 1); j++) {
						room [++lastX][--lastY] = ++num;
					}
					dir = true;
					
				} else {
					lastX ++;
					lastY = 0;
					room[lastX][0] = ++num;
					for (int j = 0; j < (i - 1); j++) {
						room [--lastX][++lastY] = ++num;
					}
					dir = false;
				}
			}
			
			for (int i = N - 1; i > 0; i--) {
				if (!dir) { // 위 -> 아래
					lastY = N - 1;
					lastX++;
					room[lastX][N - 1] = ++num;
					
					for (int j = 0; j < (i - 1); j++) {
						room [++lastX][--lastY] = ++num;
					}
					dir = true;
					
				} else {
					lastX = N - 1;
					lastY++;
					room[N - 1][lastY] = ++num;
					
					for (int j = 0; j < (i - 1); j++) {
						room [--lastX][++lastY] = ++num;
					}
					dir = false;
				}
			}
			
			//Print table
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(room[i][j]+ " ");
//				}
//				System.out.println();
//			}
			
			//move
			String gar = sc.nextLine();
			String move = sc.nextLine();
			int x = 0, y = 0, res = 1;
			for (int i = 0; i < move.length(); i++) {
				switch(move.charAt(i)) {
				case 'U':
					if (x - 1 >= 0) {
						x--;
					}
					res += room[x][y];
					break;
					
				case 'D':
					if (x + 1 < N) {
						x ++;
					}
					res += room[x][y];
					break;
					
				case 'L':
					if (y - 1 >= 0) {
						y--;
					}
					res += room[x][y];
					break;
					
				case 'R':
					if (y + 1 < N) {
						y++;
					}
					res += room[x][y];
					break;
				}
			}
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(res);
		}
	}
}