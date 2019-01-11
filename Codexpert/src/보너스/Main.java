package 보너스;

import java.util.Scanner;

public class Main {
	static int N, M;
	static int[][] relationship;
	static int[] pay;
	static int[] result;
	static boolean[] mark;
	static boolean flag = true;
	
	public static boolean isPossible(int current) {
		int adjacent = 1;
		
		while (adjacent < current) {
			
			if (relationship[current][adjacent] == 1 && result[current] <= result[adjacent]) { //current: 상사
				return false;
			}
			
			if (relationship[adjacent][current] == 1 && result[adjacent] <= result[current]) { //current: 부하직원
				return false;
			}
			adjacent++;
		}
		
		
		return true;
	}

	public static void coloring(int i) {
		if (flag && isPossible(i)) {
			if (i == N - 1) {
				for (int  j= 0; j < N; j++) {
					System.out.print(result[j] + " ");
				}
				
				flag = false;
				
			} else {
				for (int j = 0; j < N; j++) {
					if (mark[j]) {
						continue;
					}
					
					result[i + 1] = pay[j];
					mark[j] = true;
					
					coloring(i + 1);
					
					mark[j] = false;
				}
			}
		}
	}
	
	public static void sorting(int[] arr) { //내림차순
		for (int i = 0; i < arr.length - 1; i++) {
			int maxVal = arr[i];
			int maxIdx = i;

			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] > maxVal) {
					maxIdx = j;
					maxVal = arr[j];
				}
			}
			
			if (maxIdx != i) {
				int temp = arr[i];
				arr[i] = arr[maxIdx];
				arr[maxIdx] = temp;
			}
		}
	}
	
	public static boolean hasSub(int ID) {
		for (int i = 0; i < N; i++) {
			if (relationship[ID][i] == 1) {
				return true;
			}
		}
		
		return false;
	}
	
	public static int[] findSub(int ID) {
		if (!hasSub(ID)) {
			return new int[N];
		} 
		
		int[] temp;
		for (int i = 0; i < N; i++) {
			if (relationship[ID][i] == 1) {
				temp = findSub(i);
				
				for (int j = 0; j < N; j++) {
					if ((relationship[ID][j] == 1) || temp[j] == 1) {
						relationship[ID][j] = 1;
					}
				}
			}
		}
		
		return relationship[ID];
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		relationship = new int[N][N];
		result = new int[N];
		mark = new boolean[N];
		
		for (int i = 0; i < M; i++) {
			relationship[sc.nextInt() - 1][sc.nextInt() - 1] = 1;
		}
		
		pay = new int[N];
		for (int i = 0; i < N; i++) {
			pay[i] = sc.nextInt();
		}
		
		// 금액 내림차순
		sorting(pay);
		result[0] = pay[0];
		mark[0] = true;
		for (int i = 1; i < N; i++) {
			relationship[0][i] = 1;
		}
		
		for (int i = 1; i < N; i++) {
			findSub(i);
		}

		coloring(0);
	}
}
