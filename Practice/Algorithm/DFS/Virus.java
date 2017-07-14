package DfsBasic;

import java.util.Scanner;

class DFS {
	boolean[] mark;
	Node[] graph;
	int count = 0;
	
	public void dfs(int v) {
		v = v - 1;
		mark[v] = true;
		
		for (Node w = graph[v].nodeLink; w != null; w = w.nodeLink) {
			if (!mark[w.data - 1]) {
				dfs(w.data);
				count++;
			}
		}
	}
}

public class Virus {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numOfNodes = scanner.nextInt();
		int numOfEdges = scanner.nextInt();
		String garbage = scanner.nextLine();
		DFS dfs = new DFS();
		dfs.mark = new boolean[numOfNodes];
		dfs.graph = new Node[numOfNodes];
		for (int i = 0; i < numOfNodes; i++) {
			Node node = new Node(i + 1);
			dfs.graph[i] = node;
		}
		
		for (int i = 0; i < numOfEdges; i++) { // input 받으며 인접리스트 생성
			String input = scanner.nextLine();
			String[] inputs = input.split(" ");
			
			Node node = new Node(Integer.parseInt(inputs[1]));
			Node lastNode = dfs.graph[Integer.parseInt(inputs[0]) - 1];
			while (lastNode.nodeLink != null) {
				lastNode = lastNode.nodeLink;
			}
			lastNode.nodeLink = node;
			
			node = new Node(Integer.parseInt(inputs[0])); // 쌍방향
			lastNode = dfs.graph[Integer.parseInt(inputs[1]) - 1];
			while (lastNode.nodeLink != null) {
				lastNode = lastNode.nodeLink;
			}
			lastNode.nodeLink = node;
		}
		
		dfs.dfs(4);
		System.out.println(dfs.count);
		
	}
}
