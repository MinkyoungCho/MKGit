package 등산로;

import java.util.Arrays;

//Arrays.copyOf()는 배열의 내용만!! 복사 - 원본 훼손 x
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a1 = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
		int[][] a2 = new int[a1.length][]; 
		for (int i = 0; i < 2; i++) {
			a2[i] = Arrays.copyOf(a1[i], a1[1].length); 
		}
		
		a2[0][0] = 0;
		
		for (int i = 0; i < 5; i++) {
			System.out.print(a1[0][i] + " ");
		}
		
		for (int i = 0; i < 5; i++) {
			System.out.print(a2[0][i] + " ");
		}

	}

}
