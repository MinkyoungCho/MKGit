package Dice;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Main {
	static int N, M, K, Answer;
	static int[][] map;
	static int[] move;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));
		N = sc.nextInt();
		M = sc.nextInt();
		int x = sc.nextInt(), y = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][M];
		move = new int[K + 1];
		int[] dice = new int[7];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 1; i  <= K; i++) {
			move[i] = sc.nextInt();
		}
		
		int[] backup = new int[7];
		for (int i = 1; i <= K; i++) { // K번 이동
			backup = Arrays.copyOf(dice, dice.length);
			
			//주사위 돌리기 - 바닥 숫자 보기
			switch(move[i]) {
			case 1: //동
				if (y + 1 < M) {
					dice[3] = backup[1];
					dice[2] = backup[2];
					dice[6] = backup[3];
					dice[1] = backup[4];
					dice[5] = backup[5];
					dice[4] = backup[6];
					
					y++;
					if (map[x][y] == 0) {
						map[x][y] = dice[6];
					} else {
						dice[6] = map[x][y];
						map[x][y] = 0;
					}
				}
				break;
				
			case 2: //서
				if (y - 1 >= 0) {
					dice[4] = backup[1];
					dice[2] = backup[2];
					dice[1] = backup[3];
					dice[6] = backup[4];
					dice[5] = backup[5];
					dice[3] = backup[6];
					
					y--;
					if (map[x][y] == 0) {
						map[x][y] = dice[6];
					} else {
						dice[6] = map[x][y];
						map[x][y] = 0;
					}
				}
				break;
				
			case 4: //남
				if (x + 1 < N) {
					dice[2] = backup[1];
					dice[6] = backup[2];
					dice[3] = backup[3];
					dice[4] = backup[4];
					dice[1] = backup[5];
					dice[5] = backup[6];
					
					x ++;
					if (map[x][y] == 0) {
						map[x][y] = dice[6];
					} else {
						dice[6] = map[x][y];
						map[x][y] = 0;
					}
				}
				break;
				
			case 3: //북
				if (x - 1 >= 0) {
					dice[5] = backup[1];
					dice[1] = backup[2];
					dice[3] = backup[3];
					dice[4] = backup[4];
					dice[6] = backup[5];
					dice[2] = backup[6];
					
					x--;
					if (map[x][y] == 0) {
						map[x][y] = dice[6];
					} else {
						dice[6] = map[x][y];
						map[x][y] = 0;
					}
				}
				break;
			}
			
			System.out.println(dice[1]); //윗면 숫자 출력
		}
		
	}
}