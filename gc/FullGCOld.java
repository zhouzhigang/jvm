import java.util.List;
import java.util.ArrayList;

/**
 * Full GC - Old generation.
 *
 * java -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails FullGCOld
 * java -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=6 FullGCOld
 */
public class FullGCOld {
    public static void main(String[] args) {
        try {
            Thread.sleep(10000);
            List<MemoryObject> objects = new ArrayList<MemoryObject>(6);
            for (int i = 0; i < 10; i++) {
                objects.add(new MemoryObject(1024 * 1024));
            }
            // let above objects move to old generation as soon as possible
            System.gc();
            System.gc();
            Thread.sleep(2000);

            objects.clear();
            for (int i = 0; i < 10; i++) {
                objects.add(new MemoryObject(1024 * 1024));
                if (i % 3 == 0) {
                    objects.remove(0);
                }
            }
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MemoryObject {
    private byte[] bytes;
    public MemoryObject(int objectSize) {
        this.bytes = new byte[objectSize];
    }
}

/*
java -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails FullGCOld

jstat -gcutil [pid] 1000
  S0     S1     E      O      P     YGC     YGCT    FGC    FGCT     GCT
  0.00   0.00  12.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  12.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  19.46  90.00      ?      1    0.012     2    0.012    0.023
  0.00   0.00  19.46  90.00      ?      1    0.012     2    0.012    0.023
  0.00   0.00  51.73  45.57      ?      1    0.012     3    0.017    0.028
  0.00   0.00  51.73  45.57      ?      1    0.012     3    0.017    0.028
  0.00   0.00  51.73  45.57      ?      1    0.012     3    0.017    0.028
  0.00   0.00  51.73  45.57      ?      1    0.012     3    0.017    0.028
  0.00   0.00  51.73  45.57      ?      1    0.012     3    0.017    0.028

[GC (Allocation Failure) [DefNew: 8152K->581K(9216K), 0.0119188 secs] 8152K->774
9K(19456K), 0.0129461 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[Full GC (System.gc()) [Tenured: 7168K->9216K(10240K), 0.0075055 secs] 10981K->1
0819K(19456K), [Metaspace: 103K->103K(4480K)], 0.0096596 secs] [Times: user=0.02
 sys=0.00, real=0.01 secs]
[Full GC (System.gc()) [Tenured: 9216K->9216K(10240K), 0.0046771 secs] 10819K->1
0810K(19456K), [Metaspace: 103K->103K(4480K)], 0.0068193 secs] [Times: user=0.00
 sys=0.00, real=0.01 secs]
[Full GC (Allocation Failure) [Tenured: 9216K->4666K(10240K), 0.0049463 secs] 17
115K->4666K(19456K), [Metaspace: 103K->103K(4480K)], 0.0066171 secs] [Times: use
r=0.00 sys=0.00, real=0.01 secs]
Heap
 def new generation   total 9216K, used 4401K [0x04800000, 0x05200000, 0x0520000
0)
  eden space 8192K,  53% used [0x04800000, 0x04c4c740, 0x05000000)
  from space 1024K,   0% used [0x05100000, 0x05100000, 0x05200000)
  to   space 1024K,   0% used [0x05000000, 0x05000000, 0x05100000)
 tenured generation   total 10240K, used 4666K [0x05200000, 0x05c00000, 0x05c000
00)
   the space 10240K,  45% used [0x05200000, 0x0568e940, 0x0568ea00, 0x05c00000)
 Metaspace       used 103K, capacity 2242K, committed 2368K, reserved 4480K
 */

/*
java -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=6 FullGCOld

  S0     S1     E      O      P     YGC     YGCT    FGC    FGCT     GCT
  0.00   0.00  14.03   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  14.03   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  20.76  90.00      ?      1    0.011     2    0.012    0.023
  0.00   0.00  20.76  90.00      ?      1    0.011     2    0.012    0.023
  0.00   0.00  55.13  45.57      ?      1    0.011     3    0.019    0.030
  0.00   0.00  55.13  45.57      ?      1    0.011     3    0.019    0.030
  0.00   0.00  55.13  45.57      ?      1    0.011     3    0.019    0.030
  0.00   0.00  55.13  45.57      ?      1    0.011     3    0.019    0.030
  0.00   0.00  55.13  45.57      ?      1    0.011     3    0.019    0.030

[GC (Allocation Failure) [DefNew: 7221K->581K(8960K), 0.0110855 secs] 7221K->672
5K(19200K), 0.0119854 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[Full GC (System.gc()) [Tenured: 6144K->9216K(10240K), 0.0081277 secs] 10971K->1
0819K(19200K), [Metaspace: 103K->103K(4480K)], 0.0097192 secs] [Times: user=0.01
 sys=0.00, real=0.01 secs]
[Full GC (System.gc()) [Tenured: 9216K->9216K(10240K), 0.0045448 secs] 10819K->1
0810K(19200K), [Metaspace: 103K->103K(4480K)], 0.0064905 secs] [Times: user=0.00
 sys=0.00, real=0.01 secs]
[Full GC (Allocation Failure) [Tenured: 9216K->4666K(10240K), 0.0074632 secs] 17
104K->4666K(19200K), [Metaspace: 103K->103K(4480K)], 0.0102912 secs] [Times: use
r=0.00 sys=0.00, real=0.01 secs]
Heap
 def new generation   total 8960K, used 4387K [0x04800000, 0x05200000, 0x0520000
0)
  eden space 7680K,  57% used [0x04800000, 0x04c48ef0, 0x04f80000)
  from space 1280K,   0% used [0x050c0000, 0x050c0000, 0x05200000)
  to   space 1280K,   0% used [0x04f80000, 0x04f80000, 0x050c0000)
 tenured generation   total 10240K, used 4666K [0x05200000, 0x05c00000, 0x05c000
00)
   the space 10240K,  45% used [0x05200000, 0x0568e940, 0x0568ea00, 0x05c00000)
 Metaspace       used 103K, capacity 2242K, committed 2368K, reserved 4480K

 */
