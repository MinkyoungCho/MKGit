package MkDB;

import java.io.BufferedWriter;
import java.io.IOException;

public class Operation {
	
	public static int search(int key) {
		
	}
	
	public static void insert(BufferedWriter writer, int key) {
		try {
			writer.write(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String delete(int key) {
		
	}
	
	public static void sort() {
		
	}
}
