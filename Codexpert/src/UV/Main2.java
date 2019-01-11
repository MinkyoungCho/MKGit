package UV;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String[] inputs = new String[N];
		int[][] uv = new int[N][N];
		int[][] table = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			inputs[i] = sc.next();
			for (int j = 0; j < N; j++) {
				uv[i][j] = inputs[i].charAt(j) - '0';
			}
		}
		
		
		table[0][0] = uv[0][0];
		for (int i = 1 ; i < N; i++) {
			table[0][i] = table[0][i  - 1] + uv[0][i];
			table[i][0] = table[i - 1][0] + uv[i][0];
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (table[i - 1][j] > table[i][j - 1]) {
					table[i][j] = table[i][j - 1] + uv[i][j];
				} else {
					table[i][j] = table[i - 1][j] + uv[i][j];
				}
			}
		}
		
		System.out.println(table[N - 1][N - 1]);
		
	}

}
