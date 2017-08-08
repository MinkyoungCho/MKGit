package ShortestPath;

import java.util.PriorityQueue;

class Node implements Comparable<Node> {
	int node;
	int dist; // 정렬 기준, 오름차순
	
	public Node(int node, int dist) {
		this.node = node;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node target) { 
		if (dist > target.dist) {
			return 1;
		} else if (dist < target.dist) {
			return -1;
		}
		return 0;
	}
}

public class Dijkstra {
	int[][] graph;
	int numOfNodes;
	int numOfEdges;
	int begin;
	int end;
	
	public static int MAX_INT = 9999999;
	
	public Dijkstra(int[][] graph, int numOfNodes, int numOfEdges, int begin, int end) {
		this.graph = graph;
		this.numOfNodes = numOfNodes;
		this.numOfEdges = numOfEdges;
		this.begin = begin;
		this.end = end;
	}
	
	/*
	 * 모든 정점이 선택될 때가지 다음을 반복!(n번 반복)
	 * 1) <최소거리>를 가지는 노드 선택 
	 * 	  -> 최소힙사용해서 계산효율성 up
	 * 2) 거리 행렬 업데이트
	 */
	
	public int dijkstra() {
		int[] dist = new int[numOfNodes + 1]; // 각 노드까지의 최단 거리 저장
		boolean[] mark = new boolean[numOfNodes + 1];
		mark[0] = true;
		for (int i = 0; i < dist.length; i++) {
			if (i == begin) {
				dist[i] = 0;
			} else {
				dist[i] = MAX_INT;
			}
		}
		
		// Node = {인접노드, 인접노드까지의 거리}
		PriorityQueue<Node> minheap = new PriorityQueue<>(); 
		for (int j = 1; j < dist.length; j++) {
			minheap.add(new Node(j, dist[j]));
		}
		
		while (!minheap.isEmpty()) { // n번 반복
			
			// 1. <최소거리> 가지는 노드 선택
			Node currentNode = minheap.poll(); // 효율성 UPUP!
			if (currentNode.node == end) { // 목적지가 poll 되는 경우
				return currentNode.dist;
			}
			if (mark[currentNode.node]) {
				continue;
			} 
			mark[currentNode.node] = true; // 마크
			
			
			// 2. 거리 행렬 업데이트
			for (int j = 1; j <= numOfNodes; j++) { // 비교: 현재 최단 거리 vs 새로운 트리노드에의해 업데이트 된 거리 
				if (!mark[j] && graph[currentNode.node][j] < 1000 && (dist[j] > (currentNode.dist + graph[currentNode.node][j]))) {
					dist[j] = currentNode.dist + graph[currentNode.node][j];
					minheap.add(new Node(j, dist[j]));
				}
			}
		}
		return  -1 ;
	}
}
