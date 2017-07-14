package Practice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class TSP2 { // Traveling Salesman Problem
	
	double[][] matrix;
	double minDist = 100000; // TSP �Ÿ� �� �ּ� �Ÿ� ����
	
	public TSP2(double[][] matrix) {
		this.matrix = matrix;
	}
	
	public void findNextNode(int node, double distance, boolean[] selectedNodes) {
		boolean[] backup = new boolean[selectedNodes.length]; //����صα�!! (��Ϳ��� �ſ�ſ�ſ�ſ�ſ� ���� �Ǽ� ���ϴ� �κ�!)
		for (int i = 0; i < selectedNodes.length; i++) {
			backup[i] = selectedNodes[i];
		}

		selectedNodes[node] = true;
		
		for (int i = 0; i <= selectedNodes.length; i++) { // bound �˻�
			if (i == selectedNodes.length) { // ��� �湮
				distance += matrix[node][0]; // node�� ������ �湮 ����̰� ù��° ��ġ�� ���ƿ;��Ѵ�
				if (distance < minDist) {
					minDist = distance;
				}
				return;
			}
			if (!selectedNodes[i]) { // ���� �湮 ���� ��� ����
				break;
			}
		}
		
		// ���� ��忡 �����ϰ� �湮���� ���� ��� ���� ���� ��� ȣ��
		for (int i = 0; i < selectedNodes.length; i++) {
			if (!selectedNodes[i] && matrix[node][i] > 100) {
				findNextNode(i, distance + matrix[node][i], selectedNodes);
				
				for (int j = 0; j < selectedNodes.length; j++) {
					selectedNodes[i] = backup[i]; // selectedNode�� ��������Ƿ�! �����ϱ�
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
