package 토마토;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt(); //col
		int N = sc.nextInt(); //row
		
		int[][] matrix = new int[N][M];
		boolean[][] mark = new boolean[N][M];
		int totalT = 0;
		int maturedT = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				matrix[i][j] = sc.nextInt();
				
				if (matrix[i][j] != -1) {
					totalT ++;
					if (matrix[i][j] == 1) {
						maturedT ++;
					}
				}
				
			}
		}
		
		int count = -1;
		
		//모두 익었는가 
		if (totalT == maturedT) {
			System.out.println(0);
			return;
		}
		
		boolean isUpdated = true; //true이면 업데이트 내용 있음
		while (isUpdated) {
			count ++;
			isUpdated = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if ((matrix[i][j] == 1) && !mark[i][j]) {
						mark[i][j] = true;
						
						//위
						if (i - 1 >= 0 && matrix[i - 1][j] == 0) {
							matrix[i - 1][j] = 1;
						}
						
						//아래
						if (i + 1 < N && matrix[i + 1][j] == 0) {
							matrix[i + 1][j] = 1;
						}
						
						//왼
						if (j - 1 >= 0 && matrix[i][j - 1] == 0) {
							matrix[i][j - 1] = 1;
						}
						
						//오른
						if (j + 1 < M && matrix[i][j + 1] == 0) {
							matrix[i][j + 1] = 1;
						}
						
						isUpdated = true;
					}
				}
			}
		}
		
		//아직 1이 아닌 원소가 있는가
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j ++) {
				if (matrix[i][j] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(count);
	}

}
