package MkDB;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Operation {
	BufferedWriter writer = null;
	int[] data;

	//DB에 key 삽입 & 저장
	public void insert(File file, int key) {
		try {
			writer = new BufferedWriter(new FileWriter(file, true)); //파일 append
			writer.write(key);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * key 삭제 후 파일 DB에 저장
	 * 탐색 --> 그 위치 무시하고 저장
	 */
	public void delete(File file, int key) {
		try {
			writer = new BufferedWriter(new FileWriter(file, false)); //파일 덮어쓰기
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
	
	//key에 해당하는 index 리턴
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
