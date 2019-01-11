package ¿¹»ê;

import java.util.Scanner;

public class Main {
	static int avg = -1;
	
	public static void sortArray(int[] array) {
		int min = -1;
		
		for (int i = 0; i < (array.length - 1); i++) {
			min = i; 
			
			for (int j = i + 1; j < array.length; j++) {
				if (array[min] > array[j]) {
					min = j;
				}
			}
			
			int temp = array[i];
			array[i] = array[min];
			array[min] = temp; 
		}
	}
	
	public static int findMax(int N, int[] array, int M, int idx) {
		int ret = 0;
		avg = array[N - 1];
		
		if (idx == N) {
			return 0;
		} 

		int sum = 0;
		for (int i = idx; i < N; i++) {
			sum += array[i];
		}
		if (M > sum) {
			return sum;
		} else {
			avg = M / (N - idx);
			for (int j = idx; j < N; j++) {
				if (array[j] <= avg) {
					M -= array[j];
					ret += array[j];
				} else {
					if (j != idx) {
						return ret + findMax(N, array, M, j);
					} else {
						break;
					}
				}
			}
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); 
		int[] array = new int[N]; 
		
		for (int area = 0; area < N; area++) {
			array[area] = sc.nextInt();
		}
		
		int M = sc.nextInt(); 
	
		sortArray(array);
		findMax(N, array, M, 0);
		
		System.out.println(avg);
	}

}
