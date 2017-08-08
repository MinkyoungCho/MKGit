/*
 * 최단 거리 구하기 알고리즘
 * Dijkstra - Greedy approach
 * Min Heap을 사용하여 계산 효율성/코드 가독성 높이기!
 *  
 */

package ShortestPath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DijkstraMain {

	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		line = reader.readLine();
		String[] inputs = line.split(" ");
		
		int numOfNodes = Integer.parseInt(inputs[0]);
		int begin = Integer.parseInt(inputs[1]);
		int end = Integer.parseInt(inputs[2]);
		int[][] graph = new int[numOfNodes + 1][numOfNodes + 1]; 
				
		int numOfEdges = Integer.parseInt(reader.readLine());
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				graph[i][j] = 1000;
			}
		}
		
		for (int i = 0; i < numOfEdges; i++) {
			line = reader.readLine();
			inputs = line.split(" ");
			
			graph[Integer.parseInt(inputs[0])][Integer.parseInt(inputs[1])] = Integer.parseInt(inputs[2]);
		}
		
		Dijkstra dijk = new Dijkstra(graph, numOfNodes, numOfEdges, begin, end);
		System.out.println(dijk.dijkstra());
	}

}
