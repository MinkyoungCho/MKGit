package Boj1970;

public class Cheers {
	int numOfPeople;
	int[] arrOfBeers;
	boolean[] mark; // �̹� �ǹ� ������ true
	int[][] cache;
	
	public Cheers(int numOfPeople, int[] array) {
		this.numOfPeople = numOfPeople;
		this.arrOfBeers = array;
		this.mark = new boolean[numOfPeople];
		this.cache = new int[numOfPeople][numOfPeople];
	}
	
	public int makeCouple(int people, boolean[] mark) {
		if (people == numOfPeople) {
			return 0;
		}
		
		boolean[] backUp = new boolean[numOfPeople];
		for (int j = 0; j < numOfPeople; j++) {
			backUp[j] = mark[j];
		}
		
		int res = -1;
		
		//���� ����� �ǹ踦 �ϴ� ���
		for (int i = people + 1; (i < numOfPeople && !mark[i]); i++) { // �ǹ� ������ �ĺ��� ����
			if (arrOfBeers[i] == arrOfBeers[people]) { // ���ְ� ������ �ǹ� ���� ����
				mark[i] = true;
				res = Math.max(res, makeCouple(people + 1, mark) + 1); 
				
				for (int j = 0; j < numOfPeople; j++) {
					mark[j] = backUp[j];
				}
				
			}	
		}
		
		//�ǹ踦 ���ϴ� ���
		res = Math.max(res, makeCouple(people + 1, mark));
		return res;
	}
}
