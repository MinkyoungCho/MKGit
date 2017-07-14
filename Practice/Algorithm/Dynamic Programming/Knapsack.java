package DP;

class DP {
	int[][] table = new int[3][20];
	int[] weight = new int[]{3, 4, 5};
	int[] value = new int[]{6, 3, 5};
	
	public int fillKnapsack(int item, int capacity) {
		System.out.println(item + " " + capacity);
		if (capacity == 0) {
			return 0;
		}
		
		if (table[item][capacity] >= 0) {
			return table[item][capacity];
		}
		
		int ret1 = 0;
		int ret2 = 0;
		if (capacity - weight[item] >= 0 && (item - 1 >= 0)) {
			ret1 = value[item] + fillKnapsack(item - 1, capacity - weight[item]);
		}
		if (item - 1 >= 0) {
			ret2 = fillKnapsack(item - 1, capacity);
		}
		
		table[item][capacity] = Math.max(ret1, ret2);
		return table[item][capacity];
	}
	
	
}
public class Knapsack {
	
	public static void main(String[] args) {
		DP dp = new DP();
		for (int i = 0; i < dp.table.length; i++) {
			for (int j = 0; j < dp.table[0].length; j++) {
				dp.table[i][j] = -1;
			}
		}

		System.out.println(dp.fillKnapsack(2, 12));
		
		for (int i = 0; i < dp.table.length; i++) {
			for (int j = 0; j < 12; j++) {
				System.out.print(dp.table[i][j] + " ");
			}
			System.out.println();
		}
	}

}
