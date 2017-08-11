// ArrayList + BFS방식(큐 + 반복) = Time Limit Exceed (TLE)
package CG_OverlappedIntervals;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.w3c.dom.Node;

import CG_OverlappedIntervals.Solution2.Interval;


class Solution3 {
	static int Answer;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			int numOfIntervals = sc.nextInt();
			ArrayList<Interval> intervals = new ArrayList<>();
			Queue<Node> queue = queue = new LinkedList<>();
			
			for (int i = 0; i < numOfIntervals; i++) { // 저장
				intervals.add(new Interval(sc.nextInt(), sc.nextInt()));
				queue.add(new Node(i, 1));
			}
			Collections.sort(intervals); // <길이>로 오름차순 정렬

			int max = -1;
			
			while (!queue.isEmpty()) {
				Node current = queue.poll();
				if (current.count > max) {
					max = current.count;
				}
				if (current.index < numOfIntervals) {
					for (int i = current.index + 1; i < numOfIntervals; i++) {
						Interval temp = intervals.get(i);
						if (intervals.get(current.index).end >= intervals.get(i).end) {
								queue.add(new Node(i, current.count + 1));
						}
					}
				}
			}
			
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
			if (this.begin == target.begin) {
				return target.end - this.end; // end에 대해선 내림차순!
			} else {
				return this.begin - target.begin; // begin에 대해선 오름차순!
			}
		}
	}
	
	static class Node {
		int index;
		int count;
		
		public Node(int index, int count) {
			this.index = index;
			this.count = count;
		}
	}

}