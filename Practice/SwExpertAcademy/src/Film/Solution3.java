package Film;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Solution3 {
	static final int max = 100000;
	static int D, W, K, Answer;
	static int[][] map;
	static int minUsage = Integer.MAX_VALUE;
	
	static boolean passTest() {
		boolean result = false;
		int max = -1, last = -1, count = 0;
		//성능검사 (>= K인가)	
		for (int col = 1; col <= W; col++) {
			max = -1;
			last = -1;
			count = 0;
			for (int row = 1; row <= D; row++) {
				int temp = map[row][col];
				if (last == temp) {
					count ++;
					if (count >= K) {
						max = count;
						break;
					}
				} else if (last != temp) {
					if (count > max) {
						max = count;
						
					}
					count = 1;
					last = temp;
				}
			}
			
			if (max < K) {
				return false;
			} 
		} // 모든 col에대해 통과
		
		return true;
	}
	
	static void cover(int idxOfFilm, int numOfUsage) {
		if (idxOfFilm == D + 1) {
			if (passTest()) {
				if (numOfUsage < minUsage) {
					minUsage = numOfUsage;
				}
			}
			return;
		}
		
		int res = max;
		int[] backup = new int[W + 1];
		backup = Arrays.copyOf(map[idxOfFilm], map[idxOfFilm].length);
		
		//약품 x
		if (numOfUsage < minUsage) {
			cover(idxOfFilm + 1, numOfUsage);
		}
		
		//0 약품
		for (int i = 1; i <= W; i++) {
			map[idxOfFilm][i] = 0;
		}
		if (numOfUsage + 1 < minUsage) {
			cover(idxOfFilm + 1, numOfUsage + 1);
		}
		//0약품 끝
		
		map[idxOfFilm] = Arrays.copyOf(backup, backup.length); // 상태 되감기
		
		
		//1 약품
		for (int i = 1; i <= W; i++) {
			map[idxOfFilm][i] = 1;
		}
		if (numOfUsage + 1 < minUsage) {
			cover(idxOfFilm + 1, numOfUsage + 1);
		}
		//1약품 끝
		
		map[idxOfFilm] = Arrays.copyOf(backup, backup.length); // 상태 되감기
	}

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			minUsage = Integer.MAX_VALUE;
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			map = new int[D + 1][W + 1];
			
			for (int i = 1; i <= D; i++) {
				for (int j = 1; j <= W; j++) {
					map[i][j] = sc.nextInt(); // A: 0, B: 1;
				}
			}
			cover(1, 0); // 1번막에 어떤 선택? 안칠 or 0칠 or 1칠 -> 사용 가능한 최소 막의 개수 리턴
		
			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(minUsage);
			}
		}
	}
