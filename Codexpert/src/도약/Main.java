package 도약;

import java.util.Scanner;

public class Main {
	static int N;
	static int[] inputs;
	static int[][] table;
	
	public static void sortInputs() {
		int min = -1;
		
		for (int i = 0; i < (N - 1); i++) {
			min = i; 
			
			for (int j = i + 1; j < N; j++) {
				if (inputs[min] > inputs[j]) {
					min = j;
				}
			}
			
			int temp = inputs[i];
			inputs[i] = inputs[min];
			inputs[min] = temp; 
		}
	}
	
	public static void fillTable() {
		for (int i = 0; i < N; i++) {
			for (int j =  i + 1; j < N; j++) {
				table[i][j] = inputs[j] - inputs[i];
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		inputs = new int[N];
		table = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			inputs[i] = sc.nextInt();
		}
		
		sortInputs();
		
		fillTable();
		
		int count = 0;
		
		//first, next 원소 정하기
			//next에서 last 정하기(크기 비교!), 갯수 ++
		for (int i = 0; i < (N - 2); i++) {
			int first = i;
			int next;
			int sizeOfMove = -1;
			
			for (int j = i + 1; j < (N - 1); j++) {
				next = j;
				sizeOfMove = table[i][j];
				
				for (int k = j + 1; k < N; k++) {
					if (table[j][k] >= table[i][j] && table[j][k] <= 2 * table[i][j]) {
						count ++;
					}
				}
			}
			
		}
		
		System.out.println(count);
		
	}

}
