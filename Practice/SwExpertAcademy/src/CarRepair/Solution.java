package CarRepair;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

class Solution {
	static int N, M, K, A, B, Answer;
	static int[][] receptionD, repairD; // ù��° row: ó�� �ð�, �ι�° row: �ش� �ڸ��� �����ϴ� �� num
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
	
	static int select(int[][] arr) { // ù ��ĭ �ε��� ����, ��ĭ ������ -1 ����
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
			A = sc.nextInt(); // �������� üũ
			B = sc.nextInt(); 
			receptionD = new int[2][N + 1]; // ù���� row�� ó�� �ð�, �ι��� row�� �� �ѹ�
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
					if (per.time == time) { // ����
						int desk = select(receptionD); // -1 �̸� �� �� ����
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