package DP;

public class Tiling {
	static int[] matrix = new int[100];
	
	public static int tiling(int n) {
		if (n == 1) {
			return 1;
		}
		else if (n == 2) {
			return 3;
		}
		else {
			if (matrix[n - 1] > 0) {
				return matrix[n - 1];
			}
			matrix[n - 1] = (tiling(n - 1) + 3 * tiling(n - 2));
			return matrix[n - 1];
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(Tiling.tiling(4) % 10007);

	}

}
