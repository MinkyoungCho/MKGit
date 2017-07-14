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
		
		for (int i = 0; i < selectedNodes.length; i++) { //i: �̹� ���õ� ���
			if (selectedNodes[i]) {
				for (int j = i + 1; j < selectedNodes.length; j++) { //j: ���õ� �ĺ� ���
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
	
	// �ش� node�� select�ϰ�, ���� ���� �� ���� ����� node ã�Ƽ� ���ȣ��
	// ��� node select �� �Ÿ� �� ����
	public void makeMST(int node) { // �̹����ʿ� ���õ� ���, �� �Ÿ� 
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
		
		//�ּ� �Ÿ� ���� ��� ã��, ã�ڸ��� distance ���� ��� ������Ʈ�ϱ� 
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
