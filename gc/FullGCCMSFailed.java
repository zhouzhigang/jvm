import java.util.List;
import java.util.ArrayList;

/**
 * Full GC - CMS(Concurrent Make Sweep) Collector.
 *
 * java -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails FullGCCMSFailed
 * java -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=6 FullGCCMSFailed
 * java -XX:+UseConcMarkSweepGC -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails FullGCCMSFailed
 *
 */
public class FullGCCMSFailed {
    public static void main(String[] args) {
        try {
            Thread.sleep(10000);
            List<MemoryObject> objects = new ArrayList<MemoryObject>(6);
            for (int i = 0; i < 9; i++) {
                objects.add(new MemoryObject(1024 * 1024));
            }
            Thread.sleep(2000);

            objects.remove(0);
            objects.remove(0);
            objects.remove(0);
            for (int i = 0; i < 20; i++) {
                objects.add(new MemoryObject(1024 * 1024));
                if (i % 2 == 0) {
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
java -XX:+UseConcMarkSweepGC -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails FullGCCMSFailed

  S0     S1     E      O      P     YGC     YGCT    FGC    FGCT     GCT
  0.00   0.00  12.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  12.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  12.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  12.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  12.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00  57.79  26.96  70.02      ?      1    0.009     1    0.001    0.010
  0.00  57.79  26.96  70.02      ?      1    0.009     1    0.001    0.010
  0.00   0.00  87.50  95.58      ?      3    0.010     8    0.057    0.067
  0.00   0.00  87.50  95.58      ?      3    0.010     8    0.057    0.067
  0.00   0.00  87.50  95.58      ?      3    0.010    10    0.065    0.075
  0.00   0.00  87.50  95.58      ?      3    0.010    10    0.065    0.075
  0.00   0.00  87.50  95.58      ?      3    0.010    12    0.073    0.083

[GC (Allocation Failure) [ParNew: 8152K->591K(9216K), 0.0092113 secs] 8152K->776
1K(19456K), 0.0101708 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 7170K(10240K)] 8785K(19456K), 0.0016
355 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 se
cs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.0
0 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 7920K->7920K(9216K), 0.0006472 secs][CMS[CMS-c
oncurrent-abortable-preclean: 0.092/2.002 secs] [Times: user=0.13 sys=0.00, real
=2.00 secs]
 (concurrent mode failure): 7170K->8772K(10240K), 0.0110686 secs] 15090K->8772K(
19456K), [Metaspace: 103K->103K(4480K)], 0.0135091 secs] [Times: user=0.00 sys=0
.00, real=0.01 secs]
[GC (Allocation Failure) [ParNew: 7321K->7321K(9216K), 0.0006513 secs][CMS: 8772
K->9787K(10240K), 0.0123170 secs] 16094K->12859K(19456K), [Metaspace: 103K->103K
(4480K)], 0.0158095 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 9787K(10240K)] 13883K(19456K), 0.001
0228 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[Full GC (Allocation Failure) [CMS[CMS-concurrent-mark: 0.003/0.004 secs] [Times
: user=0.00 sys=0.00, real=0.01 secs]
 (concurrent mode failure): 9787K->9787K(10240K), 0.0123372 secs] 17111K->14907K
(19456K), [Metaspace: 103K->103K(4480K)], 0.0140975 secs] [Times: user=0.00 sys=
0.00, real=0.01 secs]
[Full GC (Allocation Failure) [CMS: 9787K->9787K(10240K), 0.0077003 secs] 17089K
->15931K(19456K), [Metaspace: 103K->103K(4480K)], 0.0094738 secs] [Times: user=0
.01 sys=0.00, real=0.01 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 9787K(10240K)] 16955K(19456K), 0.000
8354 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[Full GC (Allocation Failure) [CMS[CMS-concurrent-mark: 0.002/0.003 secs] [Times
: user=0.02 sys=0.00, real=0.00 secs]
 (concurrent mode failure): 9787K->9787K(10240K), 0.0115548 secs] 16955K->15931K
(19456K), [Metaspace: 103K->103K(4480K)], 0.0132901 secs] [Times: user=0.02 sys=
0.00, real=0.01 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 9787K(10240K)] 16955K(19456K), 0.001
2895 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 se
cs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.0
0 secs]
[CMS-concurrent-abortable-preclean-start]
[CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00
, real=0.00 secs]
[GC (CMS Final Remark) [YG occupancy: 7168 K (9216 K)][Rescan (parallel) , 0.001
3478 secs][weak refs processing, 0.0003661 secs][class unloading, 0.0003772 secs
][scrub symbol table, 0.0005215 secs][scrub string table, 0.0001808 secs][1 CMS-
remark: 9787K(10240K)] 16955K(19456K), 0.0074977 secs] [Times: user=0.00 sys=0.0
0, real=0.01 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 s
ecs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 s
ecs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 9787K(10240K)] 16955K(19456K), 0.001
1255 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.01 se
cs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.0
0 secs]
[CMS-concurrent-abortable-preclean-start]
[CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00
, real=0.00 secs]
[GC (CMS Final Remark) [YG occupancy: 7168 K (9216 K)][Rescan (parallel) , 0.001
4469 secs][weak refs processing, 0.0002708 secs][class unloading, 0.0005449 secs
][scrub symbol table, 0.0005568 secs][scrub string table, 0.0002268 secs][1 CMS-
remark: 9787K(10240K)] 16955K(19456K), 0.0079867 secs] [Times: user=0.00 sys=0.0
0, real=0.01 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 s
ecs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 s
ecs]
Heap
 par new generation   total 9216K, used 7332K [0x04c00000, 0x05600000, 0x0560000
0)
  eden space 8192K,  89% used [0x04c00000, 0x05329200, 0x05400000)
  from space 1024K,   0% used [0x05500000, 0x05500000, 0x05600000)
  to   space 1024K,   0% used [0x05400000, 0x05400000, 0x05500000)
 concurrent mark-sweep generation total 10240K, used 9787K [0x05600000, 0x060000
00, 0x06000000)
 Metaspace       used 103K, capacity 2242K, committed 2368K, reserved 4480K

 */
