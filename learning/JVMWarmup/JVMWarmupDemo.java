package learning.JVMWarmup;

public class JVMWarmupDemo {

	static {

		long start = System.nanoTime();
		ManualClassLoader.load();
		long end = System.nanoTime();
		System.out.println("Warm Up time : " + (end - start));

	}

	public static void main(String[] args) {
		long start = System.nanoTime();
		ManualClassLoader.load();
		long end = System.nanoTime();
		System.out.println("Total time taken : " + (end - start));

	}

}
