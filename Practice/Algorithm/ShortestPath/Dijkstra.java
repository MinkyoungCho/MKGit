package ShortestPath;

import java.util.PriorityQueue;

class Node implements Comparable<Node> {
	int node;
	int dist; // ���� ����, ��������
	
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
	 * ��� ������ ���õ� ������ ������ �ݺ�!(n�� �ݺ�)
	 * 1) <�ּҰŸ�>�� ������ ��� ���� 
	 * 	  -> �ּ�������ؼ� ���ȿ���� up
	 * 2) �Ÿ� ��� ������Ʈ
	 */
	
	public int dijkstra() {
		int[] dist = new int[numOfNodes + 1]; // �� �������� �ִ� �Ÿ� ����
		boolean[] mark = new boolean[numOfNodes + 1];
		mark[0] = true;
		for (int i = 0; i < dist.length; i++) {
			if (i == begin) {
				dist[i] = 0;
			} else {
				dist[i] = MAX_INT;
			}
		}
		
		// Node = {�������, ������������ �Ÿ�}
		PriorityQueue<Node> minheap = new PriorityQueue<>(); 
		for (int j = 1; j < dist.length; j++) {
			minheap.add(new Node(j, dist[j]));
		}
		
		while (!minheap.isEmpty()) { // n�� �ݺ�
			
			// 1. <�ּҰŸ�> ������ ��� ����
			Node currentNode = minheap.poll(); // ȿ���� UPUP!
			if (currentNode.node == end) { // �������� poll �Ǵ� ���
				return currentNode.dist;
			}
			if (mark[currentNode.node]) {
				continue;
			} 
			mark[currentNode.node] = true; // ��ũ
			
			
			// 2. �Ÿ� ��� ������Ʈ
			for (int j = 1; j <= numOfNodes; j++) { // ��: ���� �ִ� �Ÿ� vs ���ο� Ʈ����忡���� ������Ʈ �� �Ÿ� 
				if (!mark[j] && graph[currentNode.node][j] < 1000 && (dist[j] > (currentNode.dist + graph[currentNode.node][j]))) {
					dist[j] = currentNode.dist + graph[currentNode.node][j];
					minheap.add(new Node(j, dist[j]));
				}
			}
		}
		return  -1 ;
	}
}
