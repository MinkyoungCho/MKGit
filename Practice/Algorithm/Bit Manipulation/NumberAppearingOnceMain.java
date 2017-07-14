package bit_manipulation;

/*
 * 1개 나타나는 원소를 제외한 모든 수를 다 더하면 각 비트는 3의 배수가 됨!
 * 따라서 각 비트에 대한 합을 3으로 나눴을 때 나머지로 1개나타나는 원소의 비트 알수있음
 * 비트를 10진수로 표현!
 * 
 *  bitMask씌워서 각 bit의 합 저장!
 *  
 */
class NumberAppearingOnce {
	int[] bitSum = new int[31]; //0으로 자동 초기화
	int flag = 1;

	public void sumWithBitMask(int a) { //초기화, 더하기만 or 더하고 할당! 할당특히 조심하기
		flag = 1;

		for (int i = 0; i < bitSum.length; i++) {
			bitSum[i] += (a & flag) / flag; //flag가 10일때 /flag가 없으면 1을 더하는게 아니라 flag의 10진수 값 더함! 
			flag = flag << 1;
		}
		System.out.print("\n");

	}

	public int sumEachBit(int[] inputArray) {
		for (int i = 0; i < inputArray.length; i++) {
			sumWithBitMask(inputArray[i]);
		}
		int result = changeBitToNum();
		return result;
	}

	public int changeBitToNum() { // 비트배열 --> 10진수
		int result = 0;
		int[] numBit = new int[31];
		
		for (int i = 0; i < bitSum.length; i++) {
			numBit[i] = bitSum[i] % 3;

			if (numBit[i] == 1) {
				for (int j = 0; j < i; j++) {
					numBit[i] = numBit[i] << 1; // i번만큼 shift
				}

				result += numBit[i];
			}
		}
		return result;
	}
}

public class NumberAppearingOnceMain {
	public static void main(String[] args) {
		NumberAppearingOnce numClass = new NumberAppearingOnce();
		int[] input = { 1,1,1,4,4,4,8, 2,2,2};
		
		System.out.println("\n" + numClass.sumEachBit(input)); // array 바로 써서 넘기지 말기!
	}
}