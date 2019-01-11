package �丶��;

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
		
		//��� �;��°� 
		if (totalT == maturedT) {
			System.out.println(0);
			return;
		}
		
		boolean isUpdated = true; //true�̸� ������Ʈ ���� ����
		while (isUpdated) {
			count ++;
			isUpdated = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if ((matrix[i][j] == 1) && !mark[i][j]) {
						mark[i][j] = true;
						
						//��
						if (i - 1 >= 0 && matrix[i - 1][j] == 0) {
							matrix[i - 1][j] = 1;
						}
						
						//�Ʒ�
						if (i + 1 < N && matrix[i + 1][j] == 0) {
							matrix[i + 1][j] = 1;
						}
						
						//��
						if (j - 1 >= 0 && matrix[i][j - 1] == 0) {
							matrix[i][j - 1] = 1;
						}
						
						//����
						if (j + 1 < M && matrix[i][j + 1] == 0) {
							matrix[i][j + 1] = 1;
						}
						
						isUpdated = true;
					}
				}
			}
		}
		
		//���� 1�� �ƴ� ���Ұ� �ִ°�
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
