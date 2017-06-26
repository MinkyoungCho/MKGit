package MkDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
			
			// 입력 받아서 해당 operation 수행하기 (while, switch)
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				writer.write(line);
				writer.newLine();
			}
			
//			reader.close();
			writer.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (Exception e) {
			System.out.println("Exception");			
		}
		
	}

}
