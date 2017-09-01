/*
 * 실행시간 : 2.00264, 메모리: 1236032, 점수: 30
 * Backtracking + Memoization : Recursive dfs 로 구현 

 * (N, N) -> (1, 1)로 가며 가능한 루트들 알아내고,
 * (1, 1) -> (N, N)로 돌아오며 0 개수 계산 및 ArrayList에 중간결과 저장
 *   --> 최소값이 아니라 '일일이 저장'하는 이유: 현재 최선이 아닌 것이 나중에 최선이 될 수 있으므로!!
 */

package CG_RoomGame;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Solution2 {
	static int Answer;
	static int N;
	static Score[][] matrix;
	static ArrayList<Score>[][] dp;
	static int maxCount = -1;
	
	public static void main(String args[]) throws Exception	{

//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			matrix = new Score[N + 1][N + 1];
			dp = new ArrayList[N + 1][N + 1];
			
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					matrix[i][j] = new Score(sc.nextInt());
					dp[i][j] = new ArrayList<>();
				}
			}

			dp[1][1].add(matrix[1][1]);
			ArrayList<Score> res = dfs(N, N); 
			Answer = -1;

			for (Score s : res) {
				int minVal = Math.min(s.two, s.three);
				if (minVal > Answer) {
					Answer = minVal;
				}
			}
		
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
	
	static ArrayList<Score> dfs(int x, int y) { 
		
		if (x == 1 && y == 1) {
			return dp[1][1];
		}
		
		if (dp[x][y].size() > 0) { // null이면 dp[x][y].size()함수 실행 시  NullPointerException 난다!!
			return dp[x][y];
		}
		
		ArrayList<Score> list1 = new ArrayList<>(); // null로 초기화 x
		ArrayList<Score> list2 = new ArrayList<>();
		ArrayList<Score> mergedList = new ArrayList<>(); // dp[][]에 저장된 array list랑 절대 섞이면 안됨! -> 새롭게 arraylist만들어서 이걸로 계산 수행 및 리턴!
		
		if (x > 1) {
			list1 = dfs(x - 1, y); // from UP
		}
		if (y > 1) {
			list2 = dfs (x, y - 1); // from LEFT
		}

		list1 = merge(list1, list2);
		for (Score s : list1) {
			mergedList.add(new Score(s.two + matrix[x][y].two, s.three + matrix[x][y].three));
		}
		dp[x][y] = mergedList;
		
		return mergedList;
		
	}
	
	static ArrayList<Score> merge(ArrayList<Score> l1, ArrayList<Score> l2) { // list2를 list1에 merge
		if (l1.size() == 0) {
			return l2;
		}
		
		if (l2.size() == 0) {
			return l1;
		} 
		
		for (Score s : l2) {
			l1.add(s);
		}
		
		return l1;
	
	}
	
	static class Score { // 각 방에서 획득하는 점수 = 2^a * 3^ b * k
		int two = 0, three = 0; // a
		
		public Score(int n) {
			int temp = n;
			
			while (temp % 2 == 0) {
				two ++;
				temp = temp / 2;
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
	}
}