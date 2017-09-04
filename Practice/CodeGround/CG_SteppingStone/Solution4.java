/*
 * iterative dfs + memoization
 * 1) �ڽĵ��� ��� �����Ҷ����� ��ٸ�
 * 2) �ڽ� + �ڽ��� ���� ������ �θ����� �˸���
 * 
 */

package CG_SteppingStone;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

class Solution4 {
	static int Answer;
	static int N, K, L;
	static boolean[] obstacles;

	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			L = sc.nextInt();
			boolean[][] cacheForDone = new boolean[N + 1][K + 1]; // �ڽĵ��� ������ ���� & ��ΰ� true�� �Ǹ� �θ����� �˸���
			int[] cacheForCount = new int[N + 1];
			
			obstacles = null;
			Answer = 0;

			if (L > 0) {
				obstacles = new boolean[N + 1];
				for (int i = 0; i < L; i++) {
					int temp = sc.nextInt();
					obstacles[temp] = true;
				}
			}
			
			Stack<Movement> stack = new Stack<>();
			for (int i = 1; i <= K; i++) {
				if ((L == 0) && (i <= N)) {
					stack.add(new Movement(i, 0, i));
				} else if ((L != 0) && !obstacles[i] && (i <= N)) {
					stack.add(new Movement(i, 0, i)); //�ƽ���� �Ķ���ͷ� �ֱ�
				}
			}
			
			while (!stack.isEmpty()) {
				Movement current = stack.pop();
				System.out.println(current.currentStone);
				System.out.println("cacheForCount[i]");
				for (int i = 0; i < K + 1; i++) {
					System.out.print(cacheForCount[i] + "  ");
					
				}				
				System.out.println();

				if (current.currentStone == N) {
					cacheForDone[current.parentStone][current.numOfMoving] = true;
					cacheForCount[current.parentStone] = 1;
					continue;
				}
				
				//DP check
				boolean isDone = true; 
				for (int i = 0; i < K + 1; i++) {
					isDone = isDone && cacheForDone[current.currentStone][i];
					
				}
				if (isDone) { // �ش���� ������ �̹� ��� ��
					System.out.println("DP SUCCESS@@@");
					cacheForDone[current.parentStone][current.numOfMoving] = true; // �θ� update
					cacheForCount[current.parentStone] += cacheForCount[current.currentStone]; 
					continue;
				}
				
				
				Arrays.fill(cacheForDone[current.currentStone], true);
				for (int i = 1; i <= K; i++) {
					int candidate = current.currentStone + i;
					if ((L == 0) && (current.numOfMoving != i) && (candidate <= N)) {
						cacheForDone[current.currentStone][i] = false; // ���ο� �ڽ� 
						stack.add(new Movement(candidate, current.currentStone, i));
					} if ((L > 0) && !obstacles[candidate] && (current.numOfMoving != i) && (candidate <= N)) {
						cacheForDone[current.currentStone][i] = false;
						stack.add(new Movement(candidate, current.currentStone, i));
					}
				}
				
				cacheForDone[current.parentStone][current.numOfMoving] = true;
				cacheForCount[current.parentStone] += cacheForCount[current.currentStone];
						
			}
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(cacheForCount[0]);
		}

	}
	
	static class Movement {
		int currentStone;
		int parentStone;
		int numOfMoving;
		
		Movement(int currentStone, int parentStone, int numOfMoving) {
			this.currentStone = currentStone;
			this.parentStone = parentStone;
			this.numOfMoving = numOfMoving;
		}
	}
}