package bit_manipulation;

/*
 * ���� right shift�� ��� ���� ó�� ����!
 * flag = 1�� left shift�ϱ�! --> while (flag >= 0)
 * 
 */
class Number1Counting {

	public int count(int num) {
		int flag = 1;
		int count = 0;
		while (flag >= 0) {
			if ((num & flag) == flag) { // or != 0 but 1�� �ƴ�!!! --> �ش� �ڸ��� num�� ��Ʈ�� 1! flag�� 1�� �ƴϰ� 1 + �ڸ���!!
				count++;
			}
			flag = flag << 1;
		}
		return count;
	}
}

public class Num1CountingMain {
	public static void main(String[] args) {
		Number1Counting num1Counting = new Number1Counting();
		System.out.println(num1Counting.count(9));
	}
}