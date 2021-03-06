/*
 * 2.0025s, 10점
 * Table Filling 방식
 */
package CG_RoomGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution4 {
	static int Answer;
	static int N;
	static Score[][] matrix;
	static ArrayList<Score>[][] dp;
	static int maxCount = -1;
	
	// *** 잘하는 실수: ArrayList 이리저리 계산하면서 섞이지 않게 조심하기!
	
	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			matrix = new Score[N + 1][N + 1];
			dp = new ArrayList[N + 1][N + 1]; // Hashmap으로도 가능 (<Integer, Integer>이면 하나는 2, 하나는 3의 개수로!)
			
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					matrix[i][j] = new Score(sc.nextInt());
					dp[i][j] = new ArrayList<>();
				}
			}
			
			dp[1][1].add(matrix[1][1]);
			
			for (int i = 2; i < N + 1; i++) {
				dp[1][i].add(new Score(dp[1][i - 1].get(0).two + matrix[1][i].two, dp[1][i - 1].get(0).three + matrix[1][i].three));
				dp[i][1].add(new Score(dp[i - 1][1].get(0).two + matrix[i][1].two, dp[i - 1][1].get(0).three + matrix[i][1].three));
			}
			
			
			for (int i = 2; i < N + 1; i++) {
				for (int j = 2; j < N + 1; j++) {
					ArrayList<Score> mergedList = new ArrayList<>();
					
					for (Score s : dp[i - 1][j]) {
						mergedList.add(s);
					}
					
					int start = 0;
					for (Score s : dp[i][j - 1]) {
						for (int k = start; k < mergedList.size(); k++) {
							if (s.two < mergedList.get(k).two) {
								mergedList.add(k, s);
								break;
							} else if (s.two == mergedList.get(k).two) {
								if (s.three < mergedList.get(k).three) {
									s.three = mergedList.get(k).three;
								}
								start = k + 1;
								break;
							} else {
								if (k == mergedList.size()) {
									mergedList.add(s);
									start = mergedList.size() - 1;
								}
							}
						}
					}
					Collections.sort(mergedList);
					
					for (Score s : mergedList) {
						s.two = s.two + matrix[i][j].two;
						s.three = s.three + matrix[i][j].three;
					}
					
					if (i == N && j == N) {
						Answer = -1;
						for (Score s : mergedList) {
							int currentCount = Math.min(s.two, s.three);
							if (currentCount > maxCount) {
								Answer = currentCount;
							}
						}
					} else { 
						dp[i][j] = mergedList;
					}
				}
			}
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
			
		}

	}

	static class Score implements Comparable<Score> { // 각 방에서 획득하는 점수 = 2^a * 3^ b * k
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
			
		public Score(int two, int three) {
			this.two = two;
			this.three = three;
		}

		@Override
		public int compareTo(Score other) {
			return this.two - other.two; // 오름차순
		}
	}
}
