package bit_manipulation;

/*
 * 2�� pow = �� ���� ��Ʈ���� 1�� �� ��������!
 * pow�� ���� ������ �� �� ���⶧���� ������ ���� ������ ������ ó���ϰ�,���������� ���´ٰ� ����
 * ���� right shift����
 * & 1��  LSB 1���� �ƴ��� Ȯ���ϰ� >> �ݺ�
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
		} else { // count�� 0�� ���
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


