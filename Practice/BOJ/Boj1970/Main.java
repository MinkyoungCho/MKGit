package Boj1970;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		int numOfPeople = Integer.parseInt(reader.readLine());
		line = reader.readLine();
		String[] inputs = line.split(" ");
		int[] array = new int[numOfPeople];
		for (int i = 0; i < numOfPeople; i++) {
			array[i] = Integer.parseInt(inputs[i]);
		}
		
		// 객체 생성 및 함수 호출
		Cheers cheers = new Cheers(numOfPeople, array);
		
		System.out.println(cheers.makeCouple(0, new boolean[numOfPeople]));
	}
}
