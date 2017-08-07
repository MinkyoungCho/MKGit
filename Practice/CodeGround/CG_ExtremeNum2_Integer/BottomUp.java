/*
 * 기존 String(concat)처리 함수에서 Int 처리(4 or 7 * 자릿수 ) 함수로 변경
 * 실행 결과: Heap space 메모리 부족 에러 발생
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
					if (tempIndex < numOfItems / 2) { // 앞에 4 붙이기
						this.cache[index] = 4 * (int) Math.pow(10, groupID - 1) + this.cache[index - numOfItems / 2];

					} else { // 앞에 7 붙이기
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
