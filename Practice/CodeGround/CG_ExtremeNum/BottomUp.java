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

		// 2���� ���ؼ� .. 4���� ���ؼ�.. 8���� ���ؼ�.. 16���� ���ؼ�.. 
		while (true) {
			countGroup *= 2;
			tempIndex = 0;
			
			while (tempIndex < countGroup) {

				if (index == n) {
					return this.cache[n - 1];
				}

				if (tempIndex < (countGroup / 2)) { // �տ� 4 ���̱�
					this.cache[index] = Integer.parseInt(4 + "" + this.cache[index - countGroup / 2]);

				} else { // �տ� 7 ���̱�
					this.cache[index] = Integer.parseInt(7 + "" + this.cache[index - countGroup]);
				}

				index++;
				tempIndex++;
			}
		}
	}
}
