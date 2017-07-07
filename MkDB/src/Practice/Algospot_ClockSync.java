package Practice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class Sync {
	
	int numOfSwitch = 10;
	int numOfClocks = 16;
	int[][] connection = {{ 1,  1,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
			 { 0,  0,  0,  1,  0,  0,  0,  1,  0,  1,  0,  1,  0,  0,  0,  0 },
			 { 0,  0,  0,  0,  1,  0,  0,  0,  0,  0,  1,  0,  0,  0,  1,  1 },
			 { 1,  0,  0,  0,  1,  1,  1,  1,  0,  0,  0,  0,  0,  0,  0,  0 },
			 { 0,  0,  0,  0,  0,  0,  1,  1,  1,  0,  1,  0,  1,  0,  0,  0 },
			 { 1,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  1 },
			 { 0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  1 },
			 { 0,  0,  0,  0,  1,  1,  0,  1,  0,  0,  0,  0,  0,  0,  1,  1 },
			 { 0,  1,  1,  1,  1,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
			 { 0,  0,  0,  1,  1,  1,  0,  0,  0,  1,  0,  0,  0,  1,  0,  0 }
			};
			
	int ret = 10000;
	
	public boolean isSynchronized(int[] clocks) {

		for (int i = 0; i < numOfClocks; i++) {
			if (clocks[i] % 12 != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public int pressSwitch(int currentSwitch, int[] clocks) {
		int[] backup = new int[numOfClocks]; // clocks를 백업해둠
		for (int i = 0; i < numOfClocks; i++) {
			backup[i] = clocks[i];
		}
		
		if (currentSwitch == numOfSwitch) { //모든 스위치를 갔을 때 모든 시계가 12를 가리키는지 확인!
			if (isSynchronized(clocks)) {
				return 0;
			} else {
				return 1000000;
			}
		}
		
		for (int i = 0; i < 4; i++) { // Switch 누르는 횟수(0번 ~ 3번)
			for (int j = 0; j < numOfClocks; j++) {
				clocks[j] = backup[j];
			}
			
			for (int j = 0; j < numOfClocks; j++) {
				if (connection[currentSwitch][j] == 1) {
					clocks[j] = clocks[j] + 3 * i;
				}
			}
			ret = Math.min(ret, i + pressSwitch(currentSwitch + 1, clocks));
		}
		
		return ret;
	}
}

public class Algospot_ClockSync {

	public static void main(String[] args) throws Exception {
		
		File file = new File("input.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		
		int testCase = Integer.parseInt(reader.readLine());
		for (int i = 0; i < testCase; i++) {
			line = reader.readLine();
			String[] inputs = line.split(" ");
			int[] clocks = new int[inputs.length];
			for (int j = 0; j < inputs.length; j++) {
				clocks[j] = Integer.parseInt(inputs[j]);
			}
		
			Sync sync = new Sync();
			
			int ret = sync.pressSwitch(0, clocks);
			if (ret >= 1000) {
				System.out.println(-1);
			} else {
				System.out.println(ret);
				
			}
		}
	}

}
