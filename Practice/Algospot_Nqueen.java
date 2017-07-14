package Practice;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


class NQueen {
	
	int size;
	int[] queen;
	int output = 0;
	
	public NQueen(int size) {
		this.size = size;
		queen = new int[size];
	}
	
	public boolean isPossible(int x, int y) {
		for (int i = 0; i < x; i++) {
			if (queen[i] == y || (Math.abs(x - i) == Math.abs(y - queen[i]))) {
				return false;
			}
		}
		return true;
	}
	
	public void placeQueen(int level) {
		if (level == size) { // return if all the queens are placed
			output++;
			return;
		}
		
		for (int i = 0; i < size; i++) {
			if (isPossible(level, i)) {
				queen[level] = i;
				placeQueen(level + 1);
			}
		}
	}
}

public class Algospot_Nqueen {

	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", false));
		String line = null;
		
		int testCase = Integer.parseInt(reader.readLine());
		for (int i = 0; i < testCase; i++) {
			int size = Integer.parseInt(reader.readLine());
			System.out.println(size);
			NQueen nq = new NQueen(size);
			nq.placeQueen(0);
			
			System.out.println(nq.output);
			writer.write(Integer.toString(nq.output));
			writer.write("\n");
		}
		reader.close();
		writer.close();
	}

}
