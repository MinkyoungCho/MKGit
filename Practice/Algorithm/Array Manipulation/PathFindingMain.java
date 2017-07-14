package array_programming;

/*
 * 못품!
 * BackTracking!!
 *
 */

class PathFinding {
	char[][] path_matrix = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
	boolean[][] mark = new boolean[path_matrix.length][path_matrix[0].length];
	String input;
	
	public PathFinding(String input) {
		this.input = input;
		
		
	}
	
	public boolean checkString() {
		for (int i = 0; i < mark.length; i++) {
			for (int j = 0; j < mark[0].length; j++) {
				mark[i][j] = false;
			}
		}
		
		boolean result = false;
		for (int i = 0; i < path_matrix.length; i++) {
			for (int j = 0; j < path_matrix[0].length; j++) {
				if (input.charAt(0) == path_matrix[i][j]) {
					result = isPath(i, j, 0);
				}
			}
		}
		return result;
	}
	
	int stringIndex = 0;
	
	public boolean compare(int i, int j, int stringIndex) {
		if (path_matrix[i][j] == input.charAt(stringIndex)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isPath(int i, int j, int stringIndex) {
		boolean result = true;
		
		if (mark[i][j]) { // 중복 지점 방문
			return false;
		} else {
			mark[i][j] = true;
			if (stringIndex != input.length() - 1) {
				if ((i - 1) >= 0) { // left
					if (compare(i - 1, j, stringIndex + 1)) {
						result = isPath(i - 1, j, stringIndex + 1);
					} else {
						return false;
					}
				}
				if ((i + 1) < path_matrix.length) { // right
					if (compare(i + 1, j, stringIndex + 1)) {
						result = isPath(i + 1, j, stringIndex + 1);
					} else {
						return false;
					}
				}
				if ((j - 1) >= 0) {
					if (compare(i, j - 1, stringIndex + 1)) {
						result = isPath(i, j - 1, stringIndex + 1);
					} else {
						return false;
					}
				}
				if ((j + 1) < path_matrix[0].length) {
					if (compare(i, j + 1, (stringIndex + 1))) {
						result = isPath(i, j + 1, (stringIndex + 1));
					} else {
						return false;
					}
				}
			} else {
				return true;
			}
		}
		return result;
	}
}

public class PathFindingMain {
	public static void main(String[] args) {
		PathFinding path1 = new PathFinding("ABCE");
		PathFinding path2 = new PathFinding("ABCB");
		
		System.out.println(path1.checkString());
		System.out.println(path2.checkString());
	}

}
