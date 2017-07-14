package DfsBasic;

class GroupFinder {
	int[][] matrix;
	
	public int findGroups() {
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					count ++;
						
//					boolean[][] mark = new boolean[matrix.length][matrix[0].length];
					findPath(i, j);
				}
			}
		}
		
		return count;
	}
	
	public void findPath(int x, int y) { // 각 재귀노드에서의 상태에 관한 변수들, (x, y)지점
		matrix[x][y] = 0;
		
		if ((x - 1 >= 0) && (matrix[x - 1][y] == 1)) { // backtracking: if promising, go!
			findPath(x - 1, y);
		}
		if ((x + 1 < matrix.length) && (matrix[x + 1][y] == 1)) {
			findPath(x + 1, y);
		}
		if ((y - 1 >= 0) && (matrix[x][y - 1] == 1)) { // backtracking: if promising, go!
			findPath(x, y - 1);
		}
		if ((y + 1 < matrix[0].length) && (matrix[x][y + 1] == 1)) {
			findPath(x, y + 1);
		}
	}
}

public class GroupOf1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GroupFinder gf = new GroupFinder();
		gf.matrix = new int[][]{{1, 1, 0, 0, 1}, {1, 0, 0, 1, 0}, {1, 1, 1, 1, 0}, {0, 0, 0, 0, 1}};
		System.out.println(gf.findGroups());
	}

}
