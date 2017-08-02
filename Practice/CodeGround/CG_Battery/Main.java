package CG_Battery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		line = reader.readLine();
		int numOfTests =Integer.parseInt(line);
		
		for (int i = 0; i < numOfTests; i++) {
			int count = 1;
			int numOfLines = Integer.parseInt(reader.readLine());
			int[][] lines = new int[numOfLines][4];
			
			for (int j = 0; j < numOfLines; j++) {
				line = reader.readLine();
				String[] inputs = line.split(" ");
				for (int k = 0; k < 4; k++) {
					lines[j][k] = Integer.parseInt(inputs[k]);
				}
			}
			
			Battery bat = new Battery(lines, numOfLines);
			System.out.println("Case #" + count);
			System.out.println(bat.findMinVal());
			
			count ++;
		}
	}

}
