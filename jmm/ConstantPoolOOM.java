import java.util.List;
import java.util.ArrayList;

/**
 * ConstantPool OutOfMemory.
 * 
 * Create string continuously, and put them in constant pool using intern() method
 *
 * Test with -XX:PermSize -XX:MaxPermSize
 * java -XX:PermSize=10m -XX:MaxPermSize=10m ConstantPool
 *
 * Note: PermSize was removed at java 8.0(the parameter won't work)
 */
public class ConstantPoolOOM {

    public static void main(String[] args) {
        // keep reference of these string constant
        List<String> list = new ArrayList<String>();
        int i = 0;
        // create string continuously, and put them in constant pool using intern() method
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
