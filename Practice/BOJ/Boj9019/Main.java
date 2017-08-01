package Boj9019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		int numOfTest = Integer.parseInt(reader.readLine());
		for (int i = 0; i < numOfTest; i++) {
			String input = reader.readLine();
			String[] inputs = input.split(" ");
			int a = Integer.parseInt(inputs[0]);
			int b = Integer.parseInt(inputs[1]);
			
			//��ü ���� �� �Լ� ȣ��
			Dslr dslr = new Dslr(a, b);
			System.out.println(dslr.searchMinPath());
		}
	}
}
