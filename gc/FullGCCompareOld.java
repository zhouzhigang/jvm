/**
 * Full GC - ParallelGC.
 *
 * java -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:+UseParallelGC FullGCCompareOld
 */
public class FullGCCompareOld {
    public static void main(String[] args) {
        try {
        	Thread.sleep(10000);

            int m = 1024 * 1024;
            byte[] bytes = new byte [2 * m];
            byte[] bytes2 = new byte [2 * m];
            byte[] bytes3 = new byte [2 * m];
        	System.out.println("ready to happen one minor gc, if parallel scavenge gc, then should one full gc");

        	byte[] bytes4 = new byte[2 * m];
        	Thread.sleep(3000);
        	System.out.println("minor gc end");

        	byte[] bytes5 = new byte[2 * m];
        	byte[] bytes6 = new byte[2 * m];
        	System.out.println("minor gc again, and should direct full gc");

        	byte[] bytes7 = new byte[2 * m];
        	Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
java -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:+UseParallelGC FullGCCompareOld

jstat -gcutil [pid] 1000

  S0     S1     E      O      P     YGC     YGCT    FGC    FGCT     GCT
  0.00   0.00  14.03   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  14.03   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  14.03   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  14.03   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  14.03   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  26.67  65.66      ?      1    0.010     1    0.014    0.023
  0.00   0.00  26.67  65.66      ?      1    0.010     1    0.014    0.023
  0.00   0.00  26.67  65.66      ?      1    0.010     1    0.014    0.023
  0.00   0.00  80.00  85.66      ?      1    0.010     2    0.029    0.038
  0.00   0.00  80.00  85.66      ?      1    0.010     2    0.029    0.038
  0.00   0.00  80.00  85.66      ?      1    0.010     2    0.029    0.038

ready to happen one minor gc, if parallel scavenge gc, then should one full gc
[GC (Allocation Failure) [PSYoungGen: 7221K->640K(8960K)] 7221K->6784K(19200K),
0.0099119 secs] [Times: user=0.03 sys=0.00, real=0.02 secs]
[Full GC (Ergonomics) [PSYoungGen: 640K->0K(8960K)] [ParOldGen: 6144K->6723K(102
40K)] 6784K->6723K(19200K), [Metaspace: 103K->103K(4480K)], 0.0138584 secs] [Tim
es: user=0.00 sys=0.00, real=0.02 secs]
minor gc end
minor gc again, and should direct full gc
[Full GC (Ergonomics) [PSYoungGen: 6294K->4096K(8960K)] [ParOldGen: 6723K->8771K
(10240K)] 13018K->12867K(19200K), [Metaspace: 103K->103K(4480K)], 0.0152872 secs
] [Times: user=0.03 sys=0.00, real=0.02 secs]
Heap
 PSYoungGen      total 8960K, used 6298K [0x05200000, 0x05c00000, 0x05c00000)
  eden space 7680K, 82% used [0x05200000,0x058268b8,0x05980000)
  from space 1280K, 0% used [0x05980000,0x05980000,0x05ac0000)
  to   space 1280K, 0% used [0x05ac0000,0x05ac0000,0x05c00000)
 ParOldGen       total 10240K, used 8771K [0x04800000, 0x05200000, 0x05200000)
  object space 10240K, 85% used [0x04800000,0x05090df8,0x05200000)
 Metaspace       used 103K, capacity 2242K, committed 2368K, reserved 4480K

 */
