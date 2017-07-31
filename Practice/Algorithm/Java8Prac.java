
public class Java8Prac {

	public static void main(String[] args) {
		
		// 1. 기본 코드 
		new Thread(new Runnable () {

			@Override
			public void run() {
				System.out.println("BASIC");
			}
			
		}).start();
		
		// 2. 함수형 인터페이스 Runnable의 레퍼런스 변수 만들고 이를 Thread에 전달
		Runnable r = () -> System.out.println("Lambda1"); 
		new Thread(r).start();
		
		// 3. 컴파일러의 타입추론 이용 -> Thread에 람다식 자체를 전달
		new Thread(() -> System.out.println("Lambda2")).start();

	}

}



