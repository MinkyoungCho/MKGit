package CG_ExtremeNum2;

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
			System.out.println("Case #" + (i + 1));
			int number = Integer.parseInt(reader.readLine());
			
//			BottomUp bu = new BottomUp(number);
//			System.out.println("BottomUp: " + bu.findNth());
			
			TopDown td = new TopDown(number);
			System.out.println(td.findNth());
			System.out.println();
		}

	}

}
