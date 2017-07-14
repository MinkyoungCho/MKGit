package DP;

class LISDP {
	int[] inputs;
	
	public int lis(int x, int max) {
		if (x == inputs.length) {
			return 0;
		}
		
		int val = 0;
		//선택
		if (inputs[x] > max) {
			val = lis(x + 1, inputs[x]) + 1; 
		}
		//선택 안함
		val = Math.max(val, lis(x + 1, max));
		
		return val;
		
	}
}

public class LIS2 {
	
	public static void main(String[] args) {
		LISDP lis = new LISDP();
		lis.inputs = new int[]{7, 2, 3, 1, 0, 8, 9, 6};
	
		System.out.println(lis.lis(0, -1));
		
		

	}
}
