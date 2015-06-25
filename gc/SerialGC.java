/**
 * Test Serial Collector.
 *
 * Serial Collector is the default GC of JVM client mode.
 * All objects are created in Eden are of Young generation.
 * If created objects size greater than Eden size, or greate than PretenureSizeThreadhold configuration argument,
 * then assign in Old generation.
 *
 * Eden size is not enough will trigger Minor GC, when it happens,
 * it will check whether the greater than Old generation  at each Minor GC.
 *
 */
public class SerialGC {
	public static void main(String[] args) {
		int m = 1024 * 1024;
		byte[] b = new byte [2 * m];
		byte[] b2 = new byte [2 * m];
		byte[] b3 = new byte [2 * m];
		byte[] b4 = new byte [2 * m];
		byte[] b5 = new byte [2 * m];
		byte[] b6 = new byte [2 * m];
		byte[] b7 = new byte [2 * m];
	}
}
