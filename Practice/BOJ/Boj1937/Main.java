package Boj1937;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		int size = Integer.parseInt(reader.readLine());
		int[][] matrix = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			String input = reader.readLine();
			String[] inputs = input.split(" ");
			
			for (int j = 0; j < inputs.length; j++) {
				matrix[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		//객체 생성 및 함수 실행
		GreedyPanda gp = new GreedyPanda(size, matrix);
		System.out.println(gp.scanMatrix());
	}
}
