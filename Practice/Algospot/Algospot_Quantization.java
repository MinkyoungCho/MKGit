import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

class Quantization {
	public static final int INT_MAX = 999999;
	
	int[] items;
	int[][] cache;
	
	public Quantization(int[] items, int numOfPartitions) {
		this.items = items;
		cache = new int[items.length][numOfPartitions];
		for (int i = 0; i < cache.length; i++) {
			for (int j = 0; j < cache[0].length; j++) {
				cache[i][j] = -1; 
			}
		}
	}
	
	// items[start, ....]를 최대 nOP로 파티션 하는데 구할 수 있는 최소제곱합
	public int partition(int start, int numOfPartitions) {
		//바운드 설정
		if (start == items.length) { //파티션할 원소가 없음
			return 0;
		}
		if (numOfPartitions == 0) { //원소는 있는데 파티션이 없어야함 --> 원소가 있다면 최소 1개의 파티션 필요함 - 모순!
			return INT_MAX;
		}
		if (cache[start][numOfPartitions - 1] != -1) {
			return cache[start][numOfPartitions - 1];
		}
		
		//재귀호출
		cache[start][numOfPartitions - 1] = INT_MAX;
		for (int length = 1; start + length <= items.length; length++) {
			cache[start][numOfPartitions - 1] = Math.min(cache[start][numOfPartitions - 1], calcSse(start, start + length - 1) + partition(start + length, numOfPartitions - 1));
		}
		
		return cache[start][numOfPartitions - 1];
	}
	
	public int calcSse(int start, int end) {
		int mean = 0; // 해당 구간의 최소제곱합은 평균일때
		for (int i = start; i <= end; i++) {
			mean += items[i];
		}
		mean = mean / (end - start + 1);
		
		int sse = 0;
		for (int i = start; i <= end; i++) {
			sse += (items[i] - mean) * (items[i] - mean);
		}
		
		return sse;
	}
}

public class Algospot_Quantization {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
		String line = null;
		
		int testCase = Integer.parseInt(reader.readLine());
		for (int i = 0; i < testCase; i++) {
			line = reader.readLine();
			String[] inputs = line.split(" ");
			int[] items = new int[Integer.parseInt(inputs[0])];
			int numOfPartitions = Integer.parseInt(inputs[1]);
			
			line = reader.readLine();
			inputs = line.split(" ");
			for (int j = 0; j < inputs.length; j++) {
				items[j] = Integer.parseInt(inputs[j]);
			}
			Arrays.sort(items);
			
			//객체 생성
			Quantization quant = new Quantization(items, numOfPartitions);
			System.out.println(quant.partition(0, numOfPartitions));
		}	
	}

}
