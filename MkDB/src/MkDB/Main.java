package MkDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Operation op = new Operation();
		
		File data = new File("data.txt");
		BufferedReader reader = new BufferedReader(new FileReader(data));
		String line = null;
		
		//Open & Load DB File
		int lineCount = 0;
		while ((line = reader.readLine()) != null) {
			lineCount++;
		}
		op.data = new int[lineCount];
		
		reader = new BufferedReader(new FileReader(data));
		line = null;
		for(int i = 0; i < lineCount; i++) {
			op.data[i] = Integer.parseInt(reader.readLine());
		}
		
		reader.close();
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String input = scanner.nextLine();
			String[] inputs = input.split("[.]");
			
			if (inputs[0] == "insert") {
				op.insert(data, Integer.parseInt(inputs[1]));
			} else if (inputs[0] == "delete") {
				op.delete(data, Integer.parseInt(inputs[1]));
			} else if (inputs[0] == "search") {
				System.out.println(op.search(Integer.parseInt(inputs[1])));
			} else { //sort
				op.sort();
			}
		}
	}

}
