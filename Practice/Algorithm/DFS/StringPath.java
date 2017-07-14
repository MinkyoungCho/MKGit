package DfsBasic;

class PathFinder {
	char[][] matrix;
	boolean[][] mark;
	
	//(x, y)지점에서 str[index, index + 1, ..., length]를 위한 path가 존재하는가 안하는가
	public boolean findPath(String str, int index, int x, int y) {
		if (index == str.length()) {
			return true;
		}
		
		boolean ret = false;
		if (matrix[x][y] == str.charAt(index)) {
			mark[x][y] = true;
			
			if (x - 1 >= 0 && !mark[x - 1][y]) {
				ret = (ret || findPath(str, index + 1, x - 1, y));
			}
			if (x + 1 < matrix.length && !mark[x + 1][y]) {
				ret = (ret || findPath(str, index + 1, x + 1, y));
			}
			if (y - 1 >= 0 && !mark[x][y - 1]) {
				ret = (ret ||findPath(str, index + 1, x, y - 1));
			}
			if (y + 1 < matrix[0].length && !mark[x][y + 1]) {
				ret = (ret || findPath(str, index + 1, x, y + 1));
			}
			
			return ret;
			
		} else {
			return false;
		}
		
	}
}

public class StringPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PathFinder pf = new PathFinder();
		pf.matrix = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
		pf.mark = new boolean[pf.matrix.length][pf.matrix[0].length];
		
		boolean ret = false;
		for (int i = 0; i < pf.matrix.length; i++) {
			for (int j = 0; j < pf.matrix[0].length; j++) {
				ret = ret || pf.findPath("ABCCED", 0, i, j);
			}
		}
		System.out.println(ret);

	}

}
