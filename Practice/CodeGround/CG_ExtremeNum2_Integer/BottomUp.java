/*
 * ���� String(concat)ó�� �Լ����� Int ó��(4 or 7 * �ڸ��� ) �Լ��� ����
 * ���� ���: Heap space �޸� ���� ���� �߻�
 * 
 */
package CG_ExtremeNum2;

public class BottomUp {
	int n;
	int[] cache;
	
	public BottomUp(int n) {
		this.n = n;
		this.cache = new int[n + 1];
	}
	
	int groupID = 1;
	
	public int findNth() {
		if (n == 1) {
			return 4;
		} else if (n == 2) {
			return 7;
		} else {
			this.cache[1] = 4;
			this.cache[2] = 7;
			
			int index = 3;
			
			while (true) {
				groupID++;
				int tempIndex = 0;
				int numOfItems = (int) Math.pow(2, groupID);
				
				while (tempIndex < numOfItems) {
					if (tempIndex < numOfItems / 2) { // �տ� 4 ���̱�
						this.cache[index] = 4 * (int) Math.pow(10, groupID - 1) + this.cache[index - numOfItems / 2];

					} else { // �տ� 7 ���̱�
						this.cache[index] = 7 * (int) Math.pow(10, groupID - 1) + this.cache[index - numOfItems];
					}
					
					if (index == n) {
						return this.cache[index];
					}
					
					index++;
					tempIndex++;
		
				}
			}
	
		}
	}
}
