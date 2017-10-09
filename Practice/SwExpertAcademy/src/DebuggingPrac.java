/*
 * 디버깅 익숙해지기!!
 * 1) resume: 다음 breakpoint까지 쭉 진행
 * 2) step into: 한줄씩 넘어가되 함수콜 시 함수 내부로 들어감
 *    - step return: 내부 함수에서 그만!하고 빠져나오기
 * 3) step over: 한줄씩 넘어가되 함수콜 시 내부로 들어가는 과정 생략
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
