// ArrayList��� �ּҰ� �ѱ�� ������ static���� ���ϰ� local�� �Ѵ��ص�.. ȣ��� �������� ���� ������ ���: ���� static�� ��������.. �迭�̳� ArrayList, map�ǰ�� ũ�� ũ�ϱ�!
// �ѹ��� ȣ���� ������ <<<ȣ����� ����� ó�� ����>>>�� �ǵ����� �ʼ�!!!: ���� �ǰ��� or ��������� �ʱ�ȭ
// ����� ������ local��-->  �Լ�ȣ�⿡ �̿��ϸ� ���� ��!! 

package Lunch;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution2 {
	static int N, Answer;
	static int[][] map;
	static int numOfPeople;
	static Point[] stairs = new Point[2];
	static Point stairPointer;
	static ArrayList<Point> people;
	static ArrayList<Point> stair1= new ArrayList<>(); 
	static ArrayList<Point> stair2 = new ArrayList<>();
	
	static class Point{
		int row, col, len;
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		Point(int row, int col, int len) {
			this.row = row;
			this.col = col;
			this.len = len;
		}
	}
	
	static int calc(ArrayList<Point> stair, int mode) {
		int stairR, stairC;
		int timeToStair = 0;
		int timeInStair = 0;
				
		if (mode == 1) {
			stairPointer = stairs[0];
			timeInStair = stairs[0].len + 1;
			stairR = stairs[0].row;
			stairC = stairs[0].col;
		} else {
			stairPointer = stairs[1];
			timeInStair = stairs[1].len + 1;
			stairR = stairs[1].row;
			stairC = stairs[1].col;
		}
		
		if (stair.size() == 0) {
			return 0;		
		}
		
		if (stair.size() < 4) {
			int max = -1;
			for (Point people : stair) {
				timeToStair = Math.abs(people.row - stairR) + Math.abs(people.col - stairC);
				max = Math.max(max, timeToStair);
			}
			
			return (timeInStair + max);
			
		} else {
			int[] time = new int[stair.size()];
			for (int i = 0; i < stair.size(); i++) {
				Point p = stair.get(i);
				time[i] = Math.abs(p.row - stairR) + Math.abs(p.col - stairC);
			}
			Arrays.sort(time);
			int rmCount = 0;
			ArrayList<Integer> queue = new ArrayList<>();
			
			int timeForMany = 0;
			while (rmCount < stair.size()) {
				timeForMany++;
				int start = rmCount;
				for (int i = start; i < stair.size(); i++) {
					if (time[i] > -1) {
						time[i] = time[i] - 1;
					} else {
						if (queue.contains(i)) {
							if (time[i] == -1 * (stairPointer.len)) {
								time[i] = time[i] - 1;
								queue.remove(queue.indexOf(i));
								rmCount ++;
								if (rmCount == stair.size()) {
									break;
								}
							} else {
								time[i] = time[i] - 1;
							}
						} else {
							if (queue.size() < 3) {
								queue.add(i);
								time[i] = time[i] - 1;
							} 
						}
					}
				}
			}
			return timeForMany;
		}
	}
	
	static int goDown(int person) {
		if (person == numOfPeople) {
			int res1 = calc(stair1, 1);
			int res2 =  calc(stair2, 2);
//			System.out.println(res1 + " " + res2);
			return Math.max(res1, res2);
		} 
		
		Point curr = people.get(person);
		
	//��� 1
		stair1.add(new Point(curr.row, curr.col));
		int res = goDown(person + 1);
	// ��� 1 ��
		
		stair1.remove(stair1.get(stair1.size() - 1)); // ���(ȣ��� ù ���·� �ʱ�ȭ)
		
	//���2
//		int res = 10;
		stair2.add(new Point(curr.row, curr.col));
		res = Math.min(res, goDown(person + 1));
	//���2 ��

		stair2.remove(stair2.get(stair2.size() - 1)); // ���(ȣ��� ù ���·� �ʱ�ȭ)
		
		return res;
	}
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			people = new ArrayList<>();
			N = sc.nextInt();
			map = new int[N + 1][N + 1];
			int s = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] > 1) {
						stairs[s++] = new Point(i, j, map[i][j]); // 0, 1
					} else if (map[i][j] == 1){
						people.add(new Point(i, j)); // 0 ~ 5
					}
				}
			}
			numOfPeople = people.size();
			Answer = goDown(0);
			
			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(Answer);
		}
	}
}