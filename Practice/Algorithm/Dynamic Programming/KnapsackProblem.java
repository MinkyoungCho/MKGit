import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class Knapsack {
	int[] values;
	int[] weights;
	int capacity;
	public static final int MIN_INT = -9999;
	
	int[][] cache;
	
	public Knapsack(int[] values, int[] weights, int capacity) {
		this.values = values;
		this.weights = weights;
		this.capacity = capacity;
		cache = new int[values.length][capacity + 1];
	}
	
	// ������[1...current] ���� w�� ä�� �� ������ �ִ� ��ġ ���ϱ�
	public int fillKnapsack(int current, int w) { 
		if (current == values.length || w <= 0) {
			return 0;
		}
		if (cache[current][w] > 0) { // �ߺ� ��� ���ϱ�
			return cache[current][w];
		}
		
		cache[current][w] = MIN_INT;
		if (weights[current] <= w) { // �̹� item ����
			cache[current][w] = Math.max(cache[current][w], values[current] + fillKnapsack(current + 1, w - weights[current]));
		}
		cache[current][w] = Math.max(cache[current][w], fillKnapsack(current + 1, w)); //�̹� item ���� ����
		
		return cache[current][w];
	}
}

public class KnapsackProblem {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
		String line = null;
		
		int testCase = Integer.parseInt(reader.readLine().trim());
		for (int i = 0; i < testCase; i++) {
			line = reader.readLine();
			String[] inputs = line.split(" ");
			int capacity = Integer.parseInt(inputs[1]);
			int[] weights = null;
			int[] values = null;
			
			for (int j = 0; j < 2; j++) {
				line = reader.readLine();
				inputs =  line.split(" ");
				
				if (j == 0) {
					weights = new int[inputs.length];
					for (int k = 0; k < inputs.length; k++) {
						weights[k] = Integer.parseInt(inputs[k]);
					}
				} else {
					values = new int[inputs.length];
					for (int k = 0; k < inputs.length; k++) {
						values[k] = Integer.parseInt(inputs[k]);
					}
				}
			}
			
			//��ü ���� �� ��� ���
			Knapsack knapsack = new Knapsack(values, weights, capacity);
			System.out.println(knapsack.fillKnapsack(0, capacity));
			for (int m = 0; m < knapsack.cache.length; m++) {
				for (int n = 0; n < knapsack.cache[0].length; n++) {
					System.out.print(knapsack.cache[m][n] + " ");
				}
				System.out.println();;
			}
		}
	}

}
