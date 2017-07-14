package DP;

public class MaxSum {
	
	public static int findMaxSum(int[] inputs) {
		int max = -1;
		int sum = 0;
		
		for (int i = 0; i < inputs.length; i++) {
			sum += inputs[i];
			if (sum < 0) {
				sum = 0;
				continue;
			}
			if (sum > max) {
				max = sum;
			}
			
		}
		
		return max;
	}

	public static void main(String[] args) {
		int[] inputs = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
		System.out.println(MaxSum.findMaxSum(inputs));

	}

}
