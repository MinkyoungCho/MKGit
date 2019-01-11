package Lunch;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Solution {
	static int Answer;
	static int N, numOfPeople;
	static int[][] map;
	static ArrayList<Point> people = new ArrayList<>();
	static Point[] stairs = new Point[3];
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			Answer = 10000;
			N = sc.nextInt();
			map = new int[N + 1][N + 1];
			
			int s = 1;
			people.add(new Point(-1, -1));
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1) {
						people.add(new Point(i, j));
					} else if (map[i][j] > 1){
						stairs[s] = new Point(i, j, map[i][j]);
						s ++;
					}
				}
			}
			numOfPeople = people.size() - 1;
			
			//사람-계단 매칭
			//	시간계산 - 최소시간 저장
			ArrayList<Integer> temp = new ArrayList<>();
			match(1, 1, temp, new ArrayList<>()); // 1번 사람 - 1번 계단
			match(1, 2, new ArrayList<>(), temp); // 1번 사람 - 2번 계단
			
			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(Answer);
		}
	}
	
	//나중에 dp섞기
	static void match(int p, int s, ArrayList<Integer> waiting1, ArrayList<Integer> waiting2) { 
		//bound -> 시간계산
		if (p == numOfPeople + 1) {
			int time1 = measureTime(waiting1, 1);
			int time2 = measureTime(waiting2, 2);
			Answer = Math.min(Math.max(time1, time2), Answer);
			return;
		}
		
		ArrayList<Integer> temp1 = new ArrayList<>(), temp2 = new ArrayList<>();
		for (int p1 : waiting1) {
			temp1.add(p1);
		}
		temp1.add(p);
		
		for (int p2 : waiting2) {
			temp2.add(p2);
		}
		temp2.add(p);
		
		//재귀호출
		match(p + 1, 1, temp1, waiting2);
		match(p + 1, 2, waiting1, temp2);
	}
	
	static int measureTime(ArrayList<Integer> waiting, int stair) {
		int res = 0, move = -1;
		int x = stairs[stair].x, y = stairs[stair].y, l = stairs[stair].l;
		if (waiting.size() < 4) {
			res = 1 + stairs[stair].l; // 대기 및 계단 내려가기
			for (int i : waiting) {
				move = Math.max(move, Math.abs(people.get(i).x - x) + Math.abs(people.get(i).y - y));
			}
		
			return (res + move);
		} 
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int i : waiting) {
			list.add(Math.abs(people.get(i).x - x) + Math.abs(people.get(i).y - y) + 1);
		}
		Collections.sort(list);
		
		int count = 0;//계단에 있는 사람 수
		boolean flag = true; //계단 갈 수 있음
		int time = 0;
		
		while (!list.isEmpty()) {
			time ++;
			for (int j = 0; j < list.size(); j++) {
				int curr = list.get(j);
				if (curr > 0) {
					list.remove(j);
					list.add(j, curr - 1);
				} else if (curr == 0) {
					if (flag) {
						list.remove(j);
						list.add(j, -1);
						count ++;
						if (count == 3) {
							flag = false;
						}
					} 
				} else {
					if (curr - 1 == -1 * l) {
						list.remove(j);
						j = -1;
						count --;
						flag = true;
					} else {
						list.remove(j);
						list.add(j, curr - 1);
					}
					}
			}
		}
		
		return time;
	}
	
	static class Point {
		int x, y, l;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		Point(int x, int y, int l) {
			this.x = x;
			this.y = y;
			this.l = l;
		}

	}
}