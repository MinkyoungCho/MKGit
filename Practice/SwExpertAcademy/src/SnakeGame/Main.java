package SnakeGame;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class Main {
	static int L, N, Answer;
	static int row, col, dir = 0;
	static HashMap<Integer, ArrayList<Interval>> rMap, cMap;
	static int lastTime = 0;

	
	static class Interval {
		int start, end;
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	static void changeDir(char move) {
		if (move == 'L') {
			dir = (dir + 1) % 4;
		} else {
			dir = (dir + 3) % 4;
		}
		
	}
	
	static boolean canMove(int fix, int small, int large, int start, int mode) {//mode: 0이면 rMap비교, 1이면 cMap비교
		lastTime = 1000;
		Iterator iter;
		int snakePoint;
		if (mode == 0) {
			iter = rMap.keySet().iterator();
		} else {
			iter = cMap.keySet().iterator();

		}
			
		while (iter.hasNext()) {
			int tempKey = (int) iter.next();
			if (start == tempKey) {
				continue;
			}
			if (tempKey >= small && tempKey <= large) {
				if (mode == 0) {
					for (Interval i : rMap.get(tempKey)) {
						if (i.start <= fix && fix <= i.end) {
							lastTime = Math.min(lastTime, Math.abs(tempKey - row));
							return false;
						}
					}
				} else {
					for (Interval i : cMap.get(tempKey)) {
						if (i.start <= fix && fix <= i.end) {
							lastTime = Math.min(lastTime, Math.abs(tempKey - col));
							return false;
						}
					}
				}
			} 
		}
		return true;
	}
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));
		L = sc.nextInt(); // -L ~ L
		N = sc.nextInt();
		
		rMap = new HashMap<>();
		cMap = new HashMap<>();
		int duration = 0;
		boolean stop = false;
		
		for (int i = 0; i < N; i++) {
			int time = sc.nextInt();
			char move =  sc.next().charAt(0);
			
			if (stop) {
				break;
			}

			switch (dir) {
			case 0:
				if (col + time > L) { //벽 충돌 -> 종료
					System.out.println(duration + (L - col + 1));
					stop= true;
					break;
				}
				
				if(canMove(row, col, col + time, col, 1)) { // 몸통 충돌 안하면 true, 충돌시 false 리턴
					ArrayList<Interval> temp;
					if (rMap.containsKey(row)) {
						temp = rMap.get(row);
					} else {
						temp = new ArrayList<Interval>();
					}
					temp.add(new Interval(col, col + time));
					rMap.put(row, temp);
					col = col + time;
					changeDir(move);
					duration += time;
				} else {
					
					System.out.println(duration + lastTime);
					stop = true;
				}
				break;
				
			case 1:
				if (row + time > L) { //벽 충돌 -> 종료
					System.out.println(duration + (L - row + 1));
					stop = true;
					break;
				}
				if(canMove(col, row, row + time, row, 0)) { // 몸통 충돌 안하면 true, 충돌시 false 리턴
					ArrayList<Interval> temp;
					if (cMap.containsKey(col)) {
						temp = cMap.get(col);
					} else {
						temp = new ArrayList<Interval>();
					}
					temp.add(new Interval(row, row + time));
					cMap.put(col, temp);
					changeDir(move);
					row = row + time;
					duration += time;
				} else {
					System.out.println(duration + lastTime);
					stop = true;
				}
				break;
				

			case 2:
				if (col - time < -1 * L) { //벽 충돌 -> 종료
					System.out.println(duration + (col + L + 1));
					stop = true;
					break;
				}
				if(canMove(row, col - time, col, col, 1)) { // 몸통 충돌 안하면 true, 충돌시 false 리턴
					ArrayList<Interval> temp;
					if (rMap.containsKey(row)) {
						temp = rMap.get(row);
					} else {
						temp = new ArrayList<Interval>();
					}
					temp.add(new Interval(col - time, col));
					rMap.put(row, temp);
					col = col - time;
					changeDir(move);
					duration += time;
				} else {
					System.out.println(duration + lastTime);
					stop = true;
				}
				break;
				

			case 3:
				if (row - time < -1 * L) { //벽 충돌 -> 종료
					System.out.println(duration + (row + L + 1));
					stop = true;
					break;
				}
				if(canMove(col, row - time, row, row, 0)) { // 몸통 충돌 안하면 true, 충돌시 false 리턴
					ArrayList<Interval> temp;
					if (cMap.containsKey(col)) {
						temp = cMap.get(col);
					} else {
						temp = new ArrayList<Interval>();
					}
					temp.add(new Interval(row - time, row));
					cMap.put(col, temp);
					row = row - time;
					changeDir(move);
					duration += time;
				} else {
					System.out.println(duration + lastTime);
					stop = true;
				}
				break;
			}
			
		}
		
	}
}