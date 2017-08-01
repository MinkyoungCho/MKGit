package Boj1937;

public class GreedyPanda {
	int size;
	int[][] matrix;
	int[][] cache;
	
	public GreedyPanda(int size, int[][] matrix) {
		this.size = size;
		this.matrix = matrix;
		this.cache = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				cache[i][j] = -1;
			}
		}
	}
	
	/* 
	 * (i, j)���� �������� �� ������ �ִ� �Ǵ� ���� 
	 * ĳ�ÿ� �� ��� �����ϰ� �����ϱ� (DP)
	 * 
	 */
	public int eatBamboo(int i, int j) { // (i, j)���� ������ �� �Ǵ��� �ִ� ���� ����
		if (cache[i][j] >= 0) {
			return cache[i][j];
		}
		
		int current = matrix[i][j];
		int res = 1;

		if (i - 1 >= 0 && matrix[i - 1][j] > current) {
			res = Math.max(res, eatBamboo(i - 1, j) + 1);
		}
		if (i + 1 < size && matrix[i + 1][j] > current) {
			res = Math.max(res, eatBamboo(i + 1, j) + 1);
		}
		if (j - 1 >= 0 && matrix[i][j - 1] > current) {
			res = Math.max(res, eatBamboo(i, j - 1) + 1);
		}
		if (j + 1 < size && matrix[i][j + 1] > current) {
			res = Math.max(res, eatBamboo(i, j + 1) + 1);
		}
		
		cache[i][j] = res;
		return res;
	}
	
	public int scanMatrix() {
		int max = -1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int res = eatBamboo(i, j); //(i, j)���� �����ϴ� ��� �Ǵ��� �ִ� ����
				if (res > max) {
					System.out.println(i + " " + j);
					max = res; // �ִ� ���� ã��
				}
			}
		}
		
		return max;
	}
	
}
