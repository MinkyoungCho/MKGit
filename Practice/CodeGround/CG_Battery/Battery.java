package CG_Battery;

public class Battery {
	int[][] lines;
	int numOfLines;
	int size = 100;
	
	public Battery(int[][] lines, int numOfLines) {
		this.lines = lines;
		this.numOfLines = numOfLines;
	}
	
	
	public int calcPowerDist(int x, int y) { // (x, y)�� ������ ������ �ִ� ���� �Ÿ� ����
		int max = -1;
		
		for (int i = 0; i < numOfLines; i++) { // N���� line�� ���� 
			// ���� 1������ �Ÿ�
			int dist1 =  Math.max(Math.abs(x - lines[i][0]), Math.abs(x - lines[i][1]));
					
			// ���� 2������ �Ÿ�
			int dist2 = Math.max(Math.abs(x - lines[i][2]), Math.abs(x - lines[i][3]));
			
			// �� �� �ּ� �� ����
			int dist = Math.min(dist1, dist2);
			
			// �ִ� �����Ÿ� ����
			if (dist > max) {
				max = dist;
			}
		}
		
		return max;
	}
	
	public int findMinVal() {
		int min = size;
		
		for (int i = 0; i < size; i++) { // ���� �� ��ǥ�� ���� �ִ� ���� �Ÿ� ���--> �� �� �ּҰ� ����
			for (int j = 0; j < size; j++) {
				
				int res = calcPowerDist(i, j);
				if (res < min) {
					min = res;
				}
			}
		}
		
		return min;
	}
}
