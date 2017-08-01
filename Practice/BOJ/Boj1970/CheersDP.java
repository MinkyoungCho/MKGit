package Boj1970;

public class CheersDP {
	int numOfPeople;
	int[] arrOfBeers;
	boolean[] mark; // �̹� �ǹ� ������ true
	int[][] cache; // Dynamic Programming
	
	public CheersDP(int numOfPeople, int[] array) {
		this.numOfPeople = numOfPeople;
		this.arrOfBeers = array;
		this.mark = new boolean[numOfPeople];
		this.cache = new int[numOfPeople][numOfPeople + 1];
		for (int i = 0; i < cache.length; i++) {
			for (int j = 0; j < cache[0].length; j++) {
				cache[i][j] = -1;
			}
		}
	}
	
	// ���� ���°� params �϶�, ������ Ŀ�� �� �� �ִ밪 return - ���ÿ� cache�� ����
	public int makeCouple(int people, int limit, boolean[] mark) {
		if (people == limit) {
			return 0;
		}
		
		if (cache[people][limit] >= 0) {
			return cache[people][limit];
		}
		
		boolean[] backUp = new boolean[numOfPeople];
		for (int j = 0; j < numOfPeople; j++) {
			backUp[j] = mark[j];
		}
		
		int res = -1;
		int resLeft = -1;
		int resRight = -1;
		
		// 1) ���� ����� '������ ������'�� �ǹ踦 �ϴ� ���
		for (int i = people + 1; (i < limit && !mark[i]); i++) { // �ǹ� ������ �ĺ��� ����
			if (arrOfBeers[i] == arrOfBeers[people]) { // ���ְ� ������ �ǹ� ���� ����
				mark[i] = true;
				resLeft = makeCouple(people + 1, i, mark); // [people + 1, i) �κп����� ���� �� �ִ� �ִ� Ŀ�� �� 
				
				for (int j = 0; j < numOfPeople; j++) {
					mark[j] = backUp[j];
				}
				mark[i] = true;
				resRight = makeCouple(i + 1, limit, mark); // [i + 1, limit) �κп����� ���� �� �ִ� �ִ� Ŀ�� �� 
				res = Math.max(res, 1 + resLeft + resRight); // ������ ����� �� �ִ� Ŀ�� ���� ���� �ݺ��� ���࿡�� ��� �� �ִ�Ŀ�� ��((people & i)���� + 1) �� �ִ� �� ����
				
				for (int j = 0; j < numOfPeople; j++) {
					mark[j] = backUp[j];
				}
				
			}	
		}
		
		// 2)�ǹ踦 ���ϴ� ���
		res = Math.max(res, makeCouple(people + 1, limit, mark));
		
		// ���� ���� people�� 1)'������ ������'�� �ǹ踦 �� ���� 2)���ϴ� ��� �� ������ �ִ��� Ŀ�� ���� ���ϰ� �ȴ�!!
		cache[people][limit] = res;
		return res;
	}
}
