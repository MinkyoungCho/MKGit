/*
 * 축!!!!!!!! 드디어 만점!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * Table Filling 방식 - HashMap dp[][] 사용
 * 3의 개수가 key, 2의 개수가 value
 * 
 * [해결방법]
 * 기존 로직 동일 but ArrayList가 아니라 Map 사용
 * 기존 ArrayList인 경우엔 left, up으로부터의 두 list로부터 중복요소를 확인하기 위해
 * 한 list 원소 각각에 대해 "binary search & merge & sort" 수행 --> 오버헤드 큼
 * But! 
 * Map 으로 변경시, containsKey()로 한번에 중복요소인지 check 가능!!
 * 
 * 3의 개수는 한 칸에 최대 6(3^6 < 1000 && 3^7 > 1000), length of route = 200이므로 1200 이하임!
 * but 1200을 고정시켜둘 경우 메모리 낭비 크기때문에 ArrayList 또는 Map 사용을 사용해야 메모리 낭비를 줄일 수 있다.
 * 
 * 이 문제를 통해 "적절한 자료구조 사용"의 중요성을 깨달았음!
 * - 자료구저의 특성에 따라 오버헤드가 크게 들거나 작게 듦 
 * 크게 들 경우 TLE 초래(Table Filling 방식의 DP를 사용한다 할지라도...)
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
	static HashMap<Integer, Integer>[][] dp; //배열 사용 시, 공간[1200]만큼 선언하고 공간 낭비하는 것을 막을 수 있음
	static int maxCount = -1;
	
	// *** 잘하는 실수: ArrayList 이리저리 계산하면서 섞이지 않게 조심하기!
	
	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			matrix = new Score[N + 1][N + 1];
			dp = new HashMap[N + 1][N + 1]; // Hashmap으로도 가능 (<Integer, Integer>이면 하나는 2, 하나는 3의 개수로!)
			
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

	static class Score { // 각 방에서 획득하는 점수 = 2^a * 3^ b * k
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
