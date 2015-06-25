/**
 *
 * Parallel Scavenge Collector.
 *
 * Pecentage of Eden, S0, S1 is InitialSurvivorRatio(-XX:InitialSurvivorRation), default value is 8.
 * For example, if we set -Xmn16M, then both S0 and S1 are 16/8=2M, and Eden space is 12M.
 *
 * If we set SurvivorRation by -XX:SurvivorRatio(after java 1.6), Parallel Scavenge Collector will add the value with 2,
 * the assign the value to InitialSurvivorRatio.
 * For example, if we set -Xmn16M, and SurvivorRatio is 8, then S0 and S1 are 16/(8+2)=1.6M, and Eden space is 16-1.6*2=12.8M.
 *
 * What algorithm does Parallel Scavenge Collector use?
 * Copying.
 *
 * How to ?
 * Can adjust by -XX:InitialSurvivorRation or -XX:SurvivorRation(after java 1.6).
 * Parallel Scavenge Collector will add
 */
public class ParallelScavenge {
    public static void main(String[] args) throws Exception {
        int m = 1024 * 1024;
        byte[] bytes = new byte [2 * m];
        byte[] bytes2 = new byte [2 * m];
        byte[] bytes3 = new byte [2 * m];
        System.out.println("ready to direct allocate to old");
        Thread.sleep(3000);

        byte[] bytes4 = new byte [4 * m];
        Thread.sleep(3000);
    }
}
