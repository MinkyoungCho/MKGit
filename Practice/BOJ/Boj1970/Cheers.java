package Boj1970;

public class Cheers {
	int numOfPeople;
	int[] arrOfBeers;
	boolean[] mark; // 이미 건배 했으면 true
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
		
		//현재 사람이 건배를 하는 경우
		for (int i = people + 1; (i < numOfPeople && !mark[i]); i++) { // 건배 가능한 후보자 범위
			if (arrOfBeers[i] == arrOfBeers[people]) { // 맥주가 같으면 건배 로직 실행
				mark[i] = true;
				res = Math.max(res, makeCouple(people + 1, mark) + 1); 
				
				for (int j = 0; j < numOfPeople; j++) {
					mark[j] = backUp[j];
				}
				
			}	
		}
		
		//건배를 안하는 경우
		res = Math.max(res, makeCouple(people + 1, mark));
		return res;
	}
}
