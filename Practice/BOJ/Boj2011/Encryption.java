package Boj2011;

public class Encryption {
	int number;
	int length;
	int[] cache;
	
	public Encryption(int number) {
		this.number = number;
		cache = new int[length];
		length = Integer.toString(number).length();
	}
	
	int count = 0;
	public void decrypt(int index) { //������ ����� �� ���� - DFS & ������ cnt++
		int res = 0;
		
		if (index == length) {
			count++;
			return;
		} else if (index > length) {
			return; // �ӹ� �� >> �� �κ��� �ӹ��� '�ƹ��͵� ���ϱ�'
		} else {
			//1�ڸ�
			decrypt(index + 1);
			
			//2�ڸ�, if possible
			if (index < length - 1) {
				int temp = Integer.parseInt(Integer.toString(number).substring(index, index + 2));
				if (temp <= 26) {
					decrypt(index + 2);
				}
			}
		}
		
	}
}
