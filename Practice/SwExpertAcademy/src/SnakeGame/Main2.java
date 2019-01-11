package SnakeGame;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Main2 {
	static int Answer, L, N;
	
	static int changeDir(int dir, char move) {
		if (move == 'L') {
			dir = (dir + 1) % 4;
		} else {
			dir = (dir + 3) % 4;
		}
		
		return dir;
	}

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		L = sc.nextInt();
		N = sc.nextInt();
		int i = 0, j = 0, dir = 0, duration = 0;
		HashMap<Integer, ArrayList<Integer>> dup = new HashMap<>();
		ArrayList<Integer> first = new ArrayList<>();
		first.add(0);
		
		dup.put(0, first);
		for (int m = 0; m < N; m++) {
			int size = sc.nextInt();
			char move = sc.next().charAt(0);
			
			if (dir == 0) {
				if (j + size > L) {
					duration += (L - j);
					break;
				} else {
					// 몸통 박치기확인
					
					for (int k = j + 1; k <= j + size; k++) {
						if (first.contains(i)) {
							
						} else {
							
						}
					}
					j += size;
					dir = changeDir(dir, move);
				}
			} else if (dir == 1) {
				if (i + size > L) {
					duration += (L - i);
					break;
				} else {
					i += size;
					dir = changeDir(dir, move);
				}
			} else if (dir == 2) {
				if (j - size < -1 * L) {
					duration += (j - L);
					break;
				} else {
					j -= size;
					dir = changeDir(dir, move);
				}
			} else {
				if (i - size < - 1 * L) {
					duration += (i - L);
					break;
				} else {
					i -= size;
					dir = changeDir(dir, move);
				}
			}
		}
		
		System.out.println(duration);
	}
}