package bit_manipulation;

/*
 * 1�� ��Ÿ���� ���Ҹ� ������ ��� ���� �� ���ϸ� �� ��Ʈ�� 3�� ����� ��!
 * ���� �� ��Ʈ�� ���� ���� 3���� ������ �� �������� 1����Ÿ���� ������ ��Ʈ �˼�����
 * ��Ʈ�� 10������ ǥ��!
 * 
 *  bitMask������ �� bit�� �� ����!
 *  
 */
class NumberAppearingOnce {
	int[] bitSum = new int[31]; //0���� �ڵ� �ʱ�ȭ
	int flag = 1;

	public void sumWithBitMask(int a) { //�ʱ�ȭ, ���ϱ⸸ or ���ϰ� �Ҵ�! �Ҵ�Ư�� �����ϱ�
		flag = 1;

		for (int i = 0; i < bitSum.length; i++) {
			bitSum[i] += (a & flag) / flag; //flag�� 10�϶� /flag�� ������ 1�� ���ϴ°� �ƴ϶� flag�� 10���� �� ����! 
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

	public int changeBitToNum() { // ��Ʈ�迭 --> 10����
		int result = 0;
		int[] numBit = new int[31];
		
		for (int i = 0; i < bitSum.length; i++) {
			numBit[i] = bitSum[i] % 3;

			if (numBit[i] == 1) {
				for (int j = 0; j < i; j++) {
					numBit[i] = numBit[i] << 1; // i����ŭ shift
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
		
		System.out.println("\n" + numClass.sumEachBit(input)); // array �ٷ� �Ἥ �ѱ��� ����!
	}
}