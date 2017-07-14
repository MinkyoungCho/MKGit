package DP;

public class Make1 {
	
//	static int min = 1000000;

	public static int make1(int x) { // ¿ÏÀüÅ½»ö
		int ret = 0;
		int min = 10000;
		if (x == 1) {
			return 0;
		}
		if (x % 3 == 0) {
			ret = make1(x / 3) + 1;
			if (ret < min) {
				min = ret;
			}
		}
		if (x % 2 == 0) {
			ret = make1(x / 2) + 1;
			if (ret < min) {
				min = ret;
			}
		}
			
		ret = make1(x - 1) + 1;
		if (ret < min) {
			min = ret;
		}
		return min;
	}
	
	public static void main(String[] args) {
		System.out.println(Make1.make1(2));
		
	}
}
