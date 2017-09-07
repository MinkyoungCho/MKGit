/*
 * ��!!!!!!!! ���� ����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * Table Filling ��� - HashMap dp[][] ���
 * 3�� ������ key, 2�� ������ value
 * 
 * [�ذ���]
 * ���� ���� ���� but ArrayList�� �ƴ϶� Map ���
 * ���� ArrayList�� ��쿣 left, up���κ����� �� list�κ��� �ߺ���Ҹ� Ȯ���ϱ� ����
 * �� list ���� ������ ���� "binary search & merge & sort" ���� --> ������� ŭ
 * But! 
 * Map ���� �����, containsKey()�� �ѹ��� �ߺ�������� check ����!!
 * 
 * 3�� ������ �� ĭ�� �ִ� 6(3^6 < 1000 && 3^7 > 1000), length of route = 200�̹Ƿ� 1200 ������!
 * but 1200�� �������ѵ� ��� �޸� ���� ũ�⶧���� ArrayList �Ǵ� Map ����� ����ؾ� �޸� ���� ���� �� �ִ�.
 * 
 * �� ������ ���� "������ �ڷᱸ�� ���"�� �߿伺�� ���޾���!
 * - �ڷᱸ���� Ư���� ���� ������尡 ũ�� ��ų� �۰� �� 
 * ũ�� �� ��� TLE �ʷ�(Table Filling ����� DP�� ����Ѵ� ������...)
 * 
 */

package CG_RoomGame;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Solution5 {
	static int Answer;
	static int N;
	static Score[][] matrix; // 100 * 100
	static HashMap<Integer, Integer>[][] dp; //�迭 ��� ��, ����[1200]��ŭ �����ϰ� ���� �����ϴ� ���� ���� �� ����
	static int maxCount = -1;
	
	// *** ���ϴ� �Ǽ�: ArrayList �̸����� ����ϸ鼭 ������ �ʰ� �����ϱ�!
	
	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			matrix = new Score[N + 1][N + 1];
			dp = new HashMap[N + 1][N + 1]; // Hashmap���ε� ���� (<Integer, Integer>�̸� �ϳ��� 2, �ϳ��� 3�� ������!)
			
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					matrix[i][j] = new Score(sc.nextInt());
					dp[i][j] = new HashMap<>();
				}
			}
			
			dp[1][1].put(matrix[1][1].three, matrix[1][1].two);
			
			Iterator iterator;
			int two, three, temp;
			for (int i = 2; i < N + 1; i++) {
				two = matrix[1][i].two;
				three = matrix[1][i].three;
				iterator = dp[1][i - 1].keySet().iterator();
				
				while (iterator.hasNext()) {
					temp = (int) iterator.next();
					dp[1][i].put(temp + three, dp[1][i - 1].get(temp) + two);
				}
			}
			
			for (int i = 2; i < N + 1; i++) {
				two = matrix[i][1].two;
				three = matrix[i][1].three;
				iterator = dp[i - 1][1].keySet().iterator();

				while (iterator.hasNext()) {
					temp = (int) iterator.next();
					dp[i][1].put(temp + three, dp[i - 1][1].get(temp) + two);
				}
			}
			
			for (int i = 2; i < N + 1; i++) {
				for (int j = 2; j < N + 1; j++) {
					two = matrix[i][j].two;
					three = matrix[i][j].three;
					iterator = dp[i][j - 1].keySet().iterator();

					while (iterator.hasNext()) {
						temp = (int) iterator.next();
						dp[i][j].put(temp + three, dp[i][j - 1].get(temp) + two);
					}
					
					iterator = dp[i - 1][j].keySet().iterator();
					while (iterator.hasNext()) {
						temp = (int) iterator.next();
						int tempKey = temp + three;
						if (dp[i][j].containsKey(tempKey)) {
							if ((dp[i - 1][j].get(temp)+ two) > dp[i][j].get(tempKey)) {
								dp[i][j].put(tempKey, (dp[i - 1][j].get(temp)+ two));
							} 
						} else {
							dp[i][j].put(tempKey, (dp[i - 1][j].get(temp)+ two));
						}
					}
				}
			}
			
			Answer = -1;
			for (int i : dp[N][N].keySet()) {
				int res = Math.min(i, dp[N][N].get(i));
				if (res > Answer) {
					Answer = res;
				}
			}
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
			
		}

	}

	static class Score { // �� �濡�� ȹ���ϴ� ���� = 2^a * 3^ b * k
		int two = 0, three = 0; // a
			
		public Score(int n) {
			while (n % 6 == 0) {
				two ++;
				three ++;
				n = n / 6;
			}
			
			while (n % 2 == 0) {
				two ++;
				n = n / 2;
			}
			
			while (n % 3 == 0) {
				three ++;
				n = n / 3;
			}
		}
	}
}
