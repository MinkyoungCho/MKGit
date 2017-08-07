/*
 * ���� String(concat)ó�� �Լ����� Int ó��(4 or 7 * �ڸ��� ) �Լ��� ����
 * ���� ���: ������ �Ǵµ� 38��...?
 */

package CG_ExtremeNum2;

//find the N th extreme number by top down - dynamic programming
class TopDown {
	int n;
	
	public TopDown(int n) {
		this.n = n;
	}
	
	//1. �ڸ��� ���ϱ� �Լ�
	public int findCiphers() {
		int k = 1; // �� �ڸ���
		
		while (true) { // �ڸ��� ���ϱ�
			if ((Math.pow(2, k) <= (n + 1)) && ((n + 2) <= Math.pow(2, k + 1))) {
				return k;
			} else {
				k++;
			}
				
		}
	}
	
	//2. �ڸ��� �������� /2.. /4.. /8.. �ذ��� ũ�� ��
	public int findNth() {
		int numOfCiphers = findCiphers(); //�ڸ���
		
		int tempNum = n - ((int)Math.pow(2, numOfCiphers) - 1);
		int res = 0;
		
		while (--numOfCiphers >= 0) {
			if (tempNum < (int) Math.pow(2, numOfCiphers)) { 
				res = res + 4 * (int) Math.pow(10, numOfCiphers);
			} else {
				res = res + 7 * (int) Math.pow(10, numOfCiphers);
				tempNum -= (int) Math.pow(2, numOfCiphers);
			}
		}
		
		return res;
		
	}

}
