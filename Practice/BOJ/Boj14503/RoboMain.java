package Bj14503;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class RoboMain {

	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		line = reader.readLine();
		String[] inputs = line.split(" ");
		int[][] matrix = new int[Integer.parseInt(inputs[0])][Integer.parseInt(inputs[1])];
		boolean[][] mark = new boolean[Integer.parseInt(inputs[0])][Integer.parseInt(inputs[1])];
		
		line = reader.readLine();
		inputs = line.split(" ");
		int r = Integer.parseInt(inputs[0]);
		int c = Integer.parseInt(inputs[1]);
		int direction = Integer.parseInt(inputs[2]);
		
		for (int i = 0; i < matrix.length; i++) {
			line = reader.readLine();
			inputs = line.split(" ");
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = Integer.parseInt(inputs[j]);
				if (matrix[i][j] == 0) {
					mark[i][j] = true; // 걸레질 가능 지역
				}
			}
		}
		
	
		//객체 생성 및 함수호출
		Robot robo = new Robot(matrix, mark, r, c, direction);
		System.out.println(robo.cleanRoom(r, c, direction));
	}
}
