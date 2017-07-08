package MkDB;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Operation {
	BufferedWriter writer = null;
	int[] data;

	//DB�� key ���� & ����
	public void insert(File file, int key) {
		try {
			writer = new BufferedWriter(new FileWriter(file, true)); //���� append
			writer.write(key);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * key ���� �� ���� DB�� ����
	 * Ž�� --> �� ��ġ �����ϰ� ����
	 */
	public void delete(File file, int key) {
		try {
			writer = new BufferedWriter(new FileWriter(file, false)); //���� �����
			int position = search(key);
			
			for (int i = 0; i < position; i++) {
				writer.write(data[i] + '\n');
			}
			
			for (int i = position + 1; i < data.length; i++) {
				writer.write(data[i] + '\n');
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//key�� �ش��ϴ� index ����
	public int search(int key) {
		for (int i = 0; i < data.length; i++) {
			if (key == data[i]) {
				return i;
			}
		}
		return -1;
	}

	//Sort data array (not data file!)
	public void sort() {
		
	}
}
