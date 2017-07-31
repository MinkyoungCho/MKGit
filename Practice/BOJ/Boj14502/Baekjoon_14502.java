package Bj14502;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Baekjoon_14502 {

	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		line = reader.readLine();
		String[] inputs = line.split(" ");
		int rowSize = Integer.parseInt(inputs[0]);
		int colSize = Integer.parseInt(inputs[1]);
		int[][] matrix = new int[rowSize][colSize];
		
		for (int i = 0; i < rowSize; i++) {
			line = reader.readLine();
			inputs = line.split(" ");
			
			for (int j = 0; j < colSize; j++) {
				matrix[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		//객체 생성 및 계산
		Virus virus = new Virus(matrix);
		virus.placeWalls();
		System.out.println(virus.getSafeNum());
		
	}

}
