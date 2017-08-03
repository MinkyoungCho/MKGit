package CG_ExtremeNum;

// find the N th extreme number by bottom up - dynamic programming
class BottomUp {
	int n;
	int[] cache;
	
	public BottomUp(int n) {
		this.n = n;
		this.cache = new int[n];
	}
	
	public int findNth() {
		if (n == 1) {
			return 4;
		} else if (n == 2) {
			return 7;
		}
		
		this.cache[0] = 4;
		this.cache[1] = 7;
		int index = 2;
		int countGroup = 2;
		int tempIndex = 0;

		// 2개에 대해서 .. 4개에 대해서.. 8개에 대해서.. 16개에 대해서.. 
		while (true) {
			countGroup *= 2;
			tempIndex = 0;
			
			while (tempIndex < countGroup) {

				if (index == n) {
					return this.cache[n - 1];
				}

				if (tempIndex < (countGroup / 2)) { // 앞에 4 붙이기
					this.cache[index] = Integer.parseInt(4 + "" + this.cache[index - countGroup / 2]);

				} else { // 앞에 7 붙이기
					this.cache[index] = Integer.parseInt(7 + "" + this.cache[index - countGroup]);
				}

				index++;
				tempIndex++;
			}
		}
	}
}
