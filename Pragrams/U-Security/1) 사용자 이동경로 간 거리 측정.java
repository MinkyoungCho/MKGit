import java.text.DecimalFormat;


public class EditDistance {	
	public static int last_index = -1;
	
	public static double editDistance(int[] invader, int[] profile, int clusterNum) { // �����Ÿ� �˰��� (���� ���α׷���)
		double penalty_insert = 0;
		double penalty_delete = 0;
		double penalty_substitution = 0;

		int m = invader.length; // ħ���� ���
		int n = profile.length; // �������� ���
		int i, j, k, dist;
		double [][] d;
		int nodeA, nodeB;

		ExcelAccess.openExcelFile(); // ���� ������ ���� �ڹ� ���α׷����� ����

		d = new double[invader.length + 1][profile.length + 1];

		for (i = 0; i <= invader.length; i++) {
			d[i][0] = i;
		}

		for (j = 0; j <= profile.length; j++) {
			d[0][j] = j;
		}

		for (i = 1; i <= invader.length; i++) { 
			for (j = 1; j <= profile.length; j++) {
				if (invader[i-1] == profile[j-1]) { 
					d[i][j] = d[i-1][j-1] + 100;
				}
				else { 
					nodeA = ExcelAccess.nodeToindex(invader[i-1]);
					nodeB = ExcelAccess.nodeToindex(profile[j-1]);
					dist = ExcelAccess.distance_matrix[nodeA][nodeB];
					penalty_substitution = 100 / (double)(dist + 1);
					d[i][j] = findMax((d[i-1][j] + penalty_delete), (d[i][j-1] + penalty_insert), (d[i-1][j-1] + penalty_substitution));
				}
			}
		}
		last_index = findLastIndex(d);
		return d[invader.length][profile.length]; // ���� score ����

	}

	public static double findMax(double a, double b, double c) { 
		double max = Math.max(Math.max(a, b), c);

		return max;
	}
	
	public static int findLastIndex(double[][] matrix) {
		int i, j;
		int max_i = -1; // ��ü �ִ�
		double max = -1, local_max = -1; // column �� �ִ�
				
		for(i = 0; i < matrix[0].length; i++) { // ��ü �ִ밪 ã��
			for(j = 0; j < matrix.length; j++) { // �� col �� �ִ밪 ã��
				if(matrix[j][i] > local_max) local_max = matrix[j][i];
			}
			if(local_max > max) {
				max = local_max;
				max_i = i;
			}
		}
		return (max_i - 1);
	}
	
	public static void showTable(double[][] matrix) {
		int i, j;
		
		for(i = 0; i < matrix.length; i++) {
			for(j = 0; j < matrix[0].length; j++) {
				double score = 0.0f;
				String score2 = "";
				
				score = matrix[i][j];
				DecimalFormat df = new DecimalFormat();
				df.applyLocalizedPattern("0.##");
				score2 = df.format(score);
				
				System.out.print(score2+" ");
				
			}
			System.out.println();
		}
	}
}
