package DP;

class StolenVlues {
	int[] values;
	int[][] table= new int[2][5];
	
	// x��° ���� status�� ��, ������ ���� �� �ִ� �ִ� value!
	public int findValue(int x, int status) {
		if (x == values.length) {
			return 0;
		}
		// �ߺ� ��� ���ϱ�
		if (table[status][x] > -1) {
			return table[status][x];
		}
		
		//������ ��� ��쿡 ���� ��� �� �ִ밪 ã�� ���̺� ����!
		int ret = -1;
		if (status == 1) { 
			ret = findValue(x + 1, 0) + values[x];
		}
		ret = Math.max(ret, findValue(x + 1, 1)); 
		
		table[status][x] = ret;
		return ret;
	}
}

public class Thief {

	public static void main(String[] args) {
		StolenVlues sv = new StolenVlues();
		
		sv.values = new int[]{6, 1, 2, 7};
		for (int i = 0; i < sv.table.length; i++) {
			for (int j = 0; j < sv.table[0].length; j++) {
				sv.table[i][j] = -1;
			}
		}
		
		System.out.println(sv.findValue(0, 1));

	}

}
