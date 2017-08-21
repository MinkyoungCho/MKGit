package CG_Road;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Solution {
	static int Answer;

	public static void main(String args[]) throws Exception	{
//		Scanner scanner = new Scanner(System.in);
		Scanner scanner = new Scanner(new FileInputStream("input.txt")); // ���~~ �̷��� �ص� �Ǵ±���!!!!!!! ¯¯ ���ϴ�!!!!!!

		int T = scanner.nextInt();
		for (int test_case = 1; test_case < T + 1; test_case++) {

            int numOfNodes = scanner.nextInt();
			int numOfEdges = scanner.nextInt();
			ArrayList<Node>[] neighbor = (ArrayList<Node>[]) new ArrayList[numOfNodes +  1]; // ���� ȿ���� up!
			
			boolean[] markForAns = new boolean[numOfNodes + 1];
			int ansCount = 0;
			
			for (int j = 0; j < neighbor.length; j++) {
				neighbor[j] = new ArrayList<>();
			}
			
			for (int j = 0; j < numOfEdges; j++) {
				int begin = scanner.nextInt();
				int end = scanner.nextInt();
				int dist = scanner.nextInt();
				neighbor[begin].add(new Node(end, dist));
				neighbor[end].add(new Node(begin, dist));
				
			}
	
			// �ϳ��� ��带 ���������� �ؼ� Dijkstra�˰��� ����
			for (int start = 1; start <= numOfNodes; start++) {
				
				PriorityQueue<Node> heap = new PriorityQueue<>(); // �ִܰŸ� ���ÿ� -> ��� ȿ���� up! 
				ArrayList<Integer>[] front = (ArrayList<Integer>[]) new ArrayList[numOfNodes + 1]; // front ��� ���� �迭 (if 1�� or 2�� �̻� check)
				for (int j = 0; j < front.length; j++) {
					front[j] = new ArrayList<>();
				}
				
				boolean[] mark = new boolean[numOfNodes + 1];
				int[] dist = new int[numOfNodes + 1];
				
				Arrays.fill(dist, Integer.MAX_VALUE);
				dist[start] = 0;
				for (int j = 1; j < numOfNodes + 1; j++) {
					heap.offer(new Node(j, dist[j]));
				}
								
				// Dijkstra: �ִܰŸ� ��� �����ϱ�(�ּ���) - �Ÿ� ������Ʈ�ϱ� --> n�� �ݺ�
				while (!heap.isEmpty()) {  // �׷����� ��尡 ��� ���õɶ����� �ݺ�!
					// 1. ��� ����
					Node tmp = heap.poll(); 
					if (mark[tmp.node]) {
						continue;
					}
					mark[tmp.node] = true; 
				
					// 2. ������Ʈ
					for (Node adjacent : neighbor[tmp.node]) {
						if (!mark[adjacent.node] && (dist[adjacent.node] > (dist[tmp.node] + adjacent.dist))) {
							dist[adjacent.node] = dist[tmp.node] + adjacent.dist;
							heap.add(new Node(adjacent.node, dist[adjacent.node])); // ���� ���ο� �ִܰŸ� �߰�
							front[adjacent.node].clear();
							front[adjacent.node].add(tmp.node);
						} else if (!mark[adjacent.node] && (dist[adjacent.node] == (dist[tmp.node] + adjacent.dist))) {
							// �ִܰŸ��� �̹� heap�� �����Ƿ� �ٽ� �߰��� �ʿ� ����
							front[adjacent.node].add(tmp.node);
						}
					}
				} // ��� ��忡 ���� �ִܰ�� ���
				
		
				for (ArrayList<Integer> ar : front) {
					if (ar.size() == 1) {
						int frontNode= ar.get(0); // ������ �ȵ� ���
						if (frontNode != start && !markForAns[frontNode]) {
							markForAns[frontNode] = true;
							ansCount++;
						}
					}
				}				
			}
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case));
			System.out.print(ansCount);
			if (ansCount == 0) {
				break;
			}
			
			for (int j = 1; j < markForAns.length; j++) {
				if (markForAns[j]) {
					System.out.print(" ");
					System.out.print(j);
				}
			}		
			System.out.println();
		}
	}
	
	static class Node implements Comparable<Node> { // �ּ����� Object type�� ���� ��� -> � �ν��Ͻ� ������ �������� �������� ���ؾ���!
		int node;
		int dist; // ���� ����
		
		public Node(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node target) {
			// TODO Auto-generated method stub
			if (dist > target.dist) {
				return 1;
			} else if (dist < target.dist) {
				return -1;
			}
			return 0;
		}
	}
}