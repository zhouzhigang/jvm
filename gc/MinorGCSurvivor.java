/**
 *
 * Minor GC - Survivor Space.
 *
 * java -Xms40M -Xmx40M -Xmn16M -verbose:gc -XX:+PrintGCDetails MinorGCSurvivor
 *
 *  S0 and S1 = 16/8 = 2MB; Eden space = 16-2*2 = 12MB;
 *  Old Generation = 40-16 = 24MB.
 *
 * jstat -gcutil [pid] 1000
 *
 */
public class MinorGCSurvivor {

	public static void main(String[] args) {
		try {
			Thread.sleep(10000);
			MemoryObject object = new MemoryObject(1024 * 1024);
			MemoryObject object2 = new MemoryObject(1024 * 1024 * 2);
			happenMinorGC(9);
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
jstat -gcutil [pid] 1000
  S0     S1     E      O      P     YGC     YGCT    FGC    FGCT     GCT
  0.00   0.00  10.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  10.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  10.01   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  95.44   0.00      ?      0    0.000     0    0.000    0.000
  0.00   0.00  95.44   0.00      ?      0    0.000     0    0.000    0.000
  0.00 100.00   7.77   8.36      ?      1    0.007     0    0.000    0.007
  0.00 100.00   7.77   8.36      ?      1    0.007     0    0.000    0.007

minor gc should happen
happenMinorGCIndex: 9
[GC (Allocation Failure) [DefNew: 12583K->1600K(14784K), 0.0074052 secs] 12583K-
>3653K(39360K), 0.0095063 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
Heap
 def new generation   total 14784K, used 2756K [0x04800000, 0x05800000, 0x058000
00)
  eden space 13184K,   8% used [0x04800000, 0x049210e8, 0x054e0000)
  from space 1600K, 100% used [0x05670000, 0x05800000, 0x05800000)
  to   space 1600K,   0% used [0x054e0000, 0x054e0000, 0x05670000)
 tenured generation   total 24576K, used 2053K [0x05800000, 0x07000000, 0x070000
00)
   the space 24576K,   8% used [0x05800000, 0x05a015b8, 0x05a01600, 0x07000000)
 Metaspace       used 104K, capacity 2244K, committed 2368K, reserved 4480K

when minor GC happens, object(1M) and object2(2M) need move to survivor space,
while their sum is large than survivor space(2M), then object2 can't put in.
So it was moved to tenured(old) generation(2/24=8.33%).

 */
