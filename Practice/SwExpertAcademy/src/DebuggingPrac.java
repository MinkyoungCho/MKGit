/*
 * ����� �ͼ�������!!
 * 1) resume: ���� breakpoint���� �� ����
 * 2) step into: ���پ� �Ѿ�� �Լ��� �� �Լ� ���η� ��
 *    - step return: ���� �Լ����� �׸�!�ϰ� ����������
 * 3) step over: ���پ� �Ѿ�� �Լ��� �� ���η� ���� ���� ����
 * 
 */
public class DebuggingPrac {

	public static void main(String[] args) {
		int res = 0;
		for (int i = 0; i < 10; i++) {
			res += square(i);
		}
		System.out.println(res);
		
	}
	
	static int square(int a) {
		int res;
		res = a + 2;
		res = res + 3;
		return (res * res);
	}
}
