/**
 * OutOfMemory in JVM Stack.
 *
 * Create threads continuously, and keep the thread alive.
 *
 * Test with -Xss:
 * Java -Xss2m JVMStackOOM
 *
 */
public class JVMStackOOM {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        // each thread has a private stack, create thread consistantly
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // keep the thread alive(not stop)
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JVMStackOOM oom = new JVMStackOOM();
        oom.stackLeakByThread();
    }
}
