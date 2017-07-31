package Bj14502;

public class DFS {
	
	public static int dfs(int[][] matrix, boolean[][] mark, int i, int j) {
		mark[i][j] = false; //감염 가능 지역 x (이미 감염됐으니까)
		int res = 0;
		
		if ((i - 1) >= 0 && matrix[i - 1][j] == 0 && mark[i - 1][j]) {
			res = dfs(matrix, mark, i - 1, j) + 1; // 왼쪽으로 이동시 최종적으로 감염되는 모든 영역 개수
		}
		if ((i + 1) < matrix.length && matrix[i + 1][j] == 0 && mark[i + 1][j]) {
			res = res + dfs(matrix, mark, i + 1, j) + 1; // 오른쪽 이동시 최종적으로 감염되는 모든 영역 개수
		}
		if ((j - 1) >= 0 && matrix[i][j - 1] == 0 && mark[i][j - 1]) {
			res = res + dfs(matrix, mark, i, j - 1) + 1; // 위로 이동시 최종적으로 감염되는 모든 영역 개수
		}
		if ((j + 1) < matrix[0].length && matrix[i][j + 1] == 0 && mark[i][j + 1]) {
			res = res + dfs(matrix, mark, i, j + 1) + 1; // 아래로 이동시 최종적으로 감염되는 모든 영역 개수
		}
		
		return res; // 네 방향 이동시 최종 감염 횟수 모두 더하기!!!!
	}
}
