package learning;

public class SampleTest {

	public static void main(String[] args) {
		SampleTest st = new SampleTest();
		st.forLoopExample();
		
	}
	
	public void forLoopExample() {
		int i = 0;
		for (i=1;i<10;i++) {
			i++;
		}
		System.out.println(i);//this statement prints 11
	}

}
