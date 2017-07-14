package Practice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

class Coins {
	int[] arrayOfCoins;
	
	public Coins(String[] inputs) {
		arrayOfCoins = new int[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			arrayOfCoins[i] = Integer.parseInt(inputs[i]);
		}
	}
	
	int count = 0;
	public void findCombinations(int goal, int index) {
		if (goal == 0) {
			count++;
			return;
		} 
		
		for (int i = index; i < arrayOfCoins.length; i++) {
			if (goal - arrayOfCoins[i] >= 0) {
				findCombinations(goal - arrayOfCoins[i], i);
			}
		}
		
	}
}

public class Algospot_Coins {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
		String line = null;
		
		int testCase = Integer.parseInt(reader.readLine());
		for (int i = 0; i < testCase; i++) {
			line = reader.readLine();
			String[] inputs = line.split(" ");
			int goal = Integer.parseInt(inputs[0]);

			line = reader.readLine();
			inputs = line.split(" ");
			
			Coins coins = new Coins(inputs);
			coins.findCombinations(goal, 0);
			System.out.println(coins.count);
		}
	}

}
