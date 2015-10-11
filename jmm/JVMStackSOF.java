/**
 * StackOverflow on JVM Stack.
 *
 * Recursive call a method(itself), and there is no recursive exit.
 *
 * Test with -Xss (Specify at lease 228k):
 * java -Xss228k JVMStackSOF
 *
 * Stack Length: 2567
 */
public class JVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        // recursive call, no exit
        stackLeak();
    }

    public static void main(String[] args) {
        JVMStackSOF sof = new JVMStackSOF();
        try {
            sof.stackLeak();
        } catch (Throwable e) {
            System.out.println("Stack Length: " + sof.stackLength);
            e.printStackTrace();
        }
    }
}
