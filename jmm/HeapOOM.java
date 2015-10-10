import java.util.List;
import java.util.ArrayList;

/**
 * Java heap OutOfMemory.
 *
 * Create object continuously, and keep reference to avoid GC free these objects.
 *
 * Test with -Xms and -Xmx:
 * java -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError HeapOOM 
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
