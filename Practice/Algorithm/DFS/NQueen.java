package DfsBasic;

import java.util.Scanner;

class Chess {
	int size;
	int count = 0;

	public void mark(int x, int y, boolean[][] matrix) {
		for (int i = 0; i < size; i++) {
			matrix[x][i] = true;
			matrix[i][y] = true;
			
			if ((x - i >= 0) && (y - i >= 0)) {
				matrix[x-i][y-i] = true;
			}
			if ((x - i >= 0) && (y + i < size)) {
				matrix[x-i][y+i] = true;
			}
			if ((x + i < size) && (y - i >= 0)) {
				matrix[x+i][y-i] = true;
			}
			if ((x + i < size) && (y + i < size)) {
				matrix[x+i][y+i] = true;
			}					
		}
		
	}
	
	//Backtracking = if promising, dfs
	public void placeQueen(int x, int y, boolean[][] matrix) {
		if ((x + 1 == size) && !matrix[x][y]) {
			count++;
			return;
		}
		
		mark(x, y, matrix); // 마킹
	
		boolean[][] temp = new boolean[size][size];
		
		for (int i = 0; i < size; i++) {
			if (!matrix[x + 1][i]) { // 유망하지 않다면 dfs
				
				for (int m = 0; m < size; m++) { //원본 matrix 훼손하지 않기!
					for (int j = 0; j < size; j++) {
						temp[m][j] = matrix[m][j];
					}
				}
				
				placeQueen(x + 1, i, temp); // temp 넘기기
			} 
		} 
		
	}
}
public class NQueen {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Chess chess = new Chess();
		chess.size = scanner.nextInt();
		for (int i = 0; i < chess.size; i++) {
			boolean[][] matrix = new boolean[chess.size][chess.size];
			chess.placeQueen(0, i, matrix);
		}
		
		System.out.println(chess.count);
	}

}
