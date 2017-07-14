package DP;

class CoinChange {
	int min = 1000;
	int[] table = new int[20];
	int[] coins;
	
	public int findMin(int x) {
		if (x == 0) {
			return 0;
		}
		
		//dp : �ߺ� ��� ���ϱ�!!!
		if (table[x] > 0) { 
			return table[x];	
		}
		
		int ret = 0;
		int minVal = 1000;
		for (int i = 0; i < coins.length; i++) {
			if (x - coins[i] >= 0) {
				ret = 1 + findMin(x - coins[i]);
				if (ret < minVal) {
					minVal = ret;
				}
			}
		}
		
		// �Է��� x �϶� �ּ� ���� ���� ���ؼ� ����! - dp
		table[x] = minVal; 
		return table[x];
	}
}

public class CoinChangeProblem {

	public static void main(String[] args) {
		CoinChange cc = new CoinChange();
		cc.coins = new int[]{1, 3, 9, 10};
		
		cc.findMin(15);
		for (int i = 0; i < 20; i++) {
			System.out.print(" " + cc.table[i]);

		}
	}

}
