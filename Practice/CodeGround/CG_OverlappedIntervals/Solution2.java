/*
 * ArrayList + 재귀 = 메모리 부족!
 */

package CG_OverlappedIntervals;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Solution2 {
	static int Answer;
	static int numOfIntervals;
	static ArrayList<Interval> intervals;
	static int[] cache;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
 		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			numOfIntervals = sc.nextInt();
			intervals = new ArrayList<>();
			cache = new int[numOfIntervals];
			
			for (int i = 0; i < numOfIntervals; i++) { // 저장
				intervals.add(new Interval(sc.nextInt(), sc.nextInt()));
			}
			
			Collections.sort(intervals); // <길이>로 내림차순 정렬
			
			Answer = selectNext(0);
			
			System.out.println("Case #" + (test_case + 1));
			System.out.println(Answer);	
		}
	}
	
	static int selectNext(int index) { // [index .. last]에서  최대 중첩 수 리턴 
		if (index == numOfIntervals) {
			return 0; //기본적으로 1개는 존재
		}
		
		if (cache[index] > 0) { // DP
			return cache[index];
		}
		
		Interval i1 = intervals.get(index);
		Interval i2;
		int next;
		
		// 다음 가능 노드 선택
		for (next = index + 1; next < numOfIntervals; next++) {
			i2 = intervals.get(next);
			
			if (i1.begin <= i2.begin) {
				if (i1.end >= i2.end) {
					break;
				}
			}
		}
		System.out.println("index: " + index + " next: " + next);
		int res = 1 + selectNext(next);
		res = Math.max(res, selectNext(index + 1));
		
		cache[index] = res;
		return res;
	}
	
	static class Interval implements Comparable<Interval> { 
		int begin;
		int end;
		
		public Interval(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}

		@Override
		public int compareTo(Interval target) {
			int len1 = end - begin;
			int len2 = target.end - target.begin;
			if (len1 < len2) {
				return 1;
			} else if (len1 > len2) {
				return -1;
			}
			return 0;
		}
		
		
	}

}