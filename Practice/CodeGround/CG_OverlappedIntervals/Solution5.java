/*
 * Array + 반복 + 큐 사용 x -- 4.00197로 TLE
 * 도대체 어떻게 줄이지...
 * 
 */

package CG_OverlappedIntervals;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Solution5 {
	static int Answer;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int numOfIntervals = sc.nextInt();
			Interval[] intervals = new Interval[numOfIntervals];
			for (int i = 0; i < numOfIntervals; i++) {
				intervals[i] = new Interval(sc.nextInt(), sc.nextInt());
			}
			Arrays.sort(intervals);
			
			int max = -1;
			for (int i = 0; i < numOfIntervals; i++) {
				Interval current = intervals[i];
				int[] currentNum = new int[numOfIntervals];
				int[] maxNum = new int[numOfIntervals];
				currentNum[i] = 1;
				maxNum[i] = 1;
				
				for (int j = i + 1; j < numOfIntervals; j++) {
					currentNum[j] = -1;
					maxNum[j] = maxNum[j - 1];
					
					if (current.end < intervals[j].end) {
						currentNum[j] = -1;
						maxNum[j] = maxNum[j - 1];
						break;
					}
					
					for (int k = j - 1; k >= i; k--) {
						if (intervals[j].end <= intervals[k].end) { // 조건 만족
							if (maxNum[j - 1] == currentNum[k]) { // k항이 최대개수 집합에 포함된 마지막 항
								currentNum[j] = maxNum[j - 1] + 1;
								maxNum[j] = maxNum[j - 1] + 1;
								break;
							} else {
								currentNum[j] = currentNum[k] + 1;
								maxNum[j] = maxNum[j - 1];
							}
						} 
					} 
				}
				
				if (maxNum[numOfIntervals - 1] > max) { // 최대값 저장
					max = maxNum[numOfIntervals - 1];
				}
				
				if (max >= numOfIntervals - i) { // 최대 개수 >= 남은 개수 --> 더 이상 볼 필요 없음!
					break;
				}
			}

			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(max);
		}
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
			if (this.begin != target.begin) {
				return this.begin - target.begin;
			} else {
				return target.end - this.end;
			}
		}
	}
}