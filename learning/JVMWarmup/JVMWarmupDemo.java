package learning.JVMWarmup;

public class JVMWarmupDemo {

		/*
	 * We've to run the application twice; once with the load() method call inside
	 * the static block and once without this method call:
	 * As expected, with warm up approach shows much better performance than the normal one.
	 */
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
