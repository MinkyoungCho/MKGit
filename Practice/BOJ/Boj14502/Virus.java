package Bj14502;

class Virus {
	int[][] matrix;
	boolean[][] cache;
	int numOfSafe = 0; // �ʱ� �������� ����
	
	public Virus(int[][] matrix) {
		this.matrix = matrix;
		cache = new boolean[matrix.length][matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					numOfSafe++;
					cache[i][j] = true; // ���� ����
				}
			}
		}
		numOfSafe -= 3;
		System.out.println(numOfSafe);
		
	}
	
	int minOfInfection = 1000; //���� ���� ���� 
	
	public Point findNextPoint(int x, int y) {
		for (int i = x; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if ((i == x && j <= y) || matrix[i][j] == 1 || matrix[i][j] == 2) {
					continue;
				}
				return new Point(i, j);
			}
		}
		return null;
	}
	
	//�� ���� ��ġ�ϴ� ��� ���� ã�� -> ���� ����
	public void placeWalls() {
		Point first = findNextPoint(0, -1);
		Point second = findNextPoint(first.x, first.y);
		Point third = findNextPoint(second.x, second.y);
		
		while (first != null) {
			while (second != null) {
				while (third != null) {
					int count = 0;
					
						//�ϳ��� ��Ȳ���� ���̷����� �������� �������� ����
//						System.out.println((count + 1) + ": [1]" + first.x + first.y + " [2]" + second.x + second.y + " [3]" + third.x + third.y);
						int res = spreadVirus(first, second, third);
						if (res < minOfInfection) {
							minOfInfection = res;
						}
						third = findNextPoint(third.x, third.y);
					
				}
				second = findNextPoint(second.x, second.y);
				if (second != null) {
					third = findNextPoint(second.x, second.y);
				}
			}
			
			first = findNextPoint(first.x, first.y);
			if (first != null) {
				second = findNextPoint(first.x, first.y);
			}
			if (second != null) {
				third = findNextPoint(second.x, second.y);
			}
		}
		
//		return numOfSafe - minNumOfInfection;
	}
	
	public void copyMatrix(int[][] from, int[][] to) {
		for (int i = 0; i < from.length; i++) {
			for (int j = 0; j < from[0].length; j++) {
				to[i][j] = from[i][j];
			}
		}
	}
	
	public void copyMatrix(boolean[][] from, boolean[][] to) { // �����ε�
		for (int i = 0; i < from.length; i++) {
			for (int j = 0; j < from[0].length; j++) {
				to[i][j] = from[i][j];
			}
		}
	}
	
	public int spreadVirus(Point first, Point second, Point third) {
		// �ش� Params�� �� ��ġ������ ��ȿ�ؾ��ϴ� Matrix�����ϹǷ�! tmp ���� ���
		int[][] tmpMatrix = new int[matrix.length][matrix[0].length];
		boolean[][] tmpCache = new boolean[matrix.length][matrix[0].length];
		copyMatrix(matrix, tmpMatrix);
		copyMatrix(cache, tmpCache);
		
		// �� ��ġ 
		tmpMatrix[first.x][first.y] = 1;
		tmpMatrix[second.x][second.y] = 1;
		tmpMatrix[third.x][third.y] = 1;
		tmpCache[first.x][first.y] = false;
		tmpCache[second.x][second.y] = false;
		tmpCache[third.x][third.y] = false;
		
		int infection = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 2) { // ���̷��� �ִ� ���� 
					infection += DFS.dfs(tmpMatrix, tmpCache, i, j);
//					infection += BFS.bfs(tmpMatrix, tmpCache, i, j);
				}
			}
		}
		
		if (infection == 1) {
			System.out.println("[1]" + first.x + first.y + " [2]" + second.x + second.y + " [3]" + third.x + third.y);

			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					System.out.print(tmpCache[i][j] + "  ");
				}
				System.out.println();
			}
		}
		return infection;
	}
	
	public int getSafeNum() {
		return (numOfSafe - minOfInfection);
		
	}
}
