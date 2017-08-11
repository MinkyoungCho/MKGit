/*
 * Array + 재귀 = 메모리 부족!
 */

package CG_OverlappedIntervals;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import CG_OverlappedIntervals.Solution2.Interval;

class Solution1 {
	static int Answer;
	static int numOfIntervals;
	static int[][] intervals;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			numOfIntervals = sc.nextInt();
			intervals = new int[numOfIntervals][2];
			
			for (int i = 0; i < numOfIntervals; i++) { // 저장
				intervals[i][0] = sc.nextInt();
				intervals[i][1] = sc.nextInt();
			}
			
			Arrays.sort(intervals, new Desc()); // <길이>로 내림차순 정렬
			Answer = selectNext(0);
			System.out.println("Case #" + (test_case + 1));
			System.out.println(Answer);	
		}
	}
	
	static int selectNext(int index) { // [index .. last]에서  최대 중첩 수 리턴 
		int[] cache = new int[numOfIntervals];
		
		if (index == numOfIntervals) {
			return 1; //기본적으로 1개는 존재
		}
		
		if (cache[index] >= 1) { // DP
			return cache[index];
		}
		
		int res = -1;
		
		// Backtracking
		// 현재꺼 선택
		for (int next = index + 1; next < numOfIntervals; next++) {
			if (intervals[index][0] <= intervals[next][0]) {
				if (intervals[index][1] >= intervals[next][1]) {
					res = Math.max(res, 1 + selectNext(next));
					
				}
			}
		}
		
		// 현재꺼 선택 x
		res = Math.max(res, selectNext(index + 1));
		
		cache[index] = res;
		return res;
	}
	
	static class Desc implements Comparator { // 길이 내림차순 정렬

		@Override
		public int compare(Object o1, Object o2) {
			int[] a1= (int[]) o1; // Integer[]은 안됨! int[]로 캐스팅해야함!!! (신기해)
			int[] a2= (int[]) o2;
			
			Integer len1 = new Integer(a1[1] - a1[0]); // int --> Integer
			Integer len2 = new Integer(a2[1] - a2[0]);
			
			return len2.compareTo(len1); // 내림 차순!
		}
		
	}
}