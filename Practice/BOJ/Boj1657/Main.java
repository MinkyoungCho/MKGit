package Bj1657;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		line = reader.readLine();
		String[] inputs = line.split(" ");
		int row = Integer.parseInt(inputs[0]);
		int col = Integer.parseInt(inputs[1]);
		int[][] matrix = new int[row][col];
	
		for (int i = 0; i < row; i++) {
			line = reader.readLine();
			
			for (int j = 0; j < col; j++) {
				matrix[i][j] = line.charAt(j); 
			}
			System.out.println();
		}
		
		// 객체 생성 및 함수 호출
		Dubu dubu = new Dubu(matrix);
		dubu.calcPrice(dubu.mark, 0, 0, 0);
		System.out.println(dubu.maxPrice);
	}
}
