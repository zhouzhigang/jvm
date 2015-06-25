/**
 *
 * Serial GC [DefNew].
 *
 * java -XX:+UseSerialGC -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=6 MinorGC
 * 
 * ParNew [ParNew.]
 * java -XX:+UseParNewGC -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=6 MinorGC
 *
 *  S0 and S1 = 16/(6+2) = 2MB; Eden space = 16-2*2 = 12MB;
 *  Old Generation = 40-16 = 24MB.
 *
 * jstat -gcutil [pid] 1000
 *
 */
public class MinorGC {

    public static void main(String[] args) {
        try {
            Thread.sleep(10000);
            MemoryObject object = new MemoryObject(1024 * 1024);
            happenMinorGC(11);
            Thread.sleep(2000);
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

java -XX:+UseSerialGC -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=6 MinorGC

jstat -gcutil [pid] 1000
 S0     S1     E      O      P     YGC     YGCT    FGC    FGCT     GCT
 0.00   0.00  10.59   0.00      ?      0    0.000     0    0.000    0.000
 0.00   0.00  10.59   0.00      ?      0    0.000     0    0.000    0.000
 0.00  78.36   8.33   0.00      ?      1    0.005     0    0.000    0.005
 0.00  78.36   8.33   0.00      ?      1    0.005     0    0.000    0.005
 0.00  78.36  18.62   0.00      ?      1    0.005     0    0.000    0.005
 0.00  78.36  18.62   0.00      ?      1    0.005     0    0.000    0.005

[GC (Allocation Failure) [DefNew: 11541K->1604K(14336K), 0.0053050 secs] 11541K-
>1604K(38912K), 0.0068337 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
minor gc should happen
happenMinorGCIndex: 11
Heap
 def new generation   total 14336K, used 4016K [0x04800000, 0x05800000, 0x058000
00)
  eden space 12288K,  19% used [0x04800000, 0x04a5af40, 0x05400000)
  from space 2048K,  78% used [0x05600000, 0x05791340, 0x05800000)
  to   space 2048K,   0% used [0x05400000, 0x05400000, 0x05600000)
 tenured generation   total 24576K, used 0K [0x05800000, 0x07000000, 0x07000000)

   the space 24576K,   0% used [0x05800000, 0x05800000, 0x05800200, 0x07000000)
 Metaspace       used 104K, capacity 2244K, committed 2368K, reserved 4480K

 */

/*
java -XX:+UseParNewGC -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=6 MinorGC

jstat -gcutil [pid] 1000
 S0     S1     E      O      P     YGC     YGCT    FGC    FGCT     GCT
 0.00   0.00  10.59   0.00      ?      0    0.000     0    0.000    0.000
 0.00   0.00  10.59   0.00      ?      0    0.000     0    0.000    0.000
 0.00  78.85   8.33   0.00      ?      1    0.061     0    0.000    0.061
 0.00  78.85   8.33   0.00      ?      1    0.061     0    0.000    0.061
 0.00  78.85  18.62   0.00      ?      1    0.061     0    0.000    0.061
 0.00  78.85  18.62   0.00      ?      1    0.061     0    0.000    0.061

Java HotSpot(TM) Client VM warning: Using the ParNew young collector with the Se
rial old collector is deprecated and will likely be removed in a future release
[GC (Allocation Failure) [ParNew: 11541K->1614K(14336K), 0.0612444 secs] 11541K-
>1614K(38912K), 0.0626609 secs] [Times: user=0.02 sys=0.00, real=0.06 secs]
minor gc should happen
happenMinorGCIndex: 11
Heap
 par new generation   total 14336K, used 4026K [0x04a00000, 0x05a00000, 0x05a000
00)
  eden space 12288K,  19% used [0x04a00000, 0x04c5af40, 0x05600000)
  from space 2048K,  78% used [0x05800000, 0x05993b38, 0x05a00000)
  to   space 2048K,   0% used [0x05600000, 0x05600000, 0x05800000)
 tenured generation   total 24576K, used 0K [0x05a00000, 0x07200000, 0x07200000)

   the space 24576K,   0% used [0x05a00000, 0x05a00000, 0x05a00200, 0x07200000)
 Metaspace       used 104K, capacity 2244K, committed 2368K, reserved 4480K

 */
