package Boj9019;

import java.util.LinkedList;
import java.util.Queue;

public class Dslr {
	int a;
	int b;
	
	public Dslr(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public int calcD(int n) {
		return (2 * n % 10000);
	}
	
	public int calcS(int n) {
		if (n == 0) {
			return 9999;
		}
		return (n - 1);
	}
	
	public int calcL(int n) {
		int first = n / 1000;
		int last = n % 1000;
		
		return last * 10 + first;
	}
	
	public int calcR(int n) {
		int first = n / 10;
		int last = n % 10;
		
		return last * 1000 + first;
	}
	
	// KEY를 찾는 백트래킹 시, 최단 경로(깊이) 구하기 --> BFS(필요한거 찾으면 끝!) >> DFS (모든 경우 탐색)
	public String searchMinPath() {
		int count = 0;
		
		Queue<Element> queue = new LinkedList<>();
		queue.add(new Element(a, ""));
		
		while (!queue.isEmpty()) {
			Element temp = queue.remove();
			
			int res = calcD(temp.value);
			if (res != b) {
				queue.add(new Element(res, temp.path.concat("D")));				
			} else {
				return temp.path.concat("D");
			}
			
			res = calcS(temp.value);
			if (res != b) {
				queue.add(new Element(res, temp.path.concat("S")));				
			} else {
				return temp.path.concat("S");
			}
			
			
			res = calcL(temp.value);
			if (res != b) {
				queue.add(new Element(res, temp.path.concat("L")));				
			} else {
				return temp.path.concat("L");
			}
			
	
			res = calcR(temp.value);
			if (res != b) {
				queue.add(new Element(res, temp.path.concat("R")));				
			} else {
				return temp.path.concat("R");
			}
		}
		
		return null;
	}
}
