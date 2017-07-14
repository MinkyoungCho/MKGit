package DP;

class DPPelin {
	int[] inputs;
	
	public int findPivot() {
		int min = 10000;
		
		for (int i = 0; i < inputs.length; i++) {
			min = Math.min(min, editDist(i));
		}
		return min;
	}
	
	public int editDist(int x) {
		if ((x == 0) || (x == inputs.length - 1)) {
			return inputs.length - 1;
		}
		
		int[][] table = new int[x + 1][inputs.length - x];
		for (int i = 0; i < table.length; i++) {
			table[i][0] = i;
		}
		for (int i = 0; i < table[0].length; i++) {
			table[0][i] = i;
		}
		
		for (int i = 1; i < table.length; i++) {
			for (int j = 1; j < table[0].length; j++) {
				if (inputs[x - i] == inputs[x + j]) {
					table[i][j] = table[i - 1][j - 1];
				}
				else {
					int ret1 = table[i - 1][j] + 1;
					int ret2 = table[i - 1][j - 1] + 1;
					int ret3 = table[i][j - 1] + 1;
					table[i][j] = Math.min(Math.min(ret1, ret2), ret3);
				}
			}
		}
		
		return table[table.length - 1][table[0].length - 1];
		
	}
}

public class Pelindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DPPelin pelin = new DPPelin();
		pelin.inputs = new int[]{1, 2, 3, 4, 2};
		System.out.println(pelin.findPivot());

	}

}
