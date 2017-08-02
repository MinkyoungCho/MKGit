package Boj2294;

public class Coin {
	int[] coins;
	int target;
	int[][] cache;
	public static final int INT_MAX = 99999;
	
	public Coin(int[] coins, int target) {
		this.coins = coins;
		this.target = target;
		this.cache = new int[coins.length][target + 1];
		for (int i = 0; i < coins.length; i++) {
			for (int j = 0; j < target + 1; j++) {
				this.cache[i][j] = -1;
			}
		}
	}
	
	public int mixCoins(int index, int goal) { // [index, ...]�κ��� goal�� ���� �� �ִ� �ּ� ���� ��
		if (index == coins.length || goal <= 0) {
			if (goal == 0) {
				return 0; // ���� ��� x
			} else {
				return INT_MAX;
			}
		}
		
		if (cache[index][goal] >= 0) {
			return cache[index][goal];
		}
		
		int res = INT_MAX;
		
		// 1) ���
		res = Math.min(res, 1+ mixCoins(index, goal - coins[index]));
		
		// 2) ��� x
		res = Math.min(res, mixCoins(index + 1, goal));
		
		// ������ ��� ���(�ΰ���) �� �ּҰ� ����
		cache[index][goal] = res;
		return res;
	}
}
