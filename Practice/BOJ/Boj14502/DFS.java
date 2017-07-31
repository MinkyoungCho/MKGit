package Bj14502;

public class DFS {
	
	public static int dfs(int[][] matrix, boolean[][] mark, int i, int j) {
		mark[i][j] = false; //���� ���� ���� x (�̹� ���������ϱ�)
		int res = 0;
		
		if ((i - 1) >= 0 && matrix[i - 1][j] == 0 && mark[i - 1][j]) {
			res = dfs(matrix, mark, i - 1, j) + 1; // �������� �̵��� ���������� �����Ǵ� ��� ���� ����
		}
		if ((i + 1) < matrix.length && matrix[i + 1][j] == 0 && mark[i + 1][j]) {
			res = res + dfs(matrix, mark, i + 1, j) + 1; // ������ �̵��� ���������� �����Ǵ� ��� ���� ����
		}
		if ((j - 1) >= 0 && matrix[i][j - 1] == 0 && mark[i][j - 1]) {
			res = res + dfs(matrix, mark, i, j - 1) + 1; // ���� �̵��� ���������� �����Ǵ� ��� ���� ����
		}
		if ((j + 1) < matrix[0].length && matrix[i][j + 1] == 0 && mark[i][j + 1]) {
			res = res + dfs(matrix, mark, i, j + 1) + 1; // �Ʒ��� �̵��� ���������� �����Ǵ� ��� ���� ����
		}
		
		return res; // �� ���� �̵��� ���� ���� Ƚ�� ��� ���ϱ�!!!!
	}
}
