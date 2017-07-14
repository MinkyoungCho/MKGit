package Practice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class DiamondPath {
	int[][] matrix;
	int[][] calculatedMatrix;
	
	public DiamondPath(int[][] matrix) {
		this.matrix = matrix;
		calculatedMatrix = new int[matrix.length][matrix[0].length];
		calculatedMatrix[0][0] = matrix[0][0];
	}
	
	public void followPath() {
		// 다이아몬드 윗부분
		int row = 1;
		for (; row < matrix[0].length; row++) {
			calculatedMatrix[row][0] = calculatedMatrix[row - 1][0] + matrix[row][0];
			calculatedMatrix[row][row] = calculatedMatrix[row - 1][row - 1] + matrix[row][row];
			
			for (int j = 1; j < row; j++) {
				calculatedMatrix[row][j] = matrix[row][j] + Math.max(calculatedMatrix[row - 1][j - 1], calculatedMatrix[row - 1][j]);
			}
		}
		
		// 다이아몬드 아랫부분
		for (; row < matrix.length; row++) {
			for (int j = 0; (j + 1 < matrix[0].length) && (calculatedMatrix[row - 1][j + 1] > 0); j++) {
				calculatedMatrix[row][j] = matrix[row][j] + Math.max(calculatedMatrix[row - 1][j], calculatedMatrix[row - 1][j + 1]);
			}			
		}
	}
}

public class Algospot_DiamondPath {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
		String line = null;
		
		int testCase = Integer.parseInt(reader.readLine());
		for (int i = 0; i < testCase; i++) {
			line = reader.readLine();
			int size = Integer.parseInt(line);
			int[][] matrix = new int[2 * size - 1][size];
			
			for (int j = 0; j < (2 * size - 1); j++) {
				line = reader.readLine();
				String[] inputs = line.split(" ");
				
				for (int k = 0; k < inputs.length; k++) {
					matrix[j][k] = Integer.parseInt(inputs[k]);
				}	
			}
			
			DiamondPath dp = new DiamondPath(matrix);
			dp.followPath();
			System.out.println(dp.calculatedMatrix[matrix.length - 1][0]);
			
		}
	}
}
