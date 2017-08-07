/*
 * 기존 String(concat)처리 함수에서 Int 처리(4 or 7 * 자릿수 ) 함수로 변경
 * 실행 결과: 실행은 되는데 38점...?
 */

package CG_ExtremeNum2;

//find the N th extreme number by top down - dynamic programming
class TopDown {
	int n;
	
	public TopDown(int n) {
		this.n = n;
	}
	
	//1. 자릿수 구하기 함수
	public int findCiphers() {
		int k = 1; // 한 자릿수
		
		while (true) { // 자릿수 구하기
			if ((Math.pow(2, k) <= (n + 1)) && ((n + 2) <= Math.pow(2, k + 1))) {
				return k;
			} else {
				k++;
			}
				
		}
	}
	
	//2. 자릿수 기준으로 /2.. /4.. /8.. 해가며 크기 비교
	public int findNth() {
		int numOfCiphers = findCiphers(); //자릿수
		
		int tempNum = n - ((int)Math.pow(2, numOfCiphers) - 1);
		int res = 0;
		
		while (--numOfCiphers >= 0) {
			if (tempNum < (int) Math.pow(2, numOfCiphers)) { 
				res = res + 4 * (int) Math.pow(10, numOfCiphers);
			} else {
				res = res + 7 * (int) Math.pow(10, numOfCiphers);
				tempNum -= (int) Math.pow(2, numOfCiphers);
			}
		}
		
		return res;
		
	}

}
