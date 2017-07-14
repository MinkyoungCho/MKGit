package bit_manipulation;

/*
 * 2의 pow = 그 수의 비트에서 1이 한 개여야함!
 * pow의 값음 음수가 될 수 없기때문에 음수의 값이 들어오면 에러로 처리하고,양의정수가 들어온다고 가정
 * 따라서 right shift가능
 * & 1로  LSB 1인지 아닌지 확인하고 >> 반복
 * 
 */

class CheckPowerOfTwo {
int count = 0;
	
	public boolean isPowerOfTwo(int a) {
		while (a != 0) {
			if ((a & 1) == 1) {
				if (count == 0) {
					count++;
				} else {
					return false;
				}
			} 
			a = a >> 1;
		}
		if (count == 1) {
			return true;
		} else { // count가 0인 경우
			return false;
		}
		
	}
}

public class PowerOf2 {
	public static void main(String[] args) {
		CheckPowerOfTwo checkPower = new CheckPowerOfTwo();
		System.out.println(checkPower.isPowerOfTwo(16));
	}
}


