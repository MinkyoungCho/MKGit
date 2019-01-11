package 정비소;

import java.util.Scanner;

public class Main {
	
	public static void copyArray(boolean[] from, boolean[] to) {
		for (int i = 0; i < from.length; i++) {
			to[i] = from[i];
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int maxDist = sc.nextInt();
		int numOfShops = sc.nextInt();
		int[] position = new int[numOfShops + 2];
		int[] duration = new int[numOfShops + 2];
		
		for (int i = 1; i < numOfShops + 2; i++) {
			position[i] = position[i - 1] + sc.nextInt();
		}
		
		if (position[numOfShops + 1] <= maxDist) {
			System.out.println(0);
			return;
		}
			
		for (int i = 1; i <= numOfShops; i++) {
			duration[i] = sc.nextInt();
		}
		
		int[] table = new int[numOfShops + 2]; //DP
		int[] visit = new int[numOfShops + 2];
		visit[0] = 0;
		boolean[][] mark = new boolean[numOfShops + 2][numOfShops + 2];
		mark[0][0] = true;
		
		for (int i = 1; i <= numOfShops + 1; i++) { //기준
			table[i] = 1000000000;
			
			for (int j = i - 1; j >= 0; j--) { //140 이내 지점 중 min 정비시간 찾기
				if (position[i] - position[j] <= maxDist) {
					if(table[j] + duration[i] < table[i]) {
						table[i] = table[j] + duration[i];
						visit[i] = visit[j] + 1;
						copyArray(mark[j], mark[i]);
						mark[i][i] = true;
					}
				} else {
					break;
				}
			}
		}
		
		System.out.println(table[numOfShops + 1]);
		System.out.println(visit[numOfShops + 1] - 1);
		
		for (int i = 1; i < numOfShops + 1; i++) {
			if (mark[numOfShops + 1][i]) {
				System.out.print(i +  " ");
			}
		}
		
	}

}
