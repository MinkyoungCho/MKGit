package Boj2011;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		int number = Integer.parseInt(reader.readLine());
		
		Encryption enc = new Encryption(number);
		enc.decrypt(0);
		System.out.println(enc.count);
	}

}
