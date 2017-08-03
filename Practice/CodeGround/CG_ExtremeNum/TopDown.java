package CG_ExtremeNum;

//find the N th extreme number by top down - dynamic programming
class TopDown {
	int n;
	int[] cache;
	
	public TopDown(int n) {
		this.n = n;
		this.cache = new int[n + 1];
	}
	
	//n번째 수가 몇 자리수인지 구하고 자릿수 리턴
	public int findCiphers() {
		int k = 1; // 한 자릿수
		
		while (true) {
			if ((Math.pow(2, k) <= (n + 1)) && ((n + 2) <= Math.pow(2, k + 1))) {
				return k;
			} else {
				k++;
			}
				
		}
	}
	
	//자릿수 기준으로 /2.. /4.. /8.. 해가며 크기 비교
	public int findNth() {
		int numOfCiphers = findCiphers(); //자릿수
		int tempNum = n - ((int)Math.pow(2, numOfCiphers) - 1);
		String res = "";
		
		while (--numOfCiphers >= 0) {
			if (tempNum < (int) Math.pow(2, numOfCiphers)) { 
				res = res.concat("4");
			} else {
				res = res.concat("7");
				tempNum -= (int) Math.pow(2, numOfCiphers);
			}
		}
		
		return Integer.parseInt(res);
		
	}

}
