package Bj10875;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		int size = Integer.parseInt(reader.readLine());
		boolean[][] matrix = new boolean[2 * size + 1][2 * size + 1];
		matrix[size][size] = true;
		int direction = 3;
		
		int numOfMove = Integer.parseInt(reader.readLine());
		Baem baem = new Baem(size, matrix, direction);
		
		for (int i = 0; i < numOfMove; i++) {
			line = reader.readLine();
			String[] inputs = line.split(" ");
			int numOfSteps = Integer.parseInt(inputs[0]);
			char rotate = inputs[1].charAt(0);
			
			if (!baem.move(numOfSteps, rotate)) {
				break;
			}
		}
		
		System.out.println(baem.lenOfLife);
	}

}
