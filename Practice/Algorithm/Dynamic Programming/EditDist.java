package DP;

public class EditDist {
	static int[][] matrix;
	static int count = 0;
	
	public static void calculateDist(String str1, String str2) {
		matrix = new int[str1.length() + 1][str2.length() + 1];
		
		for (int i = 0; i < str1.length(); i++) {
			matrix[i][0] = 0;
		}
		
		for (int i = 0; i < str2.length(); i++) {
			matrix[0][i] = 0;
		}
		
		for (int i = 0; i < str1.length(); i++) {
			for (int j = 0; j < str2.length(); j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					matrix[i + 1][j + 1] = matrix[i][j] + 1;
				} else {
					int temp = Math.max(Math.max(matrix[i][j], matrix[i + 1][j]), matrix[i][j + 1]);
					matrix[i + 1][j + 1] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		EditDist.calculateDist("Saturday", "Solday");
		System.out.println(EditDist.matrix[EditDist.matrix.length - 1][EditDist.matrix[0].length - 1]);
//		System.out.println(EditDist.count);
	}
}
