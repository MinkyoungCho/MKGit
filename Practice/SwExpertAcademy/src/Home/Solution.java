package Home;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
	static int Answer;
	static int N, M, K;
	static int[][] resizedMap;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int max = -1;
			N = sc.nextInt();
			M = sc.nextInt();
			int[][] map = new int[N + 1][N + 1];
			int count1 = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1) {
						count1 ++;
					}
				}
			}
			
			//K 값 구하기: 모든 집에게  서비스하려고할때 손해를 보지 않는 최대 범위 (집 위치 상관 없이)
			int totalProfit = count1 * M, cost;
			for (K = 1;; K ++) {
				cost = K * K + (K - 1) * (K - 1);
				if (totalProfit < cost) { // 처음으로 손해남
					K --;
					break;
				}
			}
			
			
			for (int width = K; width >= 1; width--) {
				//map 크기 조정, 재배치
				resizedMap = new int[2 * width + N - 1][2 * width + N - 1];
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						resizedMap[i + width - 1][j + width - 1] = map[i][j];
						
					}
				} //ok
				
					//사이즈 K 마름모 nested로 이동시키며 최대 집의 개수 찾기
				for (int i = width; i < N + width; i++) {
					for (int j = width; j < N + width; j++) {
						int res = makeDiamond(i, j, width);
						if (res > max) {
							max = res;
						}
					}
				}
			}
			
			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(max);
		}
	}
	
	static int makeDiamond(int x, int y, int K) { //마름모 만들고 포함된 집의 개수 리턴		System.out.println(x + " " + y + " " + K);
		int res = 0;
		
		int j = y;
		for (int i = 1; i <= K; i++, j--) {
			for (int m = 0; m < (2 * i - 1); m++) {
				if (resizedMap[x - K + i][j + m] == 1) {
					res ++;
				}
			}
		}
		
		j = y - K + 2;
		for (int i = K - 1; i >= 1; i--, j++) {
			for (int m = 0; m < (2 * i - 1); m++) { // m: 횟수
				if (resizedMap[x + K - i][j + m] == 1) {
					res ++;
				}
			}
		}

		if (res * M >= K * K + (K - 1) * (K - 1)) {
			return res; // res개의 집에 서비스
		} else {
			return -1; // 서비스 불가
		}
	}
	
}