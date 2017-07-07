package Practice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

class ShortestPath {
	double[][] matrix;
	boolean[] mark;
	
	public ShortestPath(double[][] matrix) {
		this.matrix = matrix;
		mark = new boolean[matrix.length];
	}
	
	double minDist = 10000000;
	
	public void findNextNode(int currentNode, int destination, double distance) {
		
		//마크 & 백업 -> 자주 실수하는 부분!
		mark[currentNode] = true; // 현재 노드 마킹하기 (안하면 스택오버플로우에러 발생)
		boolean[] backup = new boolean[mark.length]; // 이전 재귀 호출과 독립적으로 가야하므로 -> 백업!
		for (int i = 0; i < mark.length; i++) {
			backup[i] = mark[i];
		}
		
		if (currentNode == destination) {
			if (distance < minDist) {
				minDist = distance;
			}
			return;
		}
		
		//인접한거 따라가기!
		for (int i = 0; i < matrix.length; i++) {
			if (!mark[i] && matrix[currentNode][i] > 0 && (distance + matrix[currentNode][i] < minDist)) {
				findNextNode(i, destination, distance + matrix[currentNode][i]);
				
				for (int j = 0; j < mark.length; j++) {
					mark[j] = backup[j];
				}
			}
		}
	}
}

public class ShortestPathProblem {

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
			
			ShortestPath sp = new ShortestPath(matrix);
			sp.findNextNode(0, matrix.length - 1, 0);
			System.out.println(sp.minDist);
		}
	}

}
