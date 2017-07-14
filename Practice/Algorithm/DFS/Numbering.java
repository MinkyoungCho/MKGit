package DfsBasic;

import java.util.Scanner;

class MatrixNumbering {
	short[][] matrix;
	boolean[][] mark;
	int count = 0;

	
	public void dfs(int i, int j) {
		mark[i][j] = true;
		matrix[i][j] = 0;
		count ++;
		
		if ((i - 1 >= 0) && !mark[i - 1][j] && (matrix[i - 1][j] == 1)) {
			dfs(i - 1, j);
		}
		if ((i + 1 < matrix.length) && !mark[i + 1][j] && (matrix[i + 1][j] == 1)) {
			dfs(i + 1, j);
		}
		if ((j - 1 >= 0) && !mark[i][j - 1] && (matrix[i][j - 1] == 1)) {
			dfs(i, j - 1);
		}
		if ((j + 1 < matrix.length) && !mark[i][j + 1] && (matrix[i][j + 1] == 1)) {
			dfs(i, j + 1);
		}
	}
}

public class Numbering {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int sizeOfMatrix = scanner.nextInt();
		MatrixNumbering numbering = new MatrixNumbering();
		numbering.matrix = new short[sizeOfMatrix][sizeOfMatrix];
		numbering.mark = new boolean[sizeOfMatrix][sizeOfMatrix];
		String gar = scanner.nextLine();
		
		for (int i = 0; i < sizeOfMatrix; i++) {
			String input = scanner.nextLine();
			for (int j = 0; j < sizeOfMatrix; j++) {
				numbering.matrix[i][j] = (short) (input.charAt(j) - '0');
			}
		}
		
		int num = 1;
		
		for (int i = 0; i < sizeOfMatrix; i++) {
			for (int j = 0; j < sizeOfMatrix; j++) {
				if (!numbering.mark[i][j] && (numbering.matrix[i][j] == 1)) {
					numbering.dfs(i, j);
					System.out.println(num + " " + numbering.count);
					numbering.count = 0;
					num++;
				}
			}
		}
		
		
	}
}
