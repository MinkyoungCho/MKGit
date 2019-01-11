package CarRepair;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

class Solution {
	static int N, M, K, A, B, Answer;
	static int[][] receptionD, repairD; // 첫번째 row: 처리 시간, 두번째 row: 해당 자리를 차지하는 고객 num
	static ArrayList<Person> arriveTime = new ArrayList<>();
	static ArrayList<Integer> receptionQ = new ArrayList<>();
	static ArrayList<Integer> repairQ = new ArrayList<>();
	
	static class Person {
		int num, time;
		Person(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}
	
	static int select(int[][] arr) { // 첫 빈칸 인덱스 리턴, 빈칸 없으면 -1 리턴
		for (int i = 1; i < arr[0].length; i++) {
			if (arr[1][i] == 0) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt(); 
			K = sc.nextInt();
			A = sc.nextInt(); // 마지막에 체크
			B = sc.nextInt(); 
			receptionD = new int[2][N + 1]; // 첫번쨰 row는 처리 시간, 두번쨰 row는 고객 넘버
			for (int i = 1; i <= N; i++) {
				receptionD[0][i] = sc.nextInt();
			}
			repairD = new int[2][M + 1];
			for (int i = 1; i <= M; i++) {
				repairD[0][i] = sc.nextInt();
			}
			for (int i = 1; i <= K; i++) {
				int time = sc.nextInt();
				arriveTime.add(new Person(i, time));
			}
			
			int rmCount = 0, time = 0;
			while (rmCount <= K) {
				for (Person per : arriveTime) {
					if (per.time == time) { // 도착
						int desk = select(receptionD); // -1 이면 빈 곳 없음
						if (desk == -1) {
							receptionQ.add(per.num);
						} else {
							receptionD[1][desk] = per.num;
						}
					}
				}
			}

			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(Answer);
		}
	}
}