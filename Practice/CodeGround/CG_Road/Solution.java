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
		Scanner scanner = new Scanner(new FileInputStream("input.txt")); // 우와~~ 이렇게 해도 되는구나!!!!!!! 짱짱 편하다!!!!!!

		int T = scanner.nextInt();
		for (int test_case = 1; test_case < T + 1; test_case++) {

            int numOfNodes = scanner.nextInt();
			int numOfEdges = scanner.nextInt();
			ArrayList<Node>[] neighbor = (ArrayList<Node>[]) new ArrayList[numOfNodes +  1]; // 공간 효율성 up!
			
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
	
			// 하나의 노드를 시작점으로 해서 Dijkstra알고리즘 수행
			for (int start = 1; start <= numOfNodes; start++) {
				
				PriorityQueue<Node> heap = new PriorityQueue<>(); // 최단거리 선택용 -> 계산 효율성 up! 
				ArrayList<Integer>[] front = (ArrayList<Integer>[]) new ArrayList[numOfNodes + 1]; // front 노드 저장 배열 (if 1개 or 2개 이상 check)
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
								
				// Dijkstra: 최단거리 노드 선택하기(최소힙) - 거리 업데이트하기 --> n번 반복
				while (!heap.isEmpty()) {  // 그래프의 노드가 모두 선택될때까지 반복!
					// 1. 노드 선택
					Node tmp = heap.poll(); 
					if (mark[tmp.node]) {
						continue;
					}
					mark[tmp.node] = true; 
				
					// 2. 업데이트
					for (Node adjacent : neighbor[tmp.node]) {
						if (!mark[adjacent.node] && (dist[adjacent.node] > (dist[tmp.node] + adjacent.dist))) {
							dist[adjacent.node] = dist[tmp.node] + adjacent.dist;
							heap.add(new Node(adjacent.node, dist[adjacent.node])); // 힙에 새로운 최단거리 추가
							front[adjacent.node].clear();
							front[adjacent.node].add(tmp.node);
						} else if (!mark[adjacent.node] && (dist[adjacent.node] == (dist[tmp.node] + adjacent.dist))) {
							// 최단거리가 이미 heap에 들어갔으므로 다시 추가할 필요 없음
							front[adjacent.node].add(tmp.node);
						}
					}
				} // 모든 노드에 대해 최단경로 계산
				
		
				for (ArrayList<Integer> ar : front) {
					if (ar.size() == 1) {
						int frontNode= ar.get(0); // 막으면 안될 노드
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
	
	static class Node implements Comparable<Node> { // 최소힙에 Object type을 넣을 경우 -> 어떤 인스턴스 변수를 기준으로 정렬할지 말해야함!
		int node;
		int dist; // 정렬 기준
		
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