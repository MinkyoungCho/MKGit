/*
 * �Ǽ��� �κ�!! big int(10�ڸ�) * big int ==> ������� int������ ����� ���� �̻�����
 * ���� (long: 19�ڸ����� ����)���� ĳ������ �� ���������� 
 */
	
package CG_Chess;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Solution4 {
	static final int MOD = 1000000007;
	static int Answer;
	
	static int[] factCache = new int[200001]; // ���� �׽�Ʈ���̽� ������ ���
	static int[] inverseCache = new int[200001];

	public static void main(String args[]) throws Exception	{
// 		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		factorial(); // �̸� ����س���!! 
		inverseFactorial();
		
		for(int test_case = 0; test_case < T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			
			ArrayList<Obstacle> obstacles = new ArrayList<>();
			int numOfObstacles = 0;
			
			for (int i = 0; i < K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				if (x < N + 1 && y < M + 1) {
					obstacles.add(new Obstacle(x, y));
				}
			}
			Collections.sort(obstacles);
			obstacles.add(new Obstacle(N, M));
			numOfObstacles = obstacles.size();
			int[] numOfPurePath = new int[numOfObstacles]; // �� �ε������� "��ֹ��� ��ġ�� �ʰ�" ���� ����� ��
			
			// ���� ���� : ��ü - ��ֹ��� �ִ� ����� ������
			for (int i = 0; i < numOfObstacles; i++) {
				int x = obstacles.get(i).x;
				int y = obstacles.get(i).y;
				int total = combi(x + y - 2, x - 1); // ��ü �������� �� ��ֹ��� ���� �� ����
//				System.out.println(x + " " + y + " " + total);
				for (int j = i - 1; j > -1; j--) {
					int tempX = obstacles.get(j).x;
					int tempY = obstacles.get(j).y;
					if (tempY <= y) {
						total -= ((long) numOfPurePath[j] * combi(x - tempX + y - tempY, x - tempX)) % MOD;
					}
				}
				
				numOfPurePath[i] = total;
			}
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(numOfPurePath[numOfObstacles - 1]);
		}
	}
	
	//�޺���̼� rCk ��� 
	static int combi(int r, int k) {
		long combi = ((long) factCache[r] * inverseCache[k]) % MOD; 
		combi = (combi * inverseCache[r - k]) % MOD;
		
		return (int) combi;
	}
	
	static void factorial() {
		factCache[0] = 1;
		long temp = factCache[0];
		for (int i = 1; i < 200001; i++) {
			temp = (temp * i) % MOD;
			factCache[i] = (int) temp;
		}
	}
	
	static void inverseFactorial() {
		long temp = newPow(factCache[200000], 1000000005); // �丣���� ������
		for (int i = 200000; i > -1; i--) {
			inverseCache[i] = (int) temp;
			temp = (temp * i) % MOD; 
		}
	}
	
	static int newPow(int a, int b) { // log���� ������ ���� ����
		if (b == 1) {
			return a;
		} 
		
		int temp = newPow(a, b / 2);
		long res = temp;
		res = (res * res) % MOD;
		if (b % 2 == 1) {
			res = (res * a) % MOD;
		}
		return (int) res;
	}
	
	static class Obstacle implements Comparable<Obstacle> {
		int x;
		int y;
		 
		public Obstacle(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Obstacle o) { // �������� ����
			if (x != o.x) {
				return (x - o.x); 
			} else {
				return (y - o.y);
			}
		}
	}
}