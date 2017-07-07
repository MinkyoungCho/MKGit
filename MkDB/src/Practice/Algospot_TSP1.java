package Practice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class TSP1 { // Minimum Spanning Tree
	
	double[][] matrix;
	boolean[] selectedNodes;
	double distance = 0;
	
	public TSP1(double[][] matrix) {
		this.matrix = matrix;
		selectedNodes = new boolean[this.matrix.length];
	}
	
	public int findMinNode() {
		double minDist = 10000;
		int minIndex = -1;
		
		for (int i = 0; i < selectedNodes.length; i++) { //i: 이미 선택된 노드
			if (selectedNodes[i]) {
				for (int j = i + 1; j < selectedNodes.length; j++) { //j: 선택될 후보 노드
					if (matrix[i][j] != 0 && !selectedNodes[j]) { 
						if (matrix[i][j] < minDist) {
							minDist = matrix[i][j];
							minIndex = j;
						}
					}
				}
			}
		}
		
		distance += minDist;
		
		return minIndex;
	}
	
	// 해당 node를 select하고, 인접 노드들 중 가장 가까운 node 찾아서 재귀호출
	// 모든 node select 시 거리 합 저장
	public void makeMST(int node) { // 이번차례에 선택된 노드, 총 거리 
		selectedNodes[node] = true;
		
		int i; 
		for (i = 0; i < selectedNodes.length; i++) {
			if (!selectedNodes[i]) {
				break;
			}
		}
		if (i == selectedNodes.length) {
			return;
		}
		
		//최소 거리 인접 노드 찾고, 찾자마자 distance 변수 즉시 업데이트하기 
		int minNode = findMinNode();
		makeMST(minNode);
	}
}

public class Algospot_TSP1 {

	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		int testCase = Integer.parseInt(reader.readLine());
		for (int i = 0; i < testCase; i++) {
			int sizeOfMatrix = Integer.parseInt(reader.readLine());
			double[][] matrix = new double[sizeOfMatrix][sizeOfMatrix];
			
			for (int j = 0; j < sizeOfMatrix; j++) {
				line = reader.readLine();
				String[] inputs = line.split("  ");
				
				for (int  k = 0; k < sizeOfMatrix; k++) {
					matrix[j][k] = Double.parseDouble(inputs[k]);
				}
			}
			
			TSP1 tsp = new TSP1(matrix);
			tsp.makeMST(0);
			System.out.println(tsp.distance);
			
		}
	}

}
