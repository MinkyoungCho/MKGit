package Boj1970;

public class CheersDP {
	int numOfPeople;
	int[] arrOfBeers;
	boolean[] mark; // 이미 건배 했으면 true
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
	
	// 현재 상태가 params 일때, 가능한 커플 수 의 최대값 return - 동시에 cache에 저장
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
		
		// 1) 현재 사람이 '임의의 누군가'와 건배를 하는 경우
		for (int i = people + 1; (i < limit && !mark[i]); i++) { // 건배 가능한 후보자 범위
			if (arrOfBeers[i] == arrOfBeers[people]) { // 맥주가 같으면 건배 로직 실행
				mark[i] = true;
				resLeft = makeCouple(people + 1, i, mark); // [people + 1, i) 부분에서의 만들 수 있는 최대 커플 수 
				
				for (int j = 0; j < numOfPeople; j++) {
					mark[j] = backUp[j];
				}
				mark[i] = true;
				resRight = makeCouple(i + 1, limit, mark); // [i + 1, limit) 부분에서의 만들 수 있는 최대 커플 수 
				res = Math.max(res, 1 + resLeft + resRight); // 이전에 얻었던 총 최대 커플 수와 현재 반복문 실행에서 얻는 총 최대커플 수((people & i)까지 + 1) 중 최대 값 선택
				
				for (int j = 0; j < numOfPeople; j++) {
					mark[j] = backUp[j];
				}
				
			}	
		}
		
		// 2)건배를 안하는 경우
		res = Math.max(res, makeCouple(people + 1, limit, mark));
		
		// 따라서 현재 people이 1)'임의의 누군가'와 건배를 할 경우와 2)안하는 경우 중 가능한 최대의 커플 수를 구하게 된다!!
		cache[people][limit] = res;
		return res;
	}
}
