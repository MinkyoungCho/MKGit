package CG_Sequence;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

class Solution {
	static int Answer;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int numOfItems = sc.nextInt();
			long[] items = new long[numOfItems];
			long minDiff = Long.MAX_VALUE;
			
			for (int i = 0; i < numOfItems; i++) {
				items[i] = sc.nextLong();
				
				if (i > 0) {
					minDiff = Math.min(minDiff, items[i] - items[i - 1]);
				}
			}
			
			ArrayList<Long> list = new ArrayList<>();
			for (long i = 1; i < minDiff + 1; i++) {
				int j;
				for (j = 1; j < numOfItems; j++) {
					if (((items[j] - items[0]) % i) != 0) {
						break;
					}
				}
				if (j == numOfItems) {
					list.add(i);
				}
 			}
			
			// 공차 0인 경우
			int i;
			for (i = 1; i < numOfItems; i++) {
				if (items[i - 1] - items[i] != 0) {
					break;
				} 
			}
			
			if (i == numOfItems) {
				list.add((long) 0);
			}
			

			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(list.size());
		}
	}
}