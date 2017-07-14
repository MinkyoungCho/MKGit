package bit_manipulation;

/*
 * 숫자 right shift할 경우 음수 처리 못함!
 * flag = 1을 left shift하기! --> while (flag >= 0)
 * 
 */
class Number1Counting {

	public int count(int num) {
		int flag = 1;
		int count = 0;
		while (flag >= 0) {
			if ((num & flag) == flag) { // or != 0 but 1은 아님!!! --> 해당 자리의 num의 비트가 1! flag는 1이 아니고 1 + 자릿수!!
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