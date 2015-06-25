/**
 * Test if JVM use Reference Counting GC.
 *
 * An object is considered to be garbage when no references to that object exist.
 * But how can we tell when no references to an object exist?
 * Reference Counting is to keep track in each object of the total number of references to that object.
 *
 * Questions:
 *  When Objects Refer to Other Objects?
 *  What about reference cycle?
 *
 * Advantage:
 *  easy to implement;
 *  it is not necessary to wait until there is insufficient memory before initiating the garbage collection process;
 *  if the reference count is zero, the object is garbage.
 * Disadvantage:
 *  every object requires the special reference count field;
 *  every time one reference is assigned to another, the reference counts must be adjusted as above;
 *  can't handle reference cycles.
 *
 * Usage
 *  java -verbose:gc -XX:+PrintGCDetails ReferenceCountingGC
 *
 * It turns out that java didn't use reference countting to collect garbage.
 * In fact, it use "Reachability Analysis" to check if an object still alive.
 * Start from "GC roots", search "Reference Chain".
 *
 * Reference
 * <a href="http://en.wikipedia.org/wiki/Reference_counting">Reference counting - wikipedia</a>
 * <a href="http://www.brpreiss.com/books/opus5/html/page421.html">Reference Counting Garbage Collection</a>
 */
public class ReferenceCountingGC {
    /**
     * instance field is used for reference each other.
     */
    public Object instance = null;
    /**
     * bigSize field is used for occupied some memory, so that we can easy to view it already garbaged.
     */
    private byte[] bigSize = new byte[2 * 1024 * 1024];

    public static void testRefCycle() {
        // create two objects
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        // reference each other
        objA.instance = objB;
        objB.instance = objA;
        // release the reference
        objA = null;
        objB = null;

        // will objA and objB be garbaged when gc happens?
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        Thread.sleep(5000); // sleep a moment to view the jstat
        testRefCycle();
        Thread.sleep(2000);
    }
}
/*
jstat -gcutil [pid] 1000
  S0     S1     E      O      P     YGC     YGCT    FGC    FGCT     GCT
  0.00   0.00  21.35   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  21.35   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00   0.00   5.29      ?      1    0.006     1    0.005    0.010
  0.00   0.00   0.00   5.29      ?      1    0.006     1    0.005    0.010

java -verbose:gc -XX:+PrintGCDetails ReferenceCountingGC

[GC (Allocation Failure) [DefNew: 2990K->512K(4928K), 0.0060110 secs] 2990K->262
8K(15872K), 0.0078618 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[Full GC (System.gc()) [Tenured: 2116K->579K(10944K), 0.0048583 secs] 4762K->579
K(15872K), [Metaspace: 102K->102K(4480K)], 0.0073711 secs] [Times: user=0.00 sys
=0.00, real=0.01 secs]
Heap
 def new generation   total 4992K, used 89K [0x04800000, 0x04d60000, 0x09d50000)

  eden space 4480K,   2% used [0x04800000, 0x048167e0, 0x04c60000)
  from space 512K,   0% used [0x04c60000, 0x04c60000, 0x04ce0000)
  to   space 512K,   0% used [0x04ce0000, 0x04ce0000, 0x04d60000)
 tenured generation   total 10944K, used 579K [0x09d50000, 0x0a800000, 0x1480000
0)
   the space 10944K,   5% used [0x09d50000, 0x09de0d30, 0x09de0e00, 0x0a800000)
 Metaspace       used 103K, capacity 2242K, committed 2368K, reserved 4480K


From the log, we can see that 4762K->579K(15872K), which means the gc was happened even reference each other.

 */
