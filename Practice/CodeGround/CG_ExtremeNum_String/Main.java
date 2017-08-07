package CG_ExtremeNum;

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
			int number = Integer.parseInt(reader.readLine());
			
			BottomUp bu = new BottomUp(number);
			System.out.println(bu.findNth());
			
			TopDown td = new TopDown(number);
			System.out.println(td.findNth());
			System.out.println();
		}

	}

}
