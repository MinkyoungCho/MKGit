/*
 * ������ ����:
 * TestCase �� hashmap, secondMap�� ����!!!
 */

package Microbe;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

class Solution {
	static int Answer;
	static int N, M, K;
	static int[][] map;
	static HashMap<Integer, Crowd> hashmap;
	static HashMap<Integer, Crowd> secondMap;
	
	public static void main(String args[]) throws Exception	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			Answer = 0;
			
			N = sc.nextInt(); //map ũ��
			M = sc.nextInt(); //�ݸ��ð�
			K = sc.nextInt(); //������
			hashmap = new HashMap<>(); 
			secondMap = new HashMap<>();
			
			//���� -> HashMap���� ǥ��
			for (int i = 0; i < K; i++) {
				int x = sc.nextInt(), y = sc.nextInt();
				int num = sc.nextInt(), direction = sc.nextInt();
				
				hashmap.put(x * N + y, new Crowd(num, direction, num));
			}
			
			//M �ð� �� -> M�� ���� ����
			for (int i = 0; i < M; i++) {
				Iterator iter = hashmap.keySet().iterator();
				
				while (iter.hasNext()) {
					int tempKey = (int) iter.next();
					int x = tempKey / N, y = tempKey % N;
					Crowd tempVal = hashmap.get(tempKey);
					
					//�̵�
					switch (tempVal.direction) {
					case 1: //��
						if (x == 1) {
							tempVal.num = tempVal.num / 2;
							tempVal.direction = 2;
							secondMap.put(y, new Crowd(tempVal.num, tempVal.direction, tempVal.num));
						} else {
							insert((x - 1) * N + y, tempVal);
						}
						break;
						
					case 2: //��
						if (x == N - 2) {
							tempVal.num = tempVal.num / 2;
							tempVal.direction = 1;
							secondMap.put((N - 1) * N + y, new Crowd(tempVal.num, tempVal.direction, tempVal.num));
						} else {
							insert((x + 1) * N + y, tempVal);
						}
						break;
						
					case 3: //��
						if (y == 1) {
							tempVal.num = tempVal.num / 2;
							tempVal.direction = 4;
							secondMap.put(x * N, new Crowd(tempVal.num, tempVal.direction, tempVal.num));
						} else {
							insert(x * N + (y - 1), tempVal);
						}
						break;
						
					case 4: //��
						if (y == N - 2) {
							tempVal.num = tempVal.num / 2;
							tempVal.direction = 3;
							secondMap.put(x * N + N - 1, new Crowd(tempVal.num, tempVal.direction, tempVal.num));
						} else {
							insert(x * N + (y + 1), tempVal);
						}
						break;
						
					}
				
				}
				
				HashMap<Integer, Crowd> tempMap = new HashMap<>();
				hashmap = secondMap;
				secondMap = tempMap;
			}
			
			
			//�̻��� �� �� ���ϱ�(iter)
			Iterator iter = hashmap.keySet().iterator();
			while (iter.hasNext()) {
				Answer += hashmap.get(iter.next()).num;
			}
			
			// Print the answer to standard output(screen).
			System.out.print("#" + (test_case+1) + " ");
			System.out.println(Answer);
		}
	}
	
	static void insert(int key, Crowd val) { // �ڵ� �ٿ����� ��������!
		int num = val.num, direction = val.direction, biggest = num;
		if (secondMap.containsKey(key)) {
			if (num < secondMap.get(key).biggest) {
				biggest = secondMap.get(key).biggest;
				direction = secondMap.get(key).direction;
			}
			num += secondMap.get(key).num;
			
		} 
		
		secondMap.put(key, new Crowd(num, direction, biggest));
	}
	
	static class Crowd {
		int num;
		int direction;
		int biggest;
		
		public Crowd(int num, int direction, int biggest) {
			this.num = num;
			this.direction = direction;
			this.biggest = biggest;
		}
	}
}