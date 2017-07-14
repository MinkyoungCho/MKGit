package Practice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class TSP2 { // Traveling Salesman Problem
	
	double[][] matrix;
	double minDist = 100000; // TSP 거리 중 최소 거리 저장
	
	public TSP2(double[][] matrix) {
		this.matrix = matrix;
	}
	
	public void findNextNode(int node, double distance, boolean[] selectedNodes) {
		boolean[] backup = new boolean[selectedNodes.length]; //백업해두기!! (재귀에서 매우매우매우매우매우 내가 실수 잘하는 부분!)
		for (int i = 0; i < selectedNodes.length; i++) {
			backup[i] = selectedNodes[i];
		}

		selectedNodes[node] = true;
		
		for (int i = 0; i <= selectedNodes.length; i++) { // bound 검사
			if (i == selectedNodes.length) { // 모두 방문
				distance += matrix[node][0]; // node가 마지막 방문 노드이고 첫번째 위치로 돌아와야한다
				if (distance < minDist) {
					minDist = distance;
				}
				return;
			}
			if (!selectedNodes[i]) { // 아직 방문 안한 노드 존재
				break;
			}
		}
		
		// 현재 노드에 인접하고 방문하지 않은 모든 노드들 대해 재귀 호출
		for (int i = 0; i < selectedNodes.length; i++) {
			if (!selectedNodes[i] && matrix[node][i] > 100) {
				findNextNode(i, distance + matrix[node][i], selectedNodes);
				
				for (int j = 0; j < selectedNodes.length; j++) {
					selectedNodes[i] = backup[i]; // selectedNode가 물들었으므로! 복구하기
				}
			}
		}
		
		
	}
}

public class Algospot_TSP2 {

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
			
			TSP2 tsp = new TSP2(matrix);
			boolean[] selectedNodes = new boolean[sizeOfMatrix];
			tsp.findNextNode(0, 0, selectedNodes);
			System.out.println(tsp.minDist);
		}
	}

}
