package DP;

public class LIS {
	static int max = 0;
	static int[][] matrix;
	
	public static int findMax(int[] inputs, int first, int last) {
		int maxVal = 0;
		
		for (int i = last - 1; i >= first; i--) {
			if ((inputs[i] < inputs[last]) && (matrix[first][i] > maxVal)) {
				maxVal = matrix[first][i];
			}
		}
		
		return maxVal;
	}
	
	public static void incrementalSeq(int[] inputs) {
		matrix = new int[inputs.length][inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			matrix[i][i] = 1;
			
			for (int j = i + 1; j < inputs.length; j++) {
				//[i][j] 보다 작은 원소 중 가장 큰 값 찾기
				//없으면  0 리턴
				matrix[i][j] = findMax(inputs, i, j) + 1;
				if (matrix[i][j] > max) {
					max = matrix[i][j];
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		int[] inputs = new int[]{7, 2, 3, 1, 5, 8, 9, 6};
		LIS lis = new LIS();
		lis.incrementalSeq(inputs);
		System.out.println(lis.max);

	}
}
