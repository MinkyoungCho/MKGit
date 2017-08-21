/*
 * 실수한 부분!! big int(10자리) * big int ==> 결과값이 int범위를 벗어나가 값이 이상해짐
 * 따라서 (long: 19자리까지 가능)으로 캐스팅한 후 계산해줘야함 
 */
	
package CG_Chess;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Solution4 {
	static final int MOD = 1000000007;
	static int Answer;
	
	static int[] factCache = new int[200001]; // 다음 테스트케이스 때에도 사용
	static int[] inverseCache = new int[200001];

	public static void main(String args[]) throws Exception	{
// 		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		factorial(); // 미리 계산해놓기!! 
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
			int[] numOfPurePath = new int[numOfObstacles]; // 각 인덱스까지 "장애물을 거치지 않고" 가는 경로의 수
			
			// 메인 로직 : 전체 - 장애물이 있는 경로의 합집합
			for (int i = 0; i < numOfObstacles; i++) {
				int x = obstacles.get(i).x;
				int y = obstacles.get(i).y;
				int total = combi(x + y - 2, x - 1); // 전체 개수에서 한 장애물씩 검토 후 빼기
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
	
	//콤비네이션 rCk 계산 
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
		long temp = newPow(factCache[200000], 1000000005); // 페르마의 소정리
		for (int i = 200000; i > -1; i--) {
			inverseCache[i] = (int) temp;
			temp = (temp * i) % MOD; 
		}
	}
	
	static int newPow(int a, int b) { // log만에 지수승 연산 가능
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
		public int compareTo(Obstacle o) { // 오름차순 정렬
			if (x != o.x) {
				return (x - o.x); 
			} else {
				return (y - o.y);
			}
		}
	}
}