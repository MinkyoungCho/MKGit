package Practice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

class LIS {
	int[] inputs;
	
	public LIS(String[] inputs) {
		this.inputs = new int[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			this.inputs[i] = Integer.parseInt(inputs[i]);
		}
	}
	
	int maxLength = -1;

	public void findLIS(int current, int max, int length) {
		if (current == inputs.length) { // 재귀 bound
			if (length > maxLength) {
				maxLength = length;
			}
			return;
		}
		
		// 두가지 경우 - 선택 or !선택
		findLIS(current + 1, max, length);
		if (inputs[current] > max) { // 현재 아이템이 max 보다 큰 경우에만 선택 가능!
			findLIS(current + 1, inputs[current], length + 1);
		}
	}
}

public class Algospot_LIS {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
		String line = null;
		
		int testCase = Integer.parseInt(reader.readLine());
		for (int i = 0; i < testCase; i++) {
			line = reader.readLine();
			int sizeOfArray = Integer.parseInt(line);
			
			line = reader.readLine();
			String[] inputs = line.split(" ");
			
			LIS lis = new LIS(inputs);
			lis.findLIS(0, -1, 0);
			System.out.println(lis.maxLength);
		}
		
	}
}
