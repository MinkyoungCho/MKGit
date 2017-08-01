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
	 * (i, j)에서 시작했을 때 가능한 최대 판다 수명 
	 * 캐시에 각 결과 저장하고 재사용하기 (DP)
	 * 
	 */
	public int eatBamboo(int i, int j) { // (i, j)에서 시작할 때 판다의 최대 수명 리턴
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
				int res = eatBamboo(i, j); //(i, j)에서 시작하는 경우 판다의 최대 수명
				if (res > max) {
					System.out.println(i + " " + j);
					max = res; // 최대 수명 찾기
				}
			}
		}
		
		return max;
	}
	
}
