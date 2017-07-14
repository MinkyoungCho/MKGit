package DP;

class DPSequence {
	int[] inputs;
	
	//���ĵ� seq �޾Ƽ� �ִ� �������� ���� ����
	public int findSequence(int x, int last, int diff) {
		if (x == inputs.length) {
			return 0;
		}
		
		int val = 0;
		if (last != -1) { //ù�� ����
			if (diff != -1) {
				if (inputs[x] == last + diff) {
					val = findSequence(x + 1, inputs[x], diff) + 1;
				} else {
					val = findSequence(x + 1, last, diff);
				}
			} else { // �ι�° �� ����
				val = findSequence(x + 1, inputs[x], inputs[x] - last) + 1;
			}
		} 
		//�̹� ���� ù������ !
		val = Math.max(val, findSequence(x + 1, inputs[x], -1) + 1);
				
		return val;
		
	}
}

public class Sequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DPSequence dpseq = new DPSequence();
		dpseq.inputs = new int[]{1, 3, 4, 5, 7, 8, 9};
		
		System.out.println(dpseq.findSequence(0, 1, -1));
	}

}
