package Boj2294; // EASY

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		line = reader.readLine();
		String[] inputs = line.split(" ");
		int numOfCoins = Integer.parseInt(inputs[0]);
		int target = Integer.parseInt(inputs[1]);
		
		int[] coins = new int[numOfCoins];
		for (int i = 0; i < coins.length; i++) {
			coins[i] = Integer.parseInt(reader.readLine());
		}
		
		Coin coin = new Coin(coins, target);
		int res = coin.mixCoins(0, target);
		if (res >= coin.INT_MAX) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}
	}

}
