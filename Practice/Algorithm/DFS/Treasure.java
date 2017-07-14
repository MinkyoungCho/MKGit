package DfsBasic;

import java.util.Scanner;

class DFSTreasure{
	char[][] matrix;
	boolean[][] mark;
	int maxCount = -1;
	int maxI;
	int maxJ;
	
	public void dfs(int i, int j, int count) {
		mark[i][j] = true;
		System.out.println(i + " "+ j);
		if (count > maxCount) {
			maxCount = count;
			maxI = i;
			maxJ = j;
		}
		
		if ((i - 1 >= 0) && (matrix[i - 1][j] == 'L') && (!mark[i - 1][j])) {
			dfs(i - 1, j, count + 1);
		}
		if ((i + 1 < matrix.length) && (matrix[i + 1][j] == 'L') && (!mark[i + 1][j])) {
			dfs(i + 1, j, count + 1);
		}
		if ((j - 1 >= 0) && (matrix[i][j - 1] == 'L') && (!mark[i][j - 1])) {
			dfs(i, j - 1, count + 1);
		}
		if ((j + 1 < matrix.length) && (matrix[i][j + 1] == 'L') && (!mark[i][j + 1])) {
			dfs(i, j + 1, count + 1);
		}
	}
}

public class Treasure {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int row = scanner.nextInt();
		int col = scanner.nextInt();
		
		DFSTreasure dfs = new DFSTreasure();
		dfs.matrix = new char[row][col];
		dfs.mark = new boolean[row][col];
		
		String gar = scanner.nextLine();
		
		for (int i = 0; i < row; i++) {
			String input = scanner.nextLine();
			for (int j = 0; j < col; j++) {
				dfs.matrix[i][j] = input.charAt(j);
			}
		}
		
//		for (int i = 0; i < row; i++) {
//			for (int j = 0; j < col; j++) {
//				if (dfs.matrix[i][j] == 'L') {
					dfs.dfs(4, 1, 0);
//				}
				
				for (int m = 0; m < row; m++) {
					for (int k = 0; k < col; k++) {
						dfs.mark[m][k] = false;
					}
				}
//			}
//		}
		
		System.out.println(dfs.maxCount + " " + dfs.maxI + " " + dfs.maxJ);
		
	}
}
