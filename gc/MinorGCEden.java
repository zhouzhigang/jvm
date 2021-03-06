/**
 *
 * Minor GC - Eden space.
 *
 * java -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails MinorGCEden
 *
 *  S0 and S1 = 16/8 = 2MB; Eden space = 16-2*2 = 12MB;
 *  Old Generation = 40-16 = 24MB.
 *
 * jstat -gcutil [pid] 1000
 *
 */
public class MinorGCEden {

    public static void main(String[] args) {
        try {
            Thread.sleep(10000);
            MemoryObject object = new MemoryObject(1024 * 1024);
            for (int i = 0; i < 2; i++) {
                happenMinorGC(11);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void happenMinorGC(int happenMinorGCIndex) throws Exception {
        for (int i = 0; i < happenMinorGCIndex; i++) {
            if (i == happenMinorGCIndex - 1) {
                Thread.sleep(2000);
                System.out.println("minor gc should happen");
                System.out.println("happenMinorGCIndex: " + happenMinorGCIndex);
            }
            // asign to eden space, and no reference, will be
            new MemoryObject(1024 * 1024);
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


jstat -gcutil [pid] 1000
  S0     S1     E      O      P     YGC     YGCT    FGC    FGCT     GCT
  0.00   0.00  10.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  10.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  10.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  95.44   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  95.44   0.00      ?      0    0.000     0    0.000    0.000
  0.00 100.00   7.77   0.02      ?      1    0.005     0    0.000    0.005
  0.00 100.00   7.77   0.02      ?      1    0.005     0    0.000    0.005
  0.00 100.00  87.40   0.02      ?      1    0.005     0    0.000    0.005
  0.00 100.00  87.40   0.02      ?      1    0.005     0    0.000    0.005
  0.00 100.00  95.16   0.02      ?      1    0.005     0    0.000    0.005

-XX:+PrintGCDetails
minor gc should happen
happenMinorGCIndex: 11
[GC (Allocation Failure) [DefNew: 12583K->1600K(14784K), 0.0049816 secs] 12583K-
>1605K(39360K), 0.0058392 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
minor gc should happen
happenMinorGCIndex: 11
Heap
 def new generation   total 14784K, used 14278K [0x04800000, 0x05800000, 0x05800
000)
  eden space 13184K,  96% used [0x04800000, 0x05461a40, 0x054e0000)
  from space 1600K, 100% used [0x05670000, 0x05800000, 0x05800000)
  to   space 1600K,   0% used [0x054e0000, 0x054e0000, 0x05670000)
 tenured generation   total 24576K, used 5K [0x05800000, 0x07000000, 0x07000000)

   the space 24576K,   0% used [0x05800000, 0x058014a0, 0x05801600, 0x07000000)
 Metaspace       used 104K, capacity 2242K, committed 2368K, reserved 4480K


when happenMinorGCIndex equals 11, there are 11M objects in Eden space, latter it assign 1M data again,
which will cause Eden space is not enough, so the minor GC was triggered.

new generation heap: before GC, used 12583K, after GC, 1600K

 */
