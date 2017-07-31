
public class Java8Prac {

	public static void main(String[] args) {
		
		// 1. �⺻ �ڵ� 
		new Thread(new Runnable () {

			@Override
			public void run() {
				System.out.println("BASIC");
			}
			
		}).start();
		
		// 2. �Լ��� �������̽� Runnable�� ���۷��� ���� ����� �̸� Thread�� ����
		Runnable r = () -> System.out.println("Lambda1"); 
		new Thread(r).start();
		
		// 3. �����Ϸ��� Ÿ���߷� �̿� -> Thread�� ���ٽ� ��ü�� ����
		new Thread(() -> System.out.println("Lambda2")).start();

	}

}



