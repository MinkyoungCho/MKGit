package DP;

import java.util.Scanner;

class DPSticker {
	int[][] matrix;
	int[][] table; // 3 states
	int max = -1;
	
	//0: À­ÁÙ, 1: ¾Æ·§ÁÙ, 2: µÑ´Ù °¡´ÉÇÑ °æ¿ì
	public int attachSticker(int col, int state) {
		int ret = 0;
		
		if (col == matrix[0].length) {
			return 0;
		}
		
		if (table[state][col] != -1) {
			return table[state][col];
		}
		
		switch (state) {
		case 0:
			ret = attachSticker((col+1), 1) + matrix[0][col]; //À­ÁÙ
			break;
		case 1:
			ret = attachSticker(col + 1, 0) + matrix[1][col]; //À­ÁÙ
			break;
		case 2:
			ret = attachSticker(col + 1, 1) + matrix[0][col]; //À­ÁÙ
			ret = Math.max(ret, attachSticker(col + 1, 0) + matrix[1][col]); //¾Æ·§ÁÙ
			break;
		}
		
		//¾È ºÙÀÏ °æ¿ì
		int temp = attachSticker(col + 1, 2);
		if (temp > ret) {
			ret = temp;
		}
		
		if (ret > max) {
			max = ret;
		}
		
		table[state][col] = ret;
		return ret;
		
	}
}

public class Sticker {
	
	public static void main(String[] args) {
		int[][] inputs = new int[][]{{50, 10, 100, 20, 40}, {30, 50, 70, 10, 60}};
		DPSticker sticker = new DPSticker();
		sticker.matrix = inputs;
		sticker.table = new int[3][5];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				sticker.table[i][j] = -1;
			}
		}
		
		sticker.attachSticker(0, 2);
		System.out.println(sticker.max);

		
	}

}
