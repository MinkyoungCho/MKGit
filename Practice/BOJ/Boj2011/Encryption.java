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
	public void decrypt(int index) { //가능한 경로의 수 세기 - DFS & 끝에서 cnt++
		int res = 0;
		
		if (index == length) {
			count++;
			return;
		} else if (index > length) {
			return; // 임무 끝 >> 이 부분의 임무는 '아무것도 안하기'
		} else {
			//1자리
			decrypt(index + 1);
			
			//2자리, if possible
			if (index < length - 1) {
				int temp = Integer.parseInt(Integer.toString(number).substring(index, index + 2));
				if (temp <= 26) {
					decrypt(index + 2);
				}
			}
		}
		
	}
}
